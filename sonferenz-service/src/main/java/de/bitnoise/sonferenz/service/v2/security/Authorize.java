package de.bitnoise.sonferenz.service.v2.security;

import java.util.List;

import de.bitnoise.sonferenz.service.v2.exceptions.NoRightsExcpetion;

public class Authorize
{

  public static void has(Authorisation roles, String right)
  {
    List<String> x = roles.getRoles();
    if (x == null)
    {
      throw new NoRightsExcpetion("User has no right for this Operation");
    }
  }

}
