package de.bitnoise.sonferenz.web.v2.panels.general;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class BookmarkableLinkPanel extends Panel
{
  private static final long serialVersionUID = 100L;
  Class<? extends WebPage> _linkTarget;
  String _linkText;

  public BookmarkableLinkPanel(String id, Class<? extends WebPage> linkTarget, String linkText)
  {
    super(id);
    _linkTarget = linkTarget;
    _linkText = linkText;
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    
    // create
    BookmarkablePageLink<WebPage> _target = new BookmarkablePageLink<WebPage>("linkTarget", _linkTarget);
    Label _label = new Label("linkText", _linkText);

    // add
    add(_target);
    _target.add(_label);
  }

}
