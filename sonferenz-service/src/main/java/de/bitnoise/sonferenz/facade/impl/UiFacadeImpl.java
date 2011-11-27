package de.bitnoise.sonferenz.facade.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.model.UserRoles;
import de.bitnoise.sonferenz.model.WhishModel;
import de.bitnoise.sonferenz.service.v2.services.AuthenticationService2;
import de.bitnoise.sonferenz.service.v2.services.ConferenceService2;
import de.bitnoise.sonferenz.service.v2.services.StaticContentService2;
import de.bitnoise.sonferenz.service.v2.services.TalkService2;
import de.bitnoise.sonferenz.service.v2.services.UserService2;
import de.bitnoise.sonferenz.service.v2.services.VoteService2;
import de.bitnoise.sonferenz.service.v2.services.WhishService2;

@Service
public class UiFacadeImpl implements UiFacade
{
  @Autowired
  ConferenceService2 _conference;

  @Autowired
  StaticContentService2 content;

  @Autowired
  TalkService2 _talks;

  @Autowired
  UserService2 userFacade;

  @Autowired
  AuthenticationService2 authService;
  
  @Autowired
  WhishService2 whishService;
  
  @Autowired
  VoteService2 voteService;

  @Override
  public ConferenceModel getActiveConference()
  {
    return _conference.getActiveConference();
  }

  @Override
  public void storeConference(ConferenceModel conference)
  {
    _conference.updateConference(conference);
  }

  @Override
  public Page<ConferenceModel> getConferences(Pageable page)
  {
    return _conference.getConferences(page);
  }

  @Override
  public UserModel getCurrentUser()
  {
    return authService.getCurrentUser();
  }

  @Override
  public void deleteTalk(TalkModel talk)
  {
    _talks.deleteTalk(talk);
  }

  @Override
  public void saveTalk(TalkModel talk)
  {
    _talks.saveTalk(talk);
  }

  @Override
  public TalkModel getTalkById(int id)
  {
    return _talks
        .getTalkById(id);
  }

  @Override
  public String getText(String key)
  {
    return content.text(key);
  }

  @Override
  public void saveText(String key, String neu)
  {
    content.storeText(key, neu);
  }

  @Override
  public Page<TalkModel> getTalks(PageRequest request)
  {
    return _talks.getTalks(request);
  }

  @Override
  public int getAllTalksCount()
  {
    return _talks.getCount();
  }

  @Override
  public int getAllConferencesCount()
  {
    return _conference.getCount();
  }

  @Override
  public int getWhishesCount()
  {
    return whishService.getCount();
  }

  @Override
  public Page<WhishModel> getAllWhishes(PageRequest request)
  {
    return whishService.getWhishes(request);
  }

  @Override
  public int getUserCount()
  {
    return userFacade.getCount();
  }

  @Override
  public Page<UserModel> getAllUsers(PageRequest request)
  {
    return userFacade.getAllUsers(request);
  }

  @Override
  public List<UserModel> getAllUsers()
  {
    return userFacade.getAllUsers();
  }

  @Override
  public void createNewLocalUser(String username, String password, Collection<UserRoles> newRoles)
  {
    userFacade.createNewLocalUser(username,password,newRoles);
  }

  @Override
  public void saveUser(UserModel user, Collection<UserRoles> newRoles)
  {
    userFacade.saveUser(user,newRoles);
  }

  @Override
  public void unwhish(WhishModel whish)
  {
    whishService.unWhish(whish);
  }

  @Override
  public void deleteWhish(WhishModel whish)
  {
    whishService.deleteWhish(whish);
  }

  @Override
  public void saveWhish(WhishModel whish)
  {
    whishService.saveWhish(whish);
  }

  @Override
  public WhishModel getWhishById(int id)
  {
    return  whishService.getWhishById(id);
  }

  @Override
  public void likeWhish(UserModel user, WhishModel whish)
  {
    whishService.like(user,whish);
  }

  @Override
  public void unLikeWhish(UserModel user, WhishModel whish)
  {
    whishService.unLike(user,whish);
  }

  @Override
  public Integer getWhishLikeCount(UserModel user, WhishModel whish)
  {
    return whishService.getWhishLikeCount(user,whish);
  }

  @Override
  public List<TalkModel> getAllTalks()
  {
    return _talks .getAllTalks();
  }

  @Override
  public List<TalkModel> getAllTalksForConference(ConferenceModel conference)
  {
    return _conference.getAllTalksForConference(conference);
  }

  @Override
  public void removeAllVotestForTalk(List<TalkModel> talks)
  {
    voteService.removeAllVotestForTalk(talks);
  }

  @Override
  public void removeTalksFromConference(ConferenceModel conference, List<TalkModel> asTalks)
  {
    _conference.removeTalksFromConference(conference, asTalks);
  }

  @Override
  public void addTalksToConference(ConferenceModel conference, List<TalkModel> asTalks)
  {
    _conference.addTalksToConference(conference, asTalks);
  }

  @Override
  public boolean vote(TalkModel talk, UserModel user, int increment)
  {
    return voteService.vote(talk,user,increment);
  }

  @Override
  public long getVotableTalksCount()
  {
    return _talks.getVotableTalksCount();
  }

  @Override
  public Page<TalkModel> getVotableTalks(PageRequest request)
  {
    return _talks.getVotableTalks(request);
  }
 

}
