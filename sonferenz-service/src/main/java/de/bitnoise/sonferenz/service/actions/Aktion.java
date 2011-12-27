package de.bitnoise.sonferenz.service.actions;

public class Aktion
{
  String action;
  String token;

  public Aktion(Integer tokenId,String action2, String token,ActionData data2)
  {
    this.action = action2;
    this.data = data2;
    this.token=token;
    this.id=tokenId;
  }

  public String getAction()
  {
    return action;
  }

  public ActionData getData()
  {
    return data;
  }

  ActionData data;
  
  Integer id;

  public String getToken()
  {
    return token;
  }

  public Integer getId()
  {
    return id;
  }
}
