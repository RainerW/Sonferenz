package de.bitnoise.sonferenz;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.injection.ConfigurableInjector;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.AnnotSpringInjector;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;

import com.jquery.JQueryResourceReference;
import com.visural.wicket.aturl.AtAnnotation;
import com.visural.wicket.util.lesscss.LessCSSResourceStreamLocator;

import de.bitnoise.sonferenz.web.pages.HomePage;
import de.bitnoise.sonferenz.web.pages.error.UnauthorisedAccess;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see de.bitnoise.sonferenz.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  @Override
  public Class<HomePage> getHomePage()
  {
    return HomePage.class;
  }

  /**
   * @see org.apache.wicket.Application#init()
   */
  @Override
  public void init()
  {
    super.init();
    // Activate Spring
    addComponentInstantiationListener(new SpringComponentInjector(this));
    
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
    
    getResourceSettings().addStringResourceLoader(0,new DatabaseLoader());
    // Remap URLSs
    try
    {
      AtAnnotation.mount(this, "de.bitnoise.sonferenz.web.pages");
    }
    catch (ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Session newSession(Request request, Response response)
  {
    return new KonferenzSession(request);
  }
}
