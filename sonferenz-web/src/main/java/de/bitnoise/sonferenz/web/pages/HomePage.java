package de.bitnoise.sonferenz.web.pages;

import org.apache.wicket.markup.html.panel.Panel;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.web.pages.home.NoUserLoggedInPanel;
import de.bitnoise.sonferenz.web.pages.home.UserLoggedInPanel;

public class HomePage extends KonferenzPage
{
  private static final long serialVersionUID = 1L;

  @Override
  protected Panel getPageContent(String id)
  {
    if (KonferenzSession.noUserLoggedIn())
    {
      return new NoUserLoggedInPanel(id);
    } else {
      return new UserLoggedInPanel(id);
    }
  }
}
