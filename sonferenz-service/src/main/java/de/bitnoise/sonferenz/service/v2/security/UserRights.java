package de.bitnoise.sonferenz.service.v2.security;

public interface UserRights
{
  interface User
  {
  }

  interface Admin
  {
    interface StaticContent
    {
      String ANY = "admin.static.content.";
    }
  }
}
