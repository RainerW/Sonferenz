package de.bitnoise.sonferenz.web.forms;

import org.apache.wicket.Component;

public interface FormCallback
{

  void onSubmitForm(Component target);

  void onCancelForm(Component target);

}
