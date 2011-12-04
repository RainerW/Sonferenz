package de.bitnoise.sonferenz.service.v2.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.repo.ConferenceRepository;
import de.bitnoise.sonferenz.repo.TalkRepository;
import de.bitnoise.sonferenz.service.v2.exceptions.GeneralConferenceException;
import de.bitnoise.sonferenz.service.v2.exceptions.RepositoryException;
import de.bitnoise.sonferenz.service.v2.services.ConferenceService2;

@Service
public class ConferenceService2Impl implements ConferenceService2
{
  @Autowired
  ConferenceRepository conferenceRepo;
  
  @Autowired
  TalkRepository talkRepo;

  @Override
  public ConferenceModel getActiveConference()
  {
    try
    {
      ConferenceModel current = conferenceRepo.findByActive(true);
      return current;
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }
  }

  @Override
  public void updateConference(ConferenceModel conference)
      throws GeneralConferenceException
  {
    try
    {
      conferenceRepo.save(conference);
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }
  }

  @Override
  public Page<ConferenceModel> getConferences(Pageable page)
      throws GeneralConferenceException
  {
    try
    {
      return conferenceRepo.findAll(page);
    }
    catch (Throwable t)
    {
      throw new RepositoryException(t);
    }

  }

  @Override
  public List<TalkModel> getAllTalksForConference(ConferenceModel conference) throws GeneralConferenceException
  {
    return talkRepo.findAllByConference(conference);
  }

  @Override
  public void removeTalksFromConference(ConferenceModel _conference, List<TalkModel> talksToRemove)
      throws GeneralConferenceException
  {
    for (TalkModel talk : talksToRemove)
    {
      talk.setConference(null);
      talkRepo.save(talk);
    }
  }

  @Override
  public void addTalksToConference(ConferenceModel conference, List<TalkModel> talksToAdd)
      throws GeneralConferenceException
  {
    for (TalkModel talk : talksToAdd)
    {
      talk.setConference(conference);
      talkRepo.save(talk);
    }
  }

  @Override
  public int getCount()
  {
    return (int) conferenceRepo.count();
  }

  @Override
  public ConferenceModel getConference(int id)
  {
    return conferenceRepo.findOne(id);
  }

}
