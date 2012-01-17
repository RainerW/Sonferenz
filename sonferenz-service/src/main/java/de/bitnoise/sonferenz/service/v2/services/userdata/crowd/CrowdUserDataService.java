package de.bitnoise.sonferenz.service.v2.services.userdata.crowd;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import de.bitnoise.sonferenz.service.v2.services.userdata.UserDataService;
import de.bitnoise.sonferenz.service.v2.services.userdata.beans.UserData;

public class CrowdUserDataService implements UserDataService
{

  private static final String TEMPLATE_USER = "http://crowd-backup.xxx.net/crowd/rest/usermanagement/1/user?username={username}";

  private String apiUsername = "xxx";

  private String apiPassword = "xxx";

  private RestTemplate restTemplate;

  public void init()
  {
    HttpClient client = new HttpClient();
    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(apiUsername, apiPassword);
    client.getState().setCredentials(AuthScope.ANY, credentials);
    CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(client);
    restTemplate = new RestTemplate(commons);
  }

  @Override
  public void setUserData(UserData userData)
  {
    // TODO: implement!
  }

  @Override
  public UserData getUserData(String userId)
  {
    CrowdUser result = restTemplate.getForObject(TEMPLATE_USER, CrowdUser.class, userId);
    return mapUserData(result);
  }

  private UserData mapUserData(CrowdUser user)
  {
    UserData result = new UserData();
    result.setDisplayName(user.getDisplayname());
    result.setEmail(user.getEmail());
    result.setFirstName(user.getFirstname());
    result.setId(user.getName());
    result.setLastName(user.getLastname());
    return result;
  }

}
