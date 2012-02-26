package de.bitnoise.sonferenz;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.jquery.JQueryResourceReference;
import com.visural.wicket.aturl.AtAnnotation;
import com.visural.wicket.util.lesscss.LessCSSResourceStreamLocator;

import de.bitnoise.sonferenz.service.v2.events.ConfigReload;
import de.bitnoise.sonferenz.service.v2.services.Eventing;
import de.bitnoise.sonferenz.web.pages.HomePage;
import de.bitnoise.sonferenz.web.pages.error.UnauthorisedAccess;
import de.bitnoise.sonferenz.web.v2.page.root.BasePage;
import de.bitnoise.sonferenz.web.v2.page.root.KonferenzPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see de.bitnoise.sonferenz.Start#main(String[])
 */
public class WicketApplication extends WebApplication implements ApplicationContextAware
{
  private SpringComponentInjector injector;

  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  @Override
  public Class<KonferenzPage> getHomePage()
  {
    // return HomePage.class;
    return KonferenzPage.class;
  }

  /**
   * @see org.apache.wicket.Application#init()
   */
  @Override
  public void init()
  {
    super.init();
    activateSpring();

    // for Selenium debugging
    getDebugSettings().setOutputComponentPath(true);

    // Activate lesscss
    getResourceSettings().setResourceStreamLocator(
        new LessCSSResourceStreamLocator(getResourceFinder()));

    // Visural JS stuff
    addRenderHeadListener(JavascriptPackageResource
        .getHeaderContribution(new JQueryResourceReference()));

    getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
    getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

    // Error pages
    getApplicationSettings().setAccessDeniedPage(UnauthorisedAccess.class);
    getApplicationSettings().setInternalErrorPage(
        de.bitnoise.sonferenz.web.pages.error.InternalError.class);
    getApplicationSettings().setPageExpiredErrorPage(UnauthorisedAccess.class);

    getResourceSettings().addStringResourceLoader(0, new DatabaseResourceLoader());
    // Remap URLSs
    try
    {
      AtAnnotation.mount(this, "de.bitnoise.sonferenz.web.pages");
      AtAnnotation.mount(this, "de.bitnoise.sonferenz.web.v2.page");
    }
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }

  }

  public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException
  {
    injector = new SpringComponentInjector(this, applicationContext,true);
    addComponentInstantiationListener(injector);
  }

  public void activateSpring()
  {
    // Activate Spring
    injector = new SpringComponentInjector(this);
    addComponentInstantiationListener(injector);
  }

  @Override
  public Session newSession(Request request, Response response)
  {
    return new KonferenzSession(request);
  }
}
