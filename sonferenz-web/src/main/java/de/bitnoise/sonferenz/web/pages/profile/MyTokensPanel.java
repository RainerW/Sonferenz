package de.bitnoise.sonferenz.web.pages.profile;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
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

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.Right;
import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.ActionModel;
import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.service.v2.actions.Aktion;
import de.bitnoise.sonferenz.web.component.SortableServiceDataProvider;
import de.bitnoise.sonferenz.web.component.TableBuilder;
import de.bitnoise.sonferenz.web.component.TableBuilder.Column;
import de.bitnoise.sonferenz.web.pages.HomePage;
import de.bitnoise.sonferenz.web.pages.base.AbstractListPanel;
import de.bitnoise.sonferenz.web.pages.profile.action.TokenCreateUser;
import de.bitnoise.sonferenz.web.pages.talks.ModelTalkList;
import de.bitnoise.sonferenz.web.pages.talks.TalksOverviewPage;
import de.bitnoise.sonferenz.web.pages.talks.action.EditOrViewTalk;
import de.bitnoise.sonferenz.web.pages.users.FormPanel;
import de.bitnoise.sonferenz.web.pages.users.UserOverviewPage;
import de.bitnoise.sonferenz.web.pages.users.action.EditUser;
import de.bitnoise.sonferenz.web.toolbar.AddToolbarWithButton;

public class MyTokensPanel extends AbstractListPanel<TokenListItem, ActionModel>
{
  @SpringBean
  UiFacade facade;

  UserModel user;

  public MyTokensPanel(String id)
  {
    super(id, "tokenTable");
  }

  @Override
  protected void initColumns(TableBuilder<TokenListItem> builder)
  {
    builder.addColumn("action");
    builder.addColumn("token");
    builder.addColumn("url");
  }
  
  @Override
  protected void addToolbars(DataTable<TokenListItem> table,
      SortableServiceDataProvider<ActionModel, TokenListItem> provider)
  {
    AddToolbarWithButton invite = new AddToolbarWithButton("inviteUser", table, new TokenCreateUser());
    invite.setVisible(KonferenzSession.hasRight(Right.Actions.InviteUser));
    table.addTopToolbar(invite);
    super.addToolbars(table, provider);
  }

  protected void onSubmitForm()
  {
    setResponsePage(HomePage.class);
  }

  @Override
  protected TokenListItem transferDbToViewModel(ActionModel dbObject)
  {
    TokenListItem item = new TokenListItem();
    item.action = dbObject.getAction();
    item.token = dbObject.getToken();
    item.url = "/action/" + item.action + "/token/" + item.token;
    return item;
  }

  @Override
  protected Page<ActionModel> getItems(PageRequest request)
  {
    UserModel user = KonferenzSession.get().getCurrentUser();
    return facade.getUserActions(request, user);
  }

}
