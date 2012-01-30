package de.bitnoise.sonferenz.service.v2.services.idp.provider.crowd;

import java.net.ConnectException;

import javax.annotation.PostConstruct;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.bitnoise.sonferenz.service.v2.exceptions.GeneralConferenceException;
import de.bitnoise.sonferenz.service.v2.services.idp.provider.Idp;

public class CrowdIdp implements Idp
{
  private static final Logger logger = LoggerFactory.getLogger(CrowdIdp.class);

  private static final String IDP_NAME = "crowd";

  private static final String TEMPLATE_GROUP = "user/group/direct?username={username}";

  private static final String TEMPLATE_USER = "user?username={username}";

  private static final String TEMPLATE_USER_CREATE = "user";

  private static final String TEMPLATE_PASSWORD = "user/password?username={username}";

  private String crowdUsername;

  private String crowdPassword;

  private String crowdRestService;

  private String crowdGroup;

  private RestTemplate restTemplate;

  public void setCrowdGroup(String crowdGroup)
  {
    this.crowdGroup = crowdGroup;
  }

  public void setCrowdRestService(String crowdRestService)
  {
    this.crowdRestService = crowdRestService;
  }

  public void setCrowdUsername(String crowdUsername)
  {
    this.crowdUsername = crowdUsername;
  }

  public void setCrowdPassword(String crowdPassword)
  {
    this.crowdPassword = crowdPassword;
  }

  @PostConstruct
  public void init()
  {
    HttpClient client = new HttpClient();
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
        crowdUsername, crowdPassword);
    client.getState().setCredentials(AuthScope.ANY, credentials);
    CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(
        client);
    restTemplate = new RestTemplate(commons);
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
      CrowdUser result = restTemplate.getForObject(crowdRestService
          + TEMPLATE_USER, CrowdUser.class, name);
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

      restTemplate.postForLocation(crowdRestService + TEMPLATE_USER_CREATE,
          crowdUser);

      if (crowdGroup != null)
      {
        CrowdGroup group = new CrowdGroup();
        group.setName(crowdGroup);
        restTemplate.postForLocation(crowdRestService + TEMPLATE_GROUP, group,
            name);
      }
    }
    catch (RuntimeException ce)
    {
      if (ce.getCause() instanceof ConnectException)
      {
        throw new GeneralConferenceException("Unable to Connect to IDP Backend");
      }
      throw new GeneralConferenceException(
          "Internal Error while connection to IDP Backend");
    }
  }

  @Override
  public void setPassword(String name, String password)
  {
    CrowdPassword passwordo = new CrowdPassword();
    passwordo.setValue(password);
    restTemplate.put(crowdRestService + TEMPLATE_PASSWORD, passwordo, name);
  }

}
