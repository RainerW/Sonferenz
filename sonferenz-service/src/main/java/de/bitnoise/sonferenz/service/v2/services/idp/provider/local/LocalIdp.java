package de.bitnoise.sonferenz.service.v2.services.idp.provider.local;

import org.jasypt.digest.StringDigester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.bitnoise.sonferenz.model.LocalUserModel;
import de.bitnoise.sonferenz.repo.LocalUserRepository;
import de.bitnoise.sonferenz.service.v2.exceptions.RepositoryException;
import de.bitnoise.sonferenz.service.v2.services.idp.provider.Idp;

@Component
public class LocalIdp implements Idp
{

  private static String IDP_NAME = "plainDB";

  @Autowired
  LocalUserRepository localRepo;

  @Autowired(required = false)
  StringDigester _digester;

  @Override
  public String getProviderName()
  {
    return IDP_NAME;
  }

  private LocalUserModel map(String name, String password)
  {
    LocalUserModel model = new LocalUserModel();
    model.setName(name);
    String pwd = sign(password);
    model.setPassword(pwd);
    return model;
  }

  private String sign(String password)
  {
    if (_digester == null)
    {
      return password;
    }
    String result = _digester.digest(password);
    return result;
  }

  @Override
  @Transactional(readOnly = true)
  public boolean checkIdentityExist(String name)
  {
    try
    {
      LocalUserModel u = localRepo.findByName(name);
      if (u != null)
      {
        return true;
      }
      return false;
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }
  }

  @Override
  @Transactional
  public void createIdentity(String name, String password)
  {
    try
    {
      localRepo.save(map(name, password));
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }
  }

  @Override
  @Transactional
  public void setPassword(String name, String password)
  {
    try
    {
      localRepo.save(map(name, password));
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }
  }

}
