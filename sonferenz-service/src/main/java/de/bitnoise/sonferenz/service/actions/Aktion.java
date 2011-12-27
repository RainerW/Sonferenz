package de.bitnoise.sonferenz.service.actions;

public class Aktion
{
  String action;

  public Aktion(String action2, ActionData data2)
  {
    this.action = action2;
    this.data = data2;
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
}
