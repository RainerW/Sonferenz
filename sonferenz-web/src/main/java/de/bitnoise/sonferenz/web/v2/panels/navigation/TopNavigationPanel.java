package de.bitnoise.sonferenz.web.v2.panels.navigation;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.web.pages.HomePage;
import de.bitnoise.sonferenz.web.pages.profile.MyProfilePage;
import de.bitnoise.sonferenz.web.v2.panels.authinfo.AuthinfoPanel;
import de.bitnoise.sonferenz.web.v2.panels.general.BookmarkableLinkPanel;

public class TopNavigationPanel extends Panel
{
  private static final long serialVersionUID = 100L;

  public TopNavigationPanel(String id)
  {
    super(id);
  }

  List<Panel> _texte = new ArrayList<Panel>();
  BookmarkableLinkPanel _profileLink;

  @Override
  protected void onInitialize()
  {
    super.onInitialize();

    // create
    _profileLink = new BookmarkableLinkPanel("listItem", MyProfilePage.class, "profile");

    _texte.add(new BookmarkableLinkPanel("listItem", HomePage.class, "home"));
    _texte.add(_profileLink);
    _texte.add(new AuthinfoPanel("listItem"));

    // add
    add(new ListView<Panel>("list", _texte) {
      private static final long serialVersionUID = 1L;

      @Override
      protected void populateItem(ListItem<Panel> item)
      {
        Panel panel = item.getModelObject();
        item.add(panel);
        item.setVisible(panel.isVisible());
      }
    });

  }

  @Override
  protected void onConfigure()
  {
    super.onConfigure();
    _profileLink.setVisible(!KonferenzSession.noUserLoggedIn());
  }
}
