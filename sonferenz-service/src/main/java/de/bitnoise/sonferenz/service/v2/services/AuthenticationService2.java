package de.bitnoise.sonferenz.service.v2.services;


import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.service.v2.exceptions.GeneralConferenceException;

public interface AuthenticationService2
{
  UserModel getCurrentUser() throws GeneralConferenceException;
}
