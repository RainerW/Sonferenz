package de.bitnoise.sonferenz.service.v2.actions;

import java.io.Serializable;

/**
 * Memmento for this action. Concrete classes get stored via XStream.
 * It's highly recommend to use XStreamAlias for your State.
 */
public interface ActionState extends Serializable
{
  /**
   * The name under which this class get's registered
   * 
   * @return The ActionName
   */
  String getActionName();
}
