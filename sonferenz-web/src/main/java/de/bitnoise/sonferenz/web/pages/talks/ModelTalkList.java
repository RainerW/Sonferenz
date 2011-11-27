package de.bitnoise.sonferenz.web.pages.talks;

import java.io.Serializable;

import de.bitnoise.sonferenz.model.TalkModel;

public class ModelTalkList implements Serializable
{
  public String title;
  public String author;
  public String owner;
  public String description;
  public boolean editable;
  public int rating;
  public TalkModel talk;
}
