package de.bitnoise.sonferenz.web.v2.panels.general;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class BookmarkableLinkPanel extends Panel
{
  private static final long serialVersionUID = 100L;
  Class<? extends WebPage> _linkTarget;
  String _linkText;
  PageParameters _parameters;

  public BookmarkableLinkPanel(String id, Class<? extends WebPage> linkTarget, String linkText)
  {
    super(id);
    _linkTarget = linkTarget;
    _linkText = linkText;
    _parameters = null;
  }

  public BookmarkableLinkPanel(String id, Class<? extends WebPage> linkTarget, String linkText, Parameter parameters)
  {
    super(id);
    _linkTarget = linkTarget;
    _linkText = linkText;
    if (parameters != null)
    {
      _parameters = parameters.toPageParameters();
    }
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();

    // create
    BookmarkablePageLink<WebPage> _target = new BookmarkablePageLink<WebPage>("linkTarget", _linkTarget,
        _parameters);
    Label _label = new Label("linkText", _linkText);

    // add
    add(_target);
    _target.add(_label);
  }

  public static class Parameter
  {
    PageParameters pp = new PageParameters();

    public static Parameter create(String key, String value)
    {
      Parameter param = new Parameter();
      param.param(key, value);
      return param;
    }

    public PageParameters toPageParameters()
    {
      return pp;
    }

    public void param(String key, String value)
    {
      pp.add(key, value);
    }

  }
}
