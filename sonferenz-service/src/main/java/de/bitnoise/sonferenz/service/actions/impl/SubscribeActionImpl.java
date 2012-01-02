package de.bitnoise.sonferenz.service.actions.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import de.bitnoise.sonferenz.model.ActionModel;
import de.bitnoise.sonferenz.model.AuthMapping;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.model.UserRoles;
import de.bitnoise.sonferenz.repo.ActionRepository;
import de.bitnoise.sonferenz.repo.AuthmappingRepository;
import de.bitnoise.sonferenz.repo.UserRepository;
import de.bitnoise.sonferenz.service.actions.ActionCreateUser;
import de.bitnoise.sonferenz.service.actions.ActionData;
import de.bitnoise.sonferenz.service.actions.Aktion;
import de.bitnoise.sonferenz.service.actions.KonferenzAction;
import de.bitnoise.sonferenz.service.v2.exceptions.ValidationException;
import de.bitnoise.sonferenz.service.v2.services.ActionService;
import de.bitnoise.sonferenz.service.v2.services.AuthenticationService;
import de.bitnoise.sonferenz.service.v2.services.ConfigurationService;
import de.bitnoise.sonferenz.service.v2.services.MailService;
import de.bitnoise.sonferenz.service.v2.services.StaticContentService;
import de.bitnoise.sonferenz.service.v2.services.UserService;

@Service
public class SubscribeActionImpl implements KonferenzAction
{

  SimpleMailMessage template;

  @Autowired
  AuthenticationService authService;

  @Autowired
  VerifyMailActionImpl actionVerify;

  @Autowired
  ConfigurationService config;

  private String baseUrl;

  @Override
  public String getActionName()
  {
    return "subscribe";
  }

  @Override
  public boolean execute(ActionData data)
  {
    if (data instanceof ActionCreateUser)
    {
      doExecute((ActionCreateUser) data);
      return true;
    }
    return false;
  }

  @PostConstruct
  public void initMail()
  {
    template = new SimpleMailMessage();
    template.setSubject(
        texte.text("action.subscribe.mail.subject",
            "Details for your new user account"));
    baseUrl = config.getStringValue("baseUrl");
  }

  @Autowired
  ActionService actionService;

  public void createNewUserToken(String user, String mail)
  {
    UserModel foundMail = userRepo.findByEmail(mail);
    if (foundMail != null)
    {
      throw new ValidationException("eMail allready inuse");
    }
    UserModel foundName = userRepo.findByName(user);
    if (foundName != null)
    {
      throw new ValidationException("Username allready inuse");
    }
    AuthMapping foundLogin = authRepo.findByAuthIdAndAuthType(user, "plainDB");
    if (foundLogin != null)
    {
      throw new ValidationException("Login Name allready inuse");
    }

    ActionCreateUser newUser = new ActionCreateUser();
    newUser.setLoginName(user);
    newUser.setMail(mail);

    ActionResult result = actionService.createAction(this, newUser);
    if (result.wasSuccessfull())
    {
      SimpleMailMessage message = new SimpleMailMessage(template);
      message.setTo(mail);
      StringBuffer body = new StringBuffer();
      body.append("You have been invited ... ");
      body.append("\r\n");
      body.append(ActionResult.ACTION_URL);
      message.setText(body.toString());
      mailer.sendMessage(result.getContentReplacer(), message);
    }
  }

  @Autowired
  StaticContentService texte;

  @Autowired
  UserRepository userRepo;

  @Autowired
  AuthmappingRepository authRepo;

  @Autowired
  UserService userService;

  @Autowired
  ActionRepository repo;

  @Autowired
  MailService mailer;

  void doExecute(ActionCreateUser data)
  {
    UserModel foundMail = userRepo.findByEmail(data.getEMail());
    if (foundMail != null)
    {
      throw new ValidationException("eMail allready inuse");
    }
    UserModel foundName = userRepo.findByName(data.getUserName());
    if (foundName != null)
    {
      throw new ValidationException("Username allready inuse");
    }
    AuthMapping foundLogin = authRepo.findByAuthIdAndAuthType(
        data.getLoginName(), "plainDB");
    if (foundLogin != null)
    {
      throw new ValidationException("Login Name allready inuse");
    }

    Collection<UserRoles> newRoles = new ArrayList<UserRoles>();
    newRoles.add(UserRoles.USER);
    UserModel user = userService.createNewLocalUser(data.getLoginName(),
        data.getPassword(), data.getEMail(), newRoles);

    actionVerify.createAction(user, data.getEMail());
  }

  public String createToken()
  {
    return UUID.randomUUID().toString();
  }

  @Override
  public Class[] getModelClasses()
  {
    return new Class[] {ActionCreateUser.class};
  }
}
