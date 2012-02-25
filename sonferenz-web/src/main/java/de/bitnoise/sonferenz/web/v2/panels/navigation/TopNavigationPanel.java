package de.bitnoise.sonferenz.web.v2.panels.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.panel.Panel;

import de.bitnoise.sonferenz.web.v2.panels.authinfo.AuthinfoPanel;

public class TopNavigationPanel extends Panel
{
  private static final long serialVersionUID = 100L;
  private Panel ai;

  public TopNavigationPanel(String id)
  {
    super(id);
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    
    List<Panel> panels = new ArrayList<Panel>();
    panels.add(new AuthinfoPanel("panel"));
    ai=new AuthinfoPanel("panel");
    panels.add(ai);
    panels.add(new AuthinfoPanel("panel"));
    final Iterator<Panel> iter = panels.iterator();
    Loop loop = new Loop("list",panels.size()) {
      @Override
      protected void populateItem(LoopItem item)
      {
        item.add(iter.next());
      }
    };
    add(loop);
    ai.setVisible(false);
  }
}
