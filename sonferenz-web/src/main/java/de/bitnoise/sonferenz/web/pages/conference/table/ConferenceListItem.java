package de.bitnoise.sonferenz.web.pages.conference.table;

import java.io.Serializable;

import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.model.ConferenceState;

public class ConferenceListItem implements Serializable
{
  public String shortName;
  public ConferenceState state;
  public ConferenceModel conference;
  public Boolean aktiv;
}
