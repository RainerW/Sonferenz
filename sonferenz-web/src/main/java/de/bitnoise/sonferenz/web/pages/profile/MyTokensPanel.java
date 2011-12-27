package de.bitnoise.sonferenz.web.pages.profile;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.ActionModel;
import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.service.actions.Aktion;
import de.bitnoise.sonferenz.web.component.SortableServiceDataProvider;
import de.bitnoise.sonferenz.web.component.TableBuilder;
import de.bitnoise.sonferenz.web.component.TableBuilder.Column;
import de.bitnoise.sonferenz.web.pages.HomePage;
import de.bitnoise.sonferenz.web.pages.talks.ModelTalkList;
import de.bitnoise.sonferenz.web.pages.talks.TalksOverviewPage;
import de.bitnoise.sonferenz.web.pages.talks.action.EditOrViewTalk;
import de.bitnoise.sonferenz.web.pages.users.FormPanel;
import de.bitnoise.sonferenz.web.pages.users.UserOverviewPage;

public class MyTokensPanel extends Panel
{
  @SpringBean
  UiFacade facade;

  UserModel user;

  public MyTokensPanel(String id, UserModel user)
  {
    super(id);
    this.user = user;
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();

    TableBuilder<TokenListItem> builder = new TableBuilder<TokenListItem>(
        "tokens")
    {
      {
        addColumn(new Column()
        {
          {
            setTitle("Aktion");
            setModelProperty("action");
            // sortable();
            // action(new EditOrViewTalk());
          }
        });
        addColumn(new Column()
        {
          {
            setTitle("Token");
            setModelProperty("token");
            // sortable();
            // action(new EditOrViewTalk());
          }
        });
        addColumn(new Column()
        {
          {
            setTitle("url");
            setModelProperty("url");
            // sortable();
            // action(new EditOrViewTalk());
          }
        });
      }
    };

    ISortableDataProvider<TokenListItem> provider =
        new SortableServiceDataProvider<ActionModel, TokenListItem>()
        {
          @Override
          protected TokenListItem transferType(ActionModel dbObject)
          {
            TokenListItem item = new TokenListItem();
            item.action = dbObject.getAction();
            item.token = dbObject.getToken();
            item.url = "/action/" + item.action + "/token/" + item.token;
            return item;
          }

          @Override
          protected Page<ActionModel> getAllItems(PageRequest request)
          {
            return facade.getUserActions(user);
          }

          @Override
          public int size()
          {
            Long l = facade.getUserActions(user).getTotalElements();
            return l.intValue();
          }

        };

    DefaultDataTable<TokenListItem> table = new DefaultDataTable<TokenListItem>(
        "tokenTable", builder.getColumns(), provider, 30);
    add(table);
  }

  protected void onSubmitForm()
  {
    setResponsePage(HomePage.class);
  }

}
