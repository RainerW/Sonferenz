package de.bitnoise.sonferenz.web.action;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

public interface ActionBookmark<T>
{

  Link<T> createBookmark(IModel<T> model,String id);

}
