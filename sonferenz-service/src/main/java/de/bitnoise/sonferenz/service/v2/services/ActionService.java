package de.bitnoise.sonferenz.service.v2.services;

import de.bitnoise.sonferenz.service.actions.ActionData;
import de.bitnoise.sonferenz.service.actions.Aktion;

public interface ActionService
{

  Aktion loadAction(String action, String token);

}
