package de.bitnoise.sonferenz.web.component.state;

import de.bitnoise.sonferenz.web.component.navigation.VisibleChoice;

public class AllwaysVisible implements VisibleChoice
{

  public boolean canBeDisplayed()
  {
    return true;
  }

}
