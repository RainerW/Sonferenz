package de.bitnoise.sonferenz.service.v2.services;


public interface StaticContentService2
{
  String text(String key);
  
  String text(String key, String defaultValue);
  
  void storeText(String key, String text);
}
