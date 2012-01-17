package de.bitnoise.sonferenz.service.v2.services.userdata.crowd;

import org.junit.Before;
import org.junit.Test;

import de.bitnoise.sonferenz.service.v2.services.userdata.beans.UserData;

public class CrowdUserDataServiceTest
{

  CrowdUserDataService target ;
  
  @Before
  public void setUp() throws Exception
  {
    target = new CrowdUserDataService();
    target.init();
  }

  @Test
  public void test()
  {
   
    UserData text = target.getUserData("peter");
    System.out.println(text);
  }

}
