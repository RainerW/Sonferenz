package de.bitnoise.sonferenz.service.v2.services.userdata;

import de.bitnoise.sonferenz.service.v2.services.userdata.beans.UserData;

public interface UserDataService
{
  public void setUserData(UserData userData);

  public UserData getUserData(String userId);
}
