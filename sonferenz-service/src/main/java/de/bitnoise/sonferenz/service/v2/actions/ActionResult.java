package de.bitnoise.sonferenz.service.v2.actions;


/**
 * Action execution result.
 */
public interface ActionResult
{
  /**
   * Replacment constant for the actual action url (complete path with
   * tokens ) in message bodies etc.
   */
  String ACTION_URL = "${url.action}";

  /**
   * Was the Action execution successfull?
   * 
   * @return true or false
   */
  boolean wasSuccessfull();

  /**
   * get a special Replacer to modify message bodies etc.
   * 
   * @return A Replacer
   */
  ContentReplacement getContentReplacer();

}
