package de.bitnoise.sonferenz.model;


public abstract class ModelBase implements DoInterface<Integer>
{
  public boolean isNew()
  {
    return getId() == null;
  }
}
