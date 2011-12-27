package de.bitnoise.sonferenz.web.component.state;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.web.component.navigation.VisibleChoice;

public class IsLoggedIn implements VisibleChoice
{
  public boolean canBeDisplayed()
  {
    return !KonferenzSession.noUserLoggedIn();
  }

}
