package de.bitnoise.sonferenz.web.pages.config;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.ConfigurationModel;
import de.bitnoise.sonferenz.web.component.TableBuilder;
import de.bitnoise.sonferenz.web.component.TableBuilder.ActionColumn;
import de.bitnoise.sonferenz.web.pages.base.AbstractListPanel;
import de.bitnoise.sonferenz.web.pages.base.IconPanel;
import de.bitnoise.sonferenz.web.pages.base.IconPanel.Type;

class ConfigViewModel implements Serializable
{
  Integer id;

  String name;

  String value;
}

public class ListConfigPanel extends
    AbstractListPanel<ConfigViewModel, ConfigurationModel>
{

  public ListConfigPanel(String id)
  {
    super(id, "GlobalConfig");
  }

  @SpringBean
  UiFacade facade;

  @Override
  protected void initColumns(TableBuilder<ConfigViewModel> builder)
  {
    builder.addActions("actions", new Edit(), new Delete());
    builder.addColumn("name");
    builder.addColumn("value");
  }

  class Edit extends ActionColumn<ConfigViewModel>
  {
    @Override
    public Component populate(String id, final ConfigViewModel row)
    {
      return new IconPanel(id, Type.EDIT)
      {
        @Override
        protected void onClick(AjaxRequestTarget target)
        {
          setResponsePage(new EditConfigEntry(row.id));
        }
      };
    }
  }

  class Delete extends ActionColumn<ConfigViewModel>
  {
    @Override
    public Component populate(String id, ConfigViewModel row)
    {
      return new IconPanel(id, Type.DELETE)
      {
        @Override
        protected void onClick(AjaxRequestTarget target)
        {
          System.out.println("Delete ....");
        }
      };
    }
  }

  @Override
  protected ConfigViewModel transferDbToViewModel(ConfigurationModel dbObject)
  {
    ConfigViewModel view = new ConfigViewModel();
    view.name = dbObject.getName();
    view.value = dbObject.getValueString();
    view.id = dbObject.getId();
    return view;
  }

  @Override
  protected Page<ConfigurationModel> getItems(PageRequest request)
  {
    return facade.getAllConfigurations(request);
  }

}
