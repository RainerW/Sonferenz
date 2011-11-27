package de.bitnoise.sonferenz.web.pages.error;

import org.apache.wicket.markup.html.panel.Panel;

import de.bitnoise.sonferenz.web.action.IWebAction;
import de.bitnoise.sonferenz.web.pages.KonferenzPage;
import de.bitnoise.sonferenz.web.pages.UnauthorizedPanel;
import de.bitnoise.sonferenz.web.pages.auth.LoginPage;

public class UnauthorisedAccess extends KonferenzPage
{

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    setRedirect(true);
    setResponsePage(LoginPage.class);
  }

  @Override
  protected Panel getPageContent(String id)
  {
    return new UnauthorizedPanel(id);
  }
}
