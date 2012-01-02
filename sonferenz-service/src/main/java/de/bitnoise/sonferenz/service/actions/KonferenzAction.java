package de.bitnoise.sonferenz.service.actions;

public interface KonferenzAction
{
  public String getActionName();

  public boolean execute(ActionData data);

  public Class[] getModelClasses();

}