package de.bitnoise.sonferenz.web.pages.users.action;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.Right;
import de.bitnoise.sonferenz.web.action.WebMenuAction;
import de.bitnoise.sonferenz.web.pages.users.UserOverviewPage;

public class CreateNewUser extends WebMenuAction<IModel<Object>>
{

  public CreateNewUser( )
  {
    super("Add", UserOverviewPage.State.NEW);
  }

  public Page doAction(IModel<Object> nix)
  {
    UserOverviewPage userOverviewPage = new UserOverviewPage();
    userOverviewPage.createNewUser();
    return userOverviewPage;
  }

  @Override
  public boolean isVisible()
  {
    return KonferenzSession.hasRight(Right.User.Create);
  }
}
