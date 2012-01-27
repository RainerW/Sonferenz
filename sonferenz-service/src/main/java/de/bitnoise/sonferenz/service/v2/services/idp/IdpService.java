package de.bitnoise.sonferenz.service.v2.services.idp;


public interface IdpService
{
  String findProviderForIdentity(String name);
  
  void createIdentity(String provider, String name, String password);
  
  void setPassword(String provider, String name, String password);
  
}
