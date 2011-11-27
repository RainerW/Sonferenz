package de.bitnoise.sonferenz.service.v2.services;

import java.util.List;

import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.model.UserModel;

public interface VoteService2
{

  void removeAllVotestForTalk(List<TalkModel> talks);

  boolean vote(TalkModel talk, UserModel user, int increment);

}
