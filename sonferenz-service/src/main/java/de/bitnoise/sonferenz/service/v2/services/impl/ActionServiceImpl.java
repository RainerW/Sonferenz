package de.bitnoise.sonferenz.service.v2.services.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoughtworks.xstream.XStream;

import de.bitnoise.sonferenz.model.ActionModel;
import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.model.VoteModel;
import de.bitnoise.sonferenz.repo.ActionRepository;
import de.bitnoise.sonferenz.repo.TalkRepository;
import de.bitnoise.sonferenz.repo.VoteRepository;
import de.bitnoise.sonferenz.service.actions.ActionData;
import de.bitnoise.sonferenz.service.actions.Aktion;
import de.bitnoise.sonferenz.service.v2.services.ActionService;
import de.bitnoise.sonferenz.service.v2.services.VoteService2;

@Service
public class ActionServiceImpl implements ActionService
{
  @Autowired
  ActionRepository repo;

  @Override
  @Transactional(readOnly = true)
  public Aktion loadAction(String action, String token)
  {
    ActionModel row = repo.findByActionAndToken(action, token);
    if (row == null)
    {
      return null;
    }
    if (row.getExpiry() == null)
    {
      return null;
    }
    if (!row.getAction().equals(action))
    {
      return null;
    }
    if (!row.getToken().equals(token))
    {
      return null;
    }
    XStream xs = new XStream();
    ActionData data = (ActionData) xs.fromXML(row.getData());
    return new Aktion(row.getAction(),data);
  }

}
