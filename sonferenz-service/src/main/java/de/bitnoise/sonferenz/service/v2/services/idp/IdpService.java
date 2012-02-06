package de.bitnoise.sonferenz.service.v2.services.idp;

import java.util.List;


public interface IdpService
{
  String findProviderForIdentity(String name);
  
  void createIdentity(String provider, String name, String password);
  
  void setPassword(String provider, String name, String password);
  
  boolean checkIdentity(String provider, String name);
  
  boolean authenticate(String provider, String name, String password);
  
  List<String> getAvailableProviders();
  
  Identity getIdentity(String provider, String name);
  
}
