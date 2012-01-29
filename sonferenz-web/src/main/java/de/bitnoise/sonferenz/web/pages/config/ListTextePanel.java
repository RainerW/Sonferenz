package de.bitnoise.sonferenz.web.pages.config;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.StaticContentModel;
import de.bitnoise.sonferenz.web.component.TableBuilder;
import de.bitnoise.sonferenz.web.pages.base.AbstractListPanel;

class TexteViewModel implements Serializable
{
  String name;

  String value;
}

public class ListTextePanel extends AbstractListPanel<TexteViewModel, StaticContentModel>
{
  @SpringBean
  UiFacade facade;

  public ListTextePanel(String id)
  {
    super(id, "TexteTable");
  }

  @Override
  protected void initColumns(TableBuilder<TexteViewModel> builder)
  {
    builder.addColumn("name");
    builder.addColumn("value");
  }

  @Override
  protected TexteViewModel transferDbToViewModel(StaticContentModel dbObject)
  {
    TexteViewModel view = new TexteViewModel();
    view.name = dbObject.getName();
    view.value = dbObject.getHtml();
    if (view.value != null && view.value.length() > 100)
    {
      view.value = view.value.substring(0, 100);
    }
    return view;
  }

  @Override
  protected Page<StaticContentModel> getItems(PageRequest request)
  {
    return facade.getTexte(request);
  }

}
