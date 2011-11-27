package de.bitnoise.sonferenz.service.v2.services.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;


import de.bitnoise.sonferenz.model.LocalUserModel;
import de.bitnoise.sonferenz.repo.AuthmappingRepository;
import de.bitnoise.sonferenz.repo.LocalUserRepository;
import de.bitnoise.sonferenz.repo.UserRepository;
import de.bitnoise.sonferenz.service.v2.BaseTest;
import de.bitnoise.sonferenz.service.v2.exceptions.RepositoryException;
import de.bitnoise.sonferenz.service.v2.services.impl.UserService2Impl;

public class UserFacadeImplTest extends BaseTest
{
  LocalUserRepository localMock = strictMock(LocalUserRepository.class);

  UserRepository userMock = strictMock(UserRepository.class);

  AuthmappingRepository authMock = strictMock(AuthmappingRepository.class);

  UserService2Impl sut = new UserService2Impl()
  {
    {
      localRepo = localMock;
      userRepo = userMock;
      authRepo = authMock;
    }
  };

  @Test
  public void testFindLocalUser_Null()
  {
    // prepare
    when(localMock.findByName(anyString()))
        .thenThrow(NullPointerException.class);

    // verify
    expectException.expect(RepositoryException.class);

    // execute
    sut.findLocalUser(null);
  }


  @Test
  @Ignore("2 implement")
  public void testFindLocalUser_WithNameAndFound()
  {
    // prepare
    LocalUserModel model = mock(LocalUserModel.class);
    when(localMock.findByName(eq("rainer"))).thenReturn(model);

    // execute
    LocalUserModel result = sut.findLocalUser("rainer");

    // verify
    assertThat(result).isSameAs(model);
    
    verify(localMock.findByName(eq("rainer")));
    verifyNoMoreInteractions(localMock);
  }

  @Test
  public void testFindLocalUser_WithNameAndNotFound()
  {
    // prepare
    when(localMock.findByName(eq("rainer"))).thenReturn(null);

    // execute
    LocalUserModel result = sut.findLocalUser("rainer");

    // verify
    assertThat(result).isNull();
  }

  @Test
  public void testGetAllUserRoles_Null()
  {
    // prepare
    when(localMock.findByName(anyString()))
        .thenThrow(NullPointerException.class);

    // verify
    expectException.expect(RepositoryException.class);

    // execute
    sut.getAllUserRoles(null, null);
  }

  @Test
  public void testUpdateLocalUser_Null()
  {
    replay();
  }

}
