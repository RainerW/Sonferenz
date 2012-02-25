package de.bitnoise.sonferenz.web.v2.page.root;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import de.bitnoise.sonferenz.web.pages.StaticContentPanel;
import de.bitnoise.sonferenz.web.v2.panels.navigation.MenuNavigationPanel;
import de.bitnoise.sonferenz.web.v2.panels.navigation.NavigationPanel;
import de.bitnoise.sonferenz.web.v2.panels.navigation.TopNavigationPanel;

public class KonferenzPage extends BasePage
{
  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    add(new NavigationPanel("Navigation"));
    add(createContentPanel("Content"));
  }

  protected Component createContentPanel(String id)
  {
    return new StaticContentPanel(id, "page.default");
  }
}
