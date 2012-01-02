package de.bitnoise.sonferenz.service.v2.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.jasypt.digest.StringDigester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;

import de.bitnoise.sonferenz.model.ActionModel;
import de.bitnoise.sonferenz.model.AuthMapping;
import de.bitnoise.sonferenz.model.LocalUserModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.model.UserRole;
import de.bitnoise.sonferenz.model.UserRoles;
import de.bitnoise.sonferenz.repo.ActionRepository;
import de.bitnoise.sonferenz.repo.AuthmappingRepository;
import de.bitnoise.sonferenz.repo.LocalUserRepository;
import de.bitnoise.sonferenz.repo.UserRepository;
import de.bitnoise.sonferenz.service.actions.ActionCreateUser;
import de.bitnoise.sonferenz.service.actions.ActionData;
import de.bitnoise.sonferenz.service.actions.Aktion;
import de.bitnoise.sonferenz.service.actions.IncrementUseCountOnToken;
import de.bitnoise.sonferenz.service.v2.exceptions.ValidationException;
import de.bitnoise.sonferenz.service.v2.services.ActionService;
import de.bitnoise.sonferenz.service.v2.services.ConfigurationService;
import de.bitnoise.sonferenz.service.v2.services.StaticContentService;
import de.bitnoise.sonferenz.service.v2.services.UserService;

@Service
public class ActionServiceImpl implements ActionService
{
  @Autowired
  ActionRepository repo;

  @Autowired
  UserService userService;

  @Autowired
  AuthmappingRepository authRepo;

  @Autowired
  UserRepository userRepo;

  @Autowired
  LocalUserRepository localUserRepo;

  @Autowired
  ConfigurationService config;

  @Autowired
  StaticContentService texte;

  MailSender sender;

  SimpleMailMessage template;

  String actionUrl;

  @PostConstruct
  public void initMail()
  {
    JavaMailSenderImpl tmp = new JavaMailSenderImpl();
    tmp.setHost(config.getStringValue("smtp.host"));
    if (config.isAvaiable("smtp.username"))
    {
      tmp.setUsername(config.getStringValue("smtp.username"));
      tmp.setPassword(config.getStringValue("smtp.password"));
    }

    String baseUrl = config.getStringValue("baseUrl");
    actionUrl = baseUrl + "/action";

    sender = tmp;

    template = new SimpleMailMessage();
    template.setFrom(config.getStringValue("mail.create.from"));
    template.setSubject(texte.text("mail.subject", "Your new useraccound"));
  }

  @Autowired
  StringDigester digester;

  @Override
  @Transactional(readOnly = true)
  public Aktion loadAction(String action, String token)
  {
    ActionModel row = repo.findByActionAndToken(action, token);
    Aktion aktion = toAktion(row);
    if (aktion == null)
    {
      return null;
    }
    if (!row.getAction().equals(action))
    {
      return null;
    }
    if (!row.getToken().equals(token))
    {
      return null;
    }
    return aktion;
  }

  public Aktion toAktion(ActionModel row)
  {
    if (row == null)
    {
      return null;
    }
    if (row.getExpiry() == null)
    {
      return null;
    }
    XStream xs = new XStream();
    ActionData data = (ActionData) xs.fromXML(row.getData());
    Aktion a = new Aktion(row.getId(), row.getAction(), row.getToken(), data);
    return a;
  }

  @Override
  public Page<ActionModel> getUserActions(PageRequest request, UserModel user)
  {
    return repo.findByCreator(user, request);
  }

  @Override
  @Transactional(rollbackFor = Throwable.class)
  public void execute(ActionData data)
  {
    if (data instanceof ActionCreateUser)
    {
      doExecute((ActionCreateUser) data);
      processActionTable(data);
    }

  }

  void processActionTable(ActionData data)
  {
    if (data instanceof IncrementUseCountOnToken)
    {
      List<Integer> tokens = ((IncrementUseCountOnToken) data)
          .getTokensToIncrementUserCount();
      if (tokens != null)
      {
        for (Integer id : tokens)
        {
          ActionModel token = repo.findOne(id);
          if (token.getUsed() == null)
          {
            token.setUsed(1);
          }
          else
          {
            token.setUsed(token.getUsed() + 1);
          }
          repo.save(token);
        }
      }
    }
  }

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

    newActionVerifyEMail(user);
  }

  void newActionVerifyEMail(UserModel user)
  {
    ActionModel entity = new ActionModel();
    entity.setAction("verifyMail");
    String token = createToken();
    entity.setToken(token);
    entity.setCreator(user);
    entity.setExpiry(new Date());
    entity.setUsed(0);
    entity.setData("<null/>");
    repo.save(entity);

    SimpleMailMessage message = new SimpleMailMessage(template);
    message.setTo(user.getEmail());
    StringBuffer body = new StringBuffer();
    body.append("Please confirm your email by open the folling link in your browser:");
    body.append("\r\n");
    // body.append(config.baseUrl);
    body.append("/verifyMail/token/");
    body.append(token);
    message.setText(body.toString());
    try
    {
      sender.send(message);
    }
    catch (MailSendException t)
    {
      t.printStackTrace();
      throw new ValidationException("eMail invalid :" + t.getMessage());
    }
  }

  public String createToken()
  {
    return UUID.randomUUID().toString();
  }

  @Override
  public void createNewUserToken(String user, String mail)
  {
    ActionModel entity = new ActionModel();
    entity.setAction("subscribe");
    String token = createToken();
    entity.setToken(token);
    // entity.setCreator(user);
    entity.setExpiry(new Date());
    entity.setUsed(0);
    entity.setData("<null/>");
    repo.save(entity);

    SimpleMailMessage message = new SimpleMailMessage(template);
    message.setTo(mail);
    StringBuffer body = new StringBuffer();
    body.append("You have been invited ... ");
    body.append("\r\n");
    body.append(actionUrl);
    body.append("/subscribe/token/");
    body.append(token);
    message.setText(body.toString());
    try
    {
      sender.send(message);
    }
    catch (MailSendException t)
    {
      t.printStackTrace();
      throw new ValidationException("eMail invalid :" + t.getMessage());
    }
  }

}
