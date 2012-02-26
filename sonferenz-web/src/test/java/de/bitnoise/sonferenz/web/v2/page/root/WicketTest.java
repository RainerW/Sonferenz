package de.bitnoise.sonferenz.web.v2.page.root;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.bitnoise.sonferenz.WicketApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
  {
      "classpath:/config/security.xml",
      "classpath:/config/spring-context.xml",
      "classpath:/config/test-context.xml"
})
public class WicketTest
{
  protected WicketTester tester;

  @Autowired
  ApplicationContext ctx;
  
  @Before
  public void setUp()
  {
    WicketApplication app = new WicketApplication(){
      @Override
      public void activateSpring()
      {
        setApplicationContext(ctx);
      }
    };
    tester = new WicketTester(app);
  }
}
