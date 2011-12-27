package de.bitnoise.sonferenz.service.v2.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.jasypt.digest.StringDigester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import de.bitnoise.sonferenz.service.v2.services.UserService2;

@Service
public class ActionServiceImpl implements ActionService
{
  @Autowired
  ActionRepository repo;
  
  @Autowired
  UserService2 userService;

  @Autowired
  AuthmappingRepository authRepo;
  
  @Autowired
  UserRepository userRepo;

  @Autowired
  LocalUserRepository localUserRepo;

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
  public Page<ActionModel> getUserActions(UserModel user)
  {
    return repo.findByCreator(user);
  }

  @Override
  @Transactional
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

    Collection<UserRoles> newRoles=new ArrayList<UserRoles>();
    newRoles.add(UserRoles.USER);
    userService.createNewLocalUser(data.getLoginName(), data.getPassword(), newRoles);
//    LocalUserModel localUser = new LocalUserModel();
//    localUser.setName(data.getLoginName());
//    String encrypted = digester.digest(data.getPassword());
//    localUser.setPassword(encrypted);
//
//    AuthMapping mapping = new AuthMapping();
//    mapping.setAuthId(data.getUserName());
//    mapping.setAuthType("plainDB");
//
//    UserModel user = new UserModel();
//    user.setProvider(mapping);
//    user.setProvider(mapping);
//    user.setName(data.getUserName());
//    user.setEmail(data.getEMail());
//    
//    user
//    user.setRoles(roles);
//
//    localUserRepo.save(localUser);
//    authRepo.save(mapping);
//    userRepo.save(user);

    // newActionVerifyEMail(data.getEMail());
  }

}
