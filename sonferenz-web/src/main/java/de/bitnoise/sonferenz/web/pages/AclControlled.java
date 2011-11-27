package de.bitnoise.sonferenz.web.pages;

import org.apache.wicket.model.IModel;

public interface AclControlled<T>
{
  public boolean canAccess(IModel<T> model);
}
