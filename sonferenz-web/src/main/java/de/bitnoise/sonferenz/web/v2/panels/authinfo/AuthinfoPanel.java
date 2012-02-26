package de.bitnoise.sonferenz.web.v2.panels.authinfo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.web.pages.auth.LoginPage;
import de.bitnoise.sonferenz.web.pages.auth.LogoutPage;

public class AuthinfoPanel extends Panel
{
  private static final long serialVersionUID = 100L;
  IModel<String> _username;
  BookmarkablePageLink<WebPage> _logout;
  BookmarkablePageLink<WebPage> _login;

  public AuthinfoPanel(String id)
  {
    super(id);
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    _username = Model.of("");
    Label username = new Label("username", _username);
    _logout = new BookmarkablePageLink<WebPage>("logout", LogoutPage.class);
    _login = new BookmarkablePageLink<WebPage>("login", de.bitnoise.sonferenz.web.v2.page.login.LoginPage.class);

    add(username);
    add(_login);
    add(_logout);
  }

  @Override
  protected void onConfigure()
  {
    super.onConfigure();

    if (KonferenzSession.noUserLoggedIn())
    {
      _logout.setVisible(false);
      _login.setVisible(true);
    }
    else
    {
      _username.setObject(KonferenzSession.get().getCurrentUser().getName());
      _logout.setVisible(true);
      _login.setVisible(false);
    }
  }

}
