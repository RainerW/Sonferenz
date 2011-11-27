package de.bitnoise.sonferenz.web.pages.users.action;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

import de.bitnoise.sonferenz.web.action.WebAction;
import de.bitnoise.sonferenz.web.pages.users.UserOverviewPage;
import de.bitnoise.sonferenz.web.pages.users.table.UserListItem;

public class EditUser  extends WebAction<IModel<UserListItem>> 
{

  public Page doAction(IModel<UserListItem> model)
  {
    UserOverviewPage userOverviewPage = new UserOverviewPage();
    userOverviewPage.editUser(model.getObject());
    return userOverviewPage;
  }

}
