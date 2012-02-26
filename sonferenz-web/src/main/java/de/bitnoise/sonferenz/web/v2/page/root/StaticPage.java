package de.bitnoise.sonferenz.web.v2.page.root;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.visural.wicket.aturl.At;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.Right;
import de.bitnoise.sonferenz.service.v2.services.StaticContentService;
import de.bitnoise.sonferenz.web.pages.StaticContentEditPage;

@At(url = "/node", urlParameters =
  { "id" })
public class StaticPage extends BasePage
{
  String key;

  @SpringBean
  StaticContentService content;

  AjaxFallbackLink editLink;

  IModel<String> _html;

  public StaticPage(PageParameters params)
  {
    super();
    String id = params.getString("id");
    key = "nodes." + id;
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();

    // create
    _html = Model.of("");

    PageParameters parameters = new PageParameters();
    parameters.add("editId", key);
    editLink = new AjaxFallbackLink("editLink") {
      @Override
      public void onClick(AjaxRequestTarget target)
      {
        setResponsePage(new StaticEditPage(key) {
          @Override
          protected void onSuccess()
          {
            setResponsePage(StaticPage.this);
          }
        });
      }
    };
    // add
    add(new Label("text", _html).setEscapeModelStrings(false));
    add(editLink);
  }

  @Override
  protected void onConfigure()
  {
    super.onConfigure();

    String html = content.text(key);
    if (html == null)
    {
      html = "<!-- Empty Page for key = " + key + " -->";
    }
    _html.setObject(html);

    boolean vis = KonferenzSession.hasRight(Right.Conference.Edit);
    editLink.setVisible(vis);
  }
}
