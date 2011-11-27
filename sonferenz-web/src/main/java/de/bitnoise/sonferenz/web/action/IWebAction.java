package de.bitnoise.sonferenz.web.action;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

public interface IWebAction<T extends IModel<?>> extends Serializable
{
  Page doAction(T model);

  boolean isVisible();
}
