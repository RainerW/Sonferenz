package de.bitnoise.sonferenz;

import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.resource.loader.IStringResourceLoader;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.bitnoise.sonferenz.service.v2.services.StaticContentService;

public class DatabaseLoader implements IStringResourceLoader
{

  public DatabaseLoader()
  {
    InjectorHolder.getInjector().inject(this);
  }

  @SpringBean
  StaticContentService content2;

  public String loadStringResource(Class<?> clazz, String key, Locale locale,
      String style)
  {
    // String result = content2.text("res." + key);
    // return result;
    return readKey(key);
  }

  public String loadStringResource(Component component, String key)
  {
    return readKey(key);
  }

  private String readKey(String key)
  {
    String result = content2.text(key);
    if (result == null)
    {
      content2.storeText(key, null);
      return key;
    }
    return result;

  }

}
