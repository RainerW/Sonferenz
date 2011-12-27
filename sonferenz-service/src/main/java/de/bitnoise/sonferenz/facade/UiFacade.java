package de.bitnoise.sonferenz.facade;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.model.UserRoles;
import de.bitnoise.sonferenz.model.WhishModel;
import de.bitnoise.sonferenz.service.actions.ActionData;
import de.bitnoise.sonferenz.service.actions.Aktion;

public interface UiFacade
{

  ConferenceModel getActiveConference();

  void storeConference(ConferenceModel conference);

  Page<ConferenceModel> getConferences(Pageable page);

  UserModel getCurrentUser();

  void deleteTalk(TalkModel talk);

  void saveTalk(TalkModel talk);

  TalkModel getTalkById(int id);

  String getText(String id);

  void saveText(String id, String neu);

  Page<TalkModel> getTalks(PageRequest request);

  int getAllTalksCount();

  int getAllConferencesCount();

  int getWhishesCount();

  Page<WhishModel> getAllWhishes(PageRequest request);

  int getUserCount();

  Page<UserModel> getAllUsers(PageRequest request);

  List<UserModel> getAllUsers();

  void createNewLocalUser(String username, String password, Collection<UserRoles> newRoles);

  void saveUser(UserModel user, Collection<UserRoles> newRoles);

  void unwhish(WhishModel whish);

  void deleteWhish(WhishModel talk);

  void saveWhish(WhishModel talk);

  WhishModel getWhishById(int id);

  void likeWhish(UserModel user, WhishModel whish);

  void unLikeWhish(UserModel user, WhishModel whish);

  Integer getWhishLikeCount(UserModel user, WhishModel whish);

  List<TalkModel> getAllTalks();

  List<TalkModel> getAllTalksForConference(ConferenceModel conference);

  void removeAllVotestForTalk(List<TalkModel> asTalks);

  void removeTalksFromConference(ConferenceModel conference, List<TalkModel> asTalks);

  void addTalksToConference(ConferenceModel conference, List<TalkModel> asTalks);

  boolean vote(TalkModel talk, UserModel user, int increment);

  long getVotableTalksCount();

  Page<TalkModel> getVotableTalks(PageRequest request);

  Page<ConferenceModel> getAllConferences(Pageable pageable);

  ConferenceModel getConference(int id);

  Aktion validateAction(String action, String token);

  void userUpdate(UserModel user, String newName);

}
