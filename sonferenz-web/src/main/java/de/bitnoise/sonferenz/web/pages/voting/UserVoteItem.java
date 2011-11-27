package de.bitnoise.sonferenz.web.pages.voting;

import java.io.Serializable;


import de.bitnoise.sonferenz.model.TalkModel;
import de.bitnoise.sonferenz.model.VoteModel;
import de.bitnoise.sonferenz.web.component.table.IMutiState;

public class UserVoteItem implements Serializable, IMutiState
{
  public String title;
  public String author;
  public Integer rateing;
  public Integer id;
  public TalkModel talk;
  public VoteModel model;

  public int getMutiStateValue()
  {
    return (rateing == null ? 1 : rateing);
  }

  public void setMutiStateValue(int newState)
  {
    rateing = newState;
  }
}
