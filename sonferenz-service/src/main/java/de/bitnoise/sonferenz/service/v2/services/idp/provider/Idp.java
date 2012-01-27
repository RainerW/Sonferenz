package de.bitnoise.sonferenz.service.v2.services.idp.provider;


public interface Idp
{
  boolean checkIdentityExist(String name);
  
  void createIdentity(String name, String password);
  
  void setPassword(String name, String password);
  
  String getProviderName();
}
