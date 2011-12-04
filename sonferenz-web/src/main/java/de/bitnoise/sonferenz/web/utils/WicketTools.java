package de.bitnoise.sonferenz.web.utils;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;

public class WicketTools
{

  public static void addCssClass(Component component, String cssClass)
  {
    component.add(new AttributeAppender("class", Model.of(cssClass), " "));
  }

}
