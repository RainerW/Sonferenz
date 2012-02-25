package de.bitnoise.sonferenz.service.v2.services.idp.provider.crowd;

import java.net.ConnectException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.common.eventbus.Subscribe;

import de.bitnoise.sonferenz.service.v2.events.ConfigReload;
import de.bitnoise.sonferenz.service.v2.exceptions.GeneralConferenceException;
import de.bitnoise.sonferenz.service.v2.services.ConfigurationService;
import de.bitnoise.sonferenz.service.v2.services.idp.Identity;
import de.bitnoise.sonferenz.service.v2.services.idp.provider.Idp;

@Service
public class CrowdIdp implements Idp
{
  private static final Logger logger = LoggerFactory.getLogger(CrowdIdp.class);

  public static final String IDP_NAME = "crowd";

  private static final String TEMPLATE_GROUP = "user/group/direct?username={username}";

  private static final String TEMPLATE_USER = "user?username={username}";

  private static final String TEMPLATE_USER_CREATE = "user";

  private static final String TEMPLATE_PASSWORD = "user/password?username={username}";

  private static final String TEMPLATE_AUTHENTICATION = "authentication?username={username}";

  private String crowdUsername;

  private String crowdPassword;

  private String crowdRestService;

  private String crowdGroup;

  private RestTemplate restTemplate;

  @Autowired
  ConfigurationService config;

  @Subscribe
  public void onConfigReload(ConfigReload event)
  {
    crowdRestService = config.getStringValueOr(null, "idp.crowd.url");
    crowdUsername = config.getStringValueOr(null, "idp.crowd.user");
    crowdPassword = config.getStringValueOr(null, "idp.crowd.password");
    crowdGroup = config.getStringValueOr(null, "idp.crowd.group");

    if (crowdRestService == null || crowdUsername == null)
    {
      logger.warn("Missing Crowd Configuration");
    }
    else
    { // init client
      HttpClient client = new HttpClient();
      UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(crowdUsername, crowdPassword);
      client.getState().setCredentials(AuthScope.ANY, credentials);
      CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(client);
      restTemplate = new RestTemplate(commons);
    }
  }

  @Override
  public String getProviderName()
  {
    return IDP_NAME;
  }

  @Override
  public boolean checkIdentityExist(String name)
  {
    boolean exist = false;
    try
    {
      CrowdUser result = restTemplate.getForObject(crowdRestService + TEMPLATE_USER, CrowdUser.class, name);
      if (result != null)
      {
        exist = true;
      }
    }
    catch (HttpClientErrorException e)
    {
      logger.debug(e.getMessage());
    }
    return exist;
  }

  @Override
  public void createIdentity(String name, String password)
  {
    try
    {
      CrowdUser crowdUser = new CrowdUser();
      crowdUser.setName(name);
      CrowdPassword passwordo = new CrowdPassword();
      passwordo.setValue(password);
      crowdUser.setPassword(passwordo);
      crowdUser.setEmail("dummy@seitenbau.com");
      crowdUser.setFirstname("firstname");
      crowdUser.setLastname("lastname");
      crowdUser.setActive("true");

      restTemplate.postForLocation(crowdRestService + TEMPLATE_USER_CREATE, crowdUser);

      if (crowdGroup != null)
      {
        CrowdGroup group = new CrowdGroup();
        group.setName(crowdGroup);
        restTemplate.postForLocation(crowdRestService + TEMPLATE_GROUP, group, name);
      }
    }
    catch (RuntimeException ce)
    {
      if (ce.getCause() instanceof ConnectException)
      {
        throw new GeneralConferenceException("Unable to Connect to IDP Backend");
      }
      throw new GeneralConferenceException("Internal Error while connection to IDP Backend");
    }
  }

  @Override
  public void setPassword(String name, String password)
  {
    CrowdPassword passwordo = new CrowdPassword();
    passwordo.setValue(password);
    restTemplate.put(crowdRestService + TEMPLATE_PASSWORD, passwordo, name);
  }

  @Override
  public boolean authenticate(String name, String password)
  {
    CrowdPassword pwd = new CrowdPassword();
    pwd.setValue(password);

    try
    {
      CrowdUser user = restTemplate.postForObject(crowdRestService + TEMPLATE_AUTHENTICATION, pwd, CrowdUser.class,
          name);
      if (user.getName().equals(name))
      {
        return true;
      }
    }
    catch (RestClientException e)
    {
      logger.info("Authentication failed!");
    }
    return false;
  }

  @Override
  public Identity getIdentity(String name)
  {
    Identity res = null;
    if(restTemplate==null) {
      return null;
    }
    try
    {
      CrowdUser result = restTemplate.getForObject(crowdRestService + TEMPLATE_USER, CrowdUser.class, name);
      if (result != null)
      {
        res = new Identity();
        res.setName(result.getName());
        res.setPassword(null);
        res.setFirstName(result.getFirstname());
        res.setLastName(result.getLastname());
        res.setProvider(IDP_NAME);
        res.setEmail(result.getEmail());
      }
    }
    catch (HttpClientErrorException e)
    {
      logger.debug(e.getMessage());
    }
    return res;
  }

}
