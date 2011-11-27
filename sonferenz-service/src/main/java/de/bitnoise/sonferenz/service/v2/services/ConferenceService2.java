package de.bitnoise.sonferenz.service.v2.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.service.v2.exceptions.GeneralConferenceException;

public interface ConferenceService2
{
  public ConferenceModel getActiveConference()
      throws GeneralConferenceException;

  public void updateConference(ConferenceModel conference)
      throws GeneralConferenceException;

  public Page<ConferenceModel> getConferences(Pageable page)
      throws GeneralConferenceException;

  public List<TalkModel> getAllTalksForConference(ConferenceModel _conference)
      throws GeneralConferenceException;

  public void removeTalksFromConference(ConferenceModel _conference, List<TalkModel> asTalks)
      throws GeneralConferenceException;

  public void addTalksToConference(ConferenceModel _conference, List<TalkModel> asTalks)
      throws GeneralConferenceException;

  public int getCount();
}
