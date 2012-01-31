package de.bitnoise.sonferenz.service.v2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class CrowdTest
{

  @Test
  public void test()
  {
    DefaultClientConfig cc=new DefaultClientConfig();
    Client client = Client.create(cc);
    client .addFilter(new HTTPBasicAuthFilter("weinhold", "password")); 
    WebResource res = client.resource("http://crowd.seitenbau.net/crowd");
    
    String result = res.get(String.class);
    System.out.println(result);
  }

}
