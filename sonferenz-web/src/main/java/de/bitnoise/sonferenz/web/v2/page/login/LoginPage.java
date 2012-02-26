package de.bitnoise.sonferenz.web.v2.page.login;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import de.bitnoise.sonferenz.web.component.FocusOnLoadBehavior;
import de.bitnoise.sonferenz.web.v2.page.root.BasePage;

public class LoginPage extends BasePage
{
  FeedbackPanel feedback;
  final Model<String> modelUsername = new Model<String>();

  final Model<String> modelPassword = new Model<String>();

  @Override
  protected void onInitialize()
  {
    super.onInitialize();

    @SuppressWarnings("serial")
    Form<String> form = new Form<String>("form")
    {
      @Override
      protected void onSubmit()
      {
        loginUser();
      }
    };
    form.add(new TextField<String>("username", modelUsername)
        .add(new FocusOnLoadBehavior()));
    form.add(new PasswordTextField("password", modelPassword));
    form.add(new Button("submit"));

    feedback = new FeedbackPanel("feedback");
    add(feedback.setOutputMarkupId(true));
    add(form);
  }

  protected void loginUser()
  {
    String username = modelUsername.getObject();
    String password = modelPassword.getObject();
    String errorMessage = getKonferenzSession().authenticate(username,
        password);
    if (errorMessage == null)
    {
      OnSuccessfullLogin();
    }
    else
    {
      feedback.error("Login failed : " + errorMessage);
    }
  }

  protected void OnSuccessfullLogin()
  {
    setResponsePage(BasePage.class);
    setRedirect(true);
  }
}
