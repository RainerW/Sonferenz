package de.bitnoise.sonferenz.web.v2.page.root;

import de.bitnoise.sonferenz.web.v2.panels.footer.VersionPanel;
import de.bitnoise.sonferenz.web.v2.panels.navigation.MenuNavigationPanel;
import de.bitnoise.sonferenz.web.v2.panels.navigation.TopNavigationPanel;

public class BasePage extends EmptyPage
{
  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    add(new TopNavigationPanel("TopNavigation"));
    add(new MenuNavigationPanel("MenuNavigation"));
    add(new VersionPanel("Version"));
  }
}
