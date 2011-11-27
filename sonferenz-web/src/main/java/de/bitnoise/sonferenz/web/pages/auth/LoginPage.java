package de.bitnoise.sonferenz.web.pages.auth;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.visural.wicket.aturl.At;

import de.bitnoise.sonferenz.web.action.IWebAction;
import de.bitnoise.sonferenz.web.pages.KonferenzPage;

@At(url = "/login")
public class LoginPage extends KonferenzPage
{

  private IWebAction<IModel<?>> _action;
  private IModel<?> _model;

  public LoginPage()
  {
  }

  public <T>LoginPage(IWebAction<IModel<?>> action, IModel<?> model)
  {
    _action = action;
    _model = model;
  }

  @Override
  protected Panel getPageContent(String id)
  {
    return new LoginPanel(id) {
      @Override
      protected void OnSuccessfullLogin()
      {
        super.OnSuccessfullLogin();
        if (_action == null)
        {
          super.OnSuccessfullLogin();
        }
        else
        {
          setRedirect(true);
          setResponsePage(_action.doAction(_model));
        }
      }
    };
  }
}
