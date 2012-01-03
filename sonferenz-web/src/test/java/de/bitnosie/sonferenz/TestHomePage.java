package de.bitnosie.sonferenz;

import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.injection.ComponentInjector;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import de.bitnoise.sonferenz.WicketApplication;
import de.bitnoise.sonferenz.web.pages.HomePage;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
  private WicketTester tester;

  @Before
  public void setUp()
  {
    tester = new WicketTester(new WicketApplication());
  }

  //@Test
  public void homepageRendersSuccessfully()
  {
    // start and render the test page
    tester.startPage(HomePage.class);

    // assert rendered page class
    tester.assertRenderedPage(HomePage.class);
  }
}
