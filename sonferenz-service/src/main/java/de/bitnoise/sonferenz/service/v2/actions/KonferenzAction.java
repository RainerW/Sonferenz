package de.bitnoise.sonferenz.service.v2.actions;

/**
 * 'Bean' interface to register a action.
 */
public interface KonferenzAction
{
  /**
   * The name under which this class get's registered
   * 
   * @return The ActionName
   */
  public String getActionName();

  /**
   * A Valid Action has been invoked. Now it has to be executed
   * 
   * @param data Data specific for this action
   * @return
   */
  public boolean execute(ActionState data);

  /**
   * The current Action State is saved by XStream. All Classes with
   * need Annotation processing should be listed here.
   * 
   * @return XStream classes to process anotations
   */
  public Class[] getModelClasses();
}