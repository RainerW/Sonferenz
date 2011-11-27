package de.bitnoise.sonferenz.web.pages.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.visural.common.web.HtmlSanitizer;

import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.model.UserRole;
import de.bitnoise.sonferenz.model.UserRoles;

public class EditUserPanel extends FormPanel
{
  @SpringBean
  UiFacade facade;

  private UserModel _user;

  final Model<String> modelName = new Model<String>();

  final Model<String> modelEMail = new Model<String>();

  private MyModel modelRoles;

  public EditUserPanel(String id, UserModel user)
  {
    super(id);
    _user = user;

  }

  @Override
  protected void onInitialize()
  {
    super.onInitialize();
    InjectorHolder.getInjector().inject(this);
    modelName.setObject(_user.getName());
    modelEMail.setObject(_user.getEmail());
    addFeedback(this, "feedback");

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
        setResponsePage(UserOverviewPage.class);
      }
    };
    cancel.setDefaultFormProcessing(false);
    form.add(cancel);

    addTextInputField(form, "username", modelName, true);
    addTextInputField(form, "email", modelEMail, false);
    form.add(new Button("submit"));

    List<UserRoles> current = new ArrayList<UserRoles>();
    for (UserRole role : _user.getRoles())
    {
      current.add(UserRoles.of(role));
    }
    if (current.size() == 0)
    {
      current.add(UserRoles.NONE);
    }
    modelRoles = new MyModel(current);

    List<? extends UserRoles> choices = UserRoles.asList();
    ListMultipleChoice<UserRoles> roleSelect = new ListMultipleChoice<UserRoles>(
        "roleSelect", modelRoles, choices);
    form.add(roleSelect);

    // List<IColumn<UserRolesItem>> columns = new
    // ArrayList<IColumn<UserRolesItem>>();
    // columns.add(new PropertyColumn<UserRolesItem>(Model.of("name"),
    // "name"));
    // IDataProvider<UserRolesItem> dataProvider = new
    // UserRolesProvider(_user);
    //
    // form.add(new DataTable<UserRolesItem>("roleTable", columns
    // .toArray(new IColumn[0]), dataProvider, 1024));

    add(form);

  }

  class MyModel implements IModel<Collection<UserRoles>>
  {
    private Collection<UserRoles> _list;

    public MyModel(List<UserRoles> current)
    {
      _list = current;
    }

    public void detach()
    {
      _list = null;
    }

    public Collection<UserRoles> getObject()
    {
      return _list;
    }

    public void setObject(Collection<UserRoles> object)
    {
      _list = object;
    }
  }

  protected void onSubmitForm()
  {
    String valueName = HtmlSanitizer.sanitize(modelName.getObject());
    String valueEMail = modelEMail.getObject();
    if (valueEMail != null)
    {
      valueEMail = HtmlSanitizer.sanitize(valueEMail);
    }
    Collection<UserRoles> newRoles = modelRoles.getObject();
    _user.setName(valueName);
    _user.setEmail(valueEMail);
    facade.saveUser(_user,newRoles);
    // userService.saveUser(_user, valueName, newRoles);

    setResponsePage(UserOverviewPage.class);
  }
}
