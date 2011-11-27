package de.bitnoise.sonferenz.service.v2.extend.ports;

import java.util.List;
import java.util.Set;

import de.bitnoise.sonferenz.model.TalkModel;

public interface TimeTable
{
  List<TalkModel> getTrack(int track);

  List<TalkDetails> getRawList();

  public interface TalkDetails
  {
    public String getTitle();

    public Integer getVotes();

    public Set<String> getVotedBy();

    public String getID();
  }
}
