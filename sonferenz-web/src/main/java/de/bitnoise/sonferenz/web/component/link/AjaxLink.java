package de.bitnoise.sonferenz.web.component.link;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class AjaxLink extends Panel
{

  String text;

  public AjaxLink(String id, String labelText)
  {
    super(id);
    text = labelText;
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();

    AjaxFallbackLink<String> link = new AjaxFallbackLink<String>("link")
    {
      @Override
      public void onClick(AjaxRequestTarget target)
      {
        onClickLink(target);
      }
    };
    Component label = createLabel("text", text);
    link.add(label);
    add(link);
  }

  private Component createLabel(String id, String text)
  {
    return new Label(id, text);
  }

  abstract protected void onClickLink(AjaxRequestTarget target);
}
