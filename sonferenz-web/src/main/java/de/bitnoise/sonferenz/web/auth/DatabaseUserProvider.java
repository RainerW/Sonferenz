package de.bitnoise.sonferenz.web.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jasypt.digest.StandardStringDigester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import de.bitnoise.sonferenz.model.LocalUserModel;
import de.bitnoise.sonferenz.service.v2.security.ProviderType;
import de.bitnoise.sonferenz.service.v2.services.UserService2;

public class DatabaseUserProvider extends
    AbstractUserDetailsAuthenticationProvider
{
  @Autowired
  UserService2 userService;

  
  @Autowired
  StandardStringDigester pwdCrypt;

  private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();

  public void setPasswordEncoder(PasswordEncoder passwordEncoder)
  {
    this.passwordEncoder = passwordEncoder;
  }
  
  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException
  {

    if (authentication.getCredentials() == null)
    {
      logger.debug("Authentication failed: no credentials provided");

      throw new BadCredentialsException("Bad Credentials");
    }

    String presentedPassword = authentication.getCredentials().toString();
    passwordEncoder.encodePassword(presentedPassword, null);
    String ep = encryptedPwd(userDetails);
    if (!passwordEncoder.isPasswordValid(ep, presentedPassword, null))
    {
      logger
          .debug("Authentication failed: password does not match stored value");

      throw new BadCredentialsException("Bad Credentials");
    }

  }

  public String encryptedPwd(UserDetails userDetails)
  {
    return userDetails.getPassword();
  }

  @Override
  protected UserDetails retrieveUser(String username,
      UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException
  {
    LocalUserModel localUser = userService.findLocalUser(username);
    if (localUser == null)
    {
      throw new UsernameNotFoundException("user not found");
    }
    User user = buildUserFromUserEntity(localUser);
    return user;
  }

  public static String PROVIDER_TYPE = "plainDB";

  User buildUserFromUserEntity(LocalUserModel userEntity)
  {
    String username = userEntity.getName();
    String password = userEntity.getPassword();
    // String password = usrSrvice.encryptPassword(passwordPlain);
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    List<String> roles = userService.getAllUserRoles(username, PROVIDER_TYPE);
//    List<String> roles = userService
//        .getAllRolesForUser(username, PROVIDER_TYPE);
    for (String role : roles)
    {
      authorities.add(new GrantedAuthorityImpl("ROLE_" + role));
    }
    authorities.add(new GrantedAuthorityImpl("ROLE_LOCAL_DB"));

    User user = new DatabaseUserDetails(username, password, enabled,
        accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
    return user;
  }

  public static class DatabaseUserDetails extends User implements ProviderType
  {
    public DatabaseUserDetails(String username, String password,
        boolean enabled, boolean accountNonExpired,
        boolean credentialsNonExpired, boolean accountNonLocked,
        Collection<GrantedAuthority> authorities)
    {
      super(username, password, enabled,
           accountNonExpired, credentialsNonExpired, accountNonLocked,
           authorities);
    }

    public String getProviderType()
    {
      return PROVIDER_TYPE;
    }

  }
}
