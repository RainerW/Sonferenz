package de.bitnoise.sonferenz.web.v2.page.root;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import wicket.contrib.tinymce.TinyMceBehavior;
import wicket.contrib.tinymce.settings.TinyMCESettings;
import wicket.contrib.tinymce.settings.TinyMCESettings.Align;
import wicket.contrib.tinymce.settings.TinyMCESettings.Location;
import wicket.contrib.tinymce.settings.TinyMCESettings.Theme;

import com.visural.wicket.aturl.At;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.Right;
import de.bitnoise.sonferenz.service.v2.services.StaticContentService;
import de.bitnoise.sonferenz.web.pages.StaticContentEditPage;

@At(url = "/editnode", urlParameters =
  { "id" })
public abstract class StaticEditPage extends BasePage
{
  String key;

  @SpringBean
  StaticContentService content;

  IModel<String> _html;

  public StaticEditPage(PageParameters params)
  {
    super();
    String id = params.getString("id");
    key = "nodes." + id;
  }

  public StaticEditPage(String id)
  {
    key = id;
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();

    // create
    _html = Model.of("");

    Form<String> form = new Form<String>("form")
    {
      @Override
      protected void onSubmit()
      {
        clickedOnSave();
      }
    };

    TextArea<String> field = new TextArea<String>("content", _html);
    TinyMCESettings settings = new TinyMCESettings(Theme.advanced);
    settings.setToolbarAlign(Align.left);
    settings.setStatusbarLocation(Location.top);
    settings.setRelativeUrls(false);
    TinyMceBehavior tinyMceBehavior = new TinyMceBehavior(settings);
    field.add(tinyMceBehavior);

    form.add(field);
    form.add(new Button("submit"));
    add(form);
  }

  protected void clickedOnSave()
  {
    String neu = _html.getObject();
    if (neu != null)
    {
      content.saveText(key, neu);
    }
    onSuccess();
  }

  abstract protected void onSuccess();

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

  }
}
