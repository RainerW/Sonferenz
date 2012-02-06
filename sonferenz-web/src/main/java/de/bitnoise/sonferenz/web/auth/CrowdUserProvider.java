package de.bitnoise.sonferenz.web.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import de.bitnoise.sonferenz.service.v2.security.ProviderType;
import de.bitnoise.sonferenz.service.v2.security.ProvidesEmail;
import de.bitnoise.sonferenz.service.v2.services.UserService;
import de.bitnoise.sonferenz.service.v2.services.idp.Identity;
import de.bitnoise.sonferenz.service.v2.services.idp.IdpService;
import de.bitnoise.sonferenz.service.v2.services.idp.provider.crowd.CrowdIdp;

public class CrowdUserProvider extends AbstractUserDetailsAuthenticationProvider
{
  @Autowired
  UserService userService;

  @Autowired
  IdpService idpService;

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
  {
    String presentedPassword = authentication.getCredentials().toString();
    boolean isAuthenticated = idpService.authenticate(CrowdIdp.IDP_NAME, userDetails.getUsername(), presentedPassword);
    if (!isAuthenticated)
    {
      logger.debug("Authentication failed: password does not match stored value");

      throw new BadCredentialsException("Bad Credentials");
    }
  }

  @Override
  @Transactional
  protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException
  {
    Identity identity = idpService.getIdentity(CrowdIdp.IDP_NAME, username);
    if (identity == null)
    {
      throw new UsernameNotFoundException("user not found");
    }
    String mail = identity.getEmail();
    String password = "dummypassword";
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    User user = new CrowdUserDetails(username, password, enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, authorities, mail);
    return user;
  }

  public static class CrowdUserDetails extends User implements ProviderType, ProvidesEmail
  {
    private static final long serialVersionUID = -3500108479282273570L;
    private String email;
    public CrowdUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
        boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities, String email)
    {
      super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
      this.email = email;
    }

    @Override
    public String getEmail()
    {
      return email;
    }

    @Override
    public String getProviderType()
    {
      return CrowdIdp.IDP_NAME;
    }

  }
}
