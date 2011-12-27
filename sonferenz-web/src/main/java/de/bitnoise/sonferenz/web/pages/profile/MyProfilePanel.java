package de.bitnoise.sonferenz.web.pages.profile;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.web.pages.HomePage;
import de.bitnoise.sonferenz.web.pages.talks.TalksOverviewPage;
import de.bitnoise.sonferenz.web.pages.users.FormPanel;
import de.bitnoise.sonferenz.web.pages.users.UserOverviewPage;

public class MyProfilePanel extends FormPanel
{
  @SpringBean
  UiFacade facade;

  TextField display;

  UserModel user;

  public MyProfilePanel(String id, UserModel user)
  {
    super(id);
    this.user = user;
  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    addFeedback(this, "feedback");
    display = new TextField<String>("display", Model.of(user.getName()));

    Form<String> form = new Form<String>("form")
    {
      @Override
      protected void onSubmit()
      {
        onSubmitForm();
      }
    };
    Button cancel = new Button("cancel")
    {
      public void onSubmit()
      {
        setResponsePage(HomePage.class);
      }
    };

    add(form);
    form.add(display);
    form.add(new TextField("login", Model.of(user.getProvider().getAuthId())));
    form.add(new Button("submit"));
    form.add(cancel);
  }

  protected void onSubmitForm()
  {
    setResponsePage(HomePage.class);
    String newName = display.getValue();
    if (user.getName().equals(newName))
    {
      return;
    }
    facade.userUpdate(user, newName);
  }

}
