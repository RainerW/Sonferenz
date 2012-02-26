package de.bitnoise.sonferenz.web.v2.page.root;

import org.apache.wicket.Component;

import de.bitnoise.sonferenz.web.pages.StaticContentPanel;
import de.bitnoise.sonferenz.web.v2.panels.navigation.NavigationPanel;

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
