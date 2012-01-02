package de.bitnoise.sonferenz.service.v2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.bitnoise.sonferenz.model.ActionModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.service.actions.ActionData;
import de.bitnoise.sonferenz.service.actions.Aktion;
import de.bitnoise.sonferenz.service.actions.KonferenzAction;
import de.bitnoise.sonferenz.service.actions.impl.ActionResult;

public interface ActionService
{

  Aktion loadAction(String action, String token);

  Page<ActionModel> getUserActions(PageRequest request, UserModel user);

  void execute(ActionData data);

  ActionResult createAction(KonferenzAction action, ActionData data);


}
