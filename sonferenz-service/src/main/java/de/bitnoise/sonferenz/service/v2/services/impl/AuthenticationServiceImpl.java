package de.bitnoise.sonferenz.service.v2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import de.bitnoise.sonferenz.model.AuthMapping;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.repo.AuthmappingRepository;
import de.bitnoise.sonferenz.repo.UserRepository;
import de.bitnoise.sonferenz.service.v2.exceptions.GeneralConferenceException;
import de.bitnoise.sonferenz.service.v2.impl.Detach;
import de.bitnoise.sonferenz.service.v2.security.ProviderType;
import de.bitnoise.sonferenz.service.v2.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{

  @Autowired
  AuthmappingRepository authRepo;
  
  @Autowired
  UserRepository userRepo;

  @Override
  @Transactional
  public UserModel getCurrentUser() throws GeneralConferenceException
  {
    try
    {
      return internGetCurrentUser();
    }
    catch (Throwable t)
    {
      throw new GeneralConferenceException("Unerwarteter Fehler aufgetreten",t);
    }
  }

  private UserModel internGetCurrentUser()
  {
    SecurityContext context = SecurityContextHolder.getContext();
    if (context == null)
    {
      return null;
    }
    Thread t = Thread.currentThread();
    Authentication authentication = context.getAuthentication();
    if (authentication == null)
    {
      return null;
    }
    String providerId = authentication.getName();
    String providerType = null;
    if (authentication.getPrincipal() instanceof LdapUserDetails)
    {
      providerType = "ldap";
    }
    if (authentication instanceof ProviderType)
    {
      providerType = ((ProviderType) authentication).getProviderType();
    }
    if (authentication.getPrincipal() instanceof ProviderType)
    {
      providerType = ((ProviderType) authentication.getPrincipal())
          .getProviderType();
    }
    if (providerType == null)
    {
      return null;
    }
    AuthMapping mapping = authRepo.findByAuthIdAndAuthType(providerId,
        providerType);
    if (mapping == null)
    {
      UserModel neuerUser = neuenNutzerAnlegen(providerId);
      neuesAuthMappingAnlegen(neuerUser, providerId, providerType);
      return neuerUser;
    }
    return Detach.detach(mapping.getUser());
  }

  private void neuesAuthMappingAnlegen(UserModel neuerUser, String providerId,
      String providerType)
  {
    AuthMapping neuesMapping = new AuthMapping();
    neuesMapping.setAuthId(providerId);
    neuesMapping.setAuthType(providerType);
    neuesMapping.setUser(neuerUser);
    authRepo.save(neuesMapping);
  }

  private UserModel neuenNutzerAnlegen(String providerId)
  {
    UserModel neuerUser = new UserModel();
    neuerUser.setName(providerId);
    userRepo.save(neuerUser);
    return neuerUser;
  }
}
