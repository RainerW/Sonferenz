package de.bitnoise.sonferenz.service.actions.impl;

public interface ActionResult 
{

  Object ACTION_URL = "${url.action}";

  boolean wasSuccessfull();

  ContentReplacement getContentReplacer();

}
