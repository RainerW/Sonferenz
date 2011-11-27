package de.bitnoise.sonferenz.web.pages.conference;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.web.component.SortableServiceDataProvider;
import de.bitnoise.sonferenz.web.component.TableBuilder;
import de.bitnoise.sonferenz.web.pages.conference.action.EditConference;
import de.bitnoise.sonferenz.web.pages.conference.table.ConferenceListItem;

public class ListConferencesPanel extends Panel
{
  @SpringBean
  private UiFacade facade;

  public ListConferencesPanel(String id)
  {
    super(id);
    InjectorHolder.getInjector().inject(this);
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    TableBuilder<ConferenceListItem> builder = new TableBuilder<ConferenceListItem>("conference") {
      {
        addColumn(new Column() {
          {
            setTitle("Aktiv");
            setModelProperty("aktiv");
          }
        });
        addColumn(new Column() {
          {
            setTitle("Status");
            setModelProperty("state");
            sortable();
          }
        });
        addColumn(new Column() {
          {
            setTitle("Name");
            setModelProperty("shortName");
            sortable();
            action(new EditConference());
          }
        });
      }
    };

    ISortableDataProvider<ConferenceListItem> provider = 
        new SortableServiceDataProvider<ConferenceModel, ConferenceListItem>() {
      @Override
      protected ConferenceListItem transferType(ConferenceModel dbObject)
      {
        ConferenceListItem item = new ConferenceListItem();
        item.shortName = dbObject.getShortName();
        item.state = dbObject.getState();
        item.aktiv = dbObject.getActive();
        item.conference = dbObject;
        return item;
      }

      @Override
      protected Page<ConferenceModel> getAllItems(PageRequest request)
      {
        return facade.getConferences(request);
      }

      @Override
      public int size()
      {
        return facade.getAllConferencesCount();
      }
    };
    DefaultDataTable<ConferenceListItem> table = new DefaultDataTable<ConferenceListItem>(
        "conferenceTable", builder.getColumns(), provider, 20);

    add(table);
  }
}
