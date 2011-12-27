package de.bitnoise.sonferenz.service.v2.services;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.bitnoise.sonferenz.model.LocalUserModel;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.model.UserRoles;

public interface UserService2
{
  LocalUserModel findLocalUser(String username);

  List<String> getAllUserRoles(String username, String providerType);

  void updateLocalUser(LocalUserModel localUser);

  UserModel getUser(String username, String providerType);

  void createNewUser(UserModel neu, String authId,String authType);

  int getCount();

  Page<UserModel> getAllUsers(PageRequest request);

  List<UserModel> getAllUsers();

  void createNewLocalUser(String username, String password, Collection<UserRoles> newRoles);

  void saveUser(UserModel user, Collection<UserRoles> newRoles);

  void updateUser(UserModel user, String newName);
}
