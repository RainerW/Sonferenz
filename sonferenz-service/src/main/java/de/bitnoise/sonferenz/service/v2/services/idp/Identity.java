package de.bitnoise.sonferenz.service.v2.services.idp;

public class Identity
{
  private String provider;
  
  private String name;
  
  private String password;

  public String getProvider()
  {
    return provider;
  }

  public void setProvider(String provider)
  {
    this.provider = provider;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }
  
  
}
