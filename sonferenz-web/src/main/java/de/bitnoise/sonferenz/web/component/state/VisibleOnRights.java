package de.bitnoise.sonferenz.web.component.state;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.web.component.navigation.VisibleChoice;

public class VisibleOnRights implements VisibleChoice
{
  private String _rights;

  public VisibleOnRights(String rights)
  {
    _rights = rights;
  }

  public boolean canBeDisplayed()
  {
    return KonferenzSession.hasRight(_rights);
  }

}
