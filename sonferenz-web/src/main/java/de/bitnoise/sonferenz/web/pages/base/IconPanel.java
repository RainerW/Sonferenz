package de.bitnoise.sonferenz.web.pages.base;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class IconPanel extends Panel
{
  final ResourceReference refEdit = new ResourceReference(
      "/images/sun_on.gif");

  final ResourceReference refDelete = new ResourceReference(
      "/images/sun_off.gif");

  public enum Type
  {
    EDIT, DELETE
  }

  public IconPanel(String id, Type type)
  {
    super(id);
    AjaxFallbackLink<String> link = new AjaxFallbackLink<String>("linkElement")
    {
      @Override
      public void onClick(AjaxRequestTarget target)
      {
        IconPanel.this.onClick(target);
      }
    };
    ResourceReference ref = refDelete;
    if (type.equals(Type.EDIT))
    {
      ref = refEdit;
    }
    Image img = new Image("imgElement", ref);
    add(link);
    link.add(img);
  }

  abstract protected void onClick(AjaxRequestTarget target);

}
