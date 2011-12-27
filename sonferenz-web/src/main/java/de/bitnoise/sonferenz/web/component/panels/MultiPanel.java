package de.bitnoise.sonferenz.web.component.panels;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

public abstract class MultiPanel extends Panel
{

  public MultiPanel(String id)
  {
    super(id);
  }
  
  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    RepeatingView view = new RepeatingView("repeater");
    onInitPanels(view);
    add(view);
  }

  abstract protected void onInitPanels(RepeatingView view);


}
