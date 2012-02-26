package de.bitnoise.sonferenz.web.v2.panels.navigation;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.bitnoise.sonferenz.service.v2.services.StaticContentService;
import de.bitnoise.sonferenz.web.v2.page.root.StaticPage;
import de.bitnoise.sonferenz.web.v2.panels.general.BookmarkableLinkPanel;
import de.bitnoise.sonferenz.web.v2.panels.general.BookmarkableLinkPanel.Parameter;

public class MenuNavigationPanel extends Panel
{
  private static final long serialVersionUID = 100L;
  List<Panel> _texte = new ArrayList<Panel>();

  @SpringBean
  StaticContentService content;

  public MenuNavigationPanel(String id)
  {
    super(id);
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    // create

    // add
    add(new ListView<Panel>("list", _texte) {
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
    // TODO : Get List of Menu Items from a Service
    _texte.clear();
    List<StaticItem> pages = new ArrayList<StaticItem>();
    pages.add(new StaticItem("Info","info"));
    pages.add(new StaticItem("Details","details"));
    pages.add(new StaticItem("About","about"));
    for (StaticItem page : pages)
    {
      String linkText = page.getLinkName();
      String linkTarget = page.getLinkTarget();
      _texte.add(new BookmarkableLinkPanel("listItem", StaticPage.class, linkText,Parameter.create("id",linkTarget)));
    }
  }

}
