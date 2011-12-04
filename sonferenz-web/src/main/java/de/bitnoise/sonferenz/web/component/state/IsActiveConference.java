package de.bitnoise.sonferenz.web.component.state;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.web.component.navigation.VisibleChoice;

public class IsActiveConference implements VisibleChoice
{
  public IsActiveConference()
  {
  }

  public boolean canBeDisplayed()
  {
    return KonferenzSession.get().activeIsCurrent();
  }

}
