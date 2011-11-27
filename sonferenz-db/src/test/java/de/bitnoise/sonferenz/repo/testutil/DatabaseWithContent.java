package de.bitnoise.sonferenz.repo.testutil;

import de.bitnoise.sonferenz.repo.testutil.builder.RowBuilder;

public class DatabaseWithContent extends BasicDataSet
{
  public RowBuilder<Integer> userRainer;

  public RowBuilder<Integer> userOther;
  
  public RowBuilder<Integer> userAnonym;

  public RowBuilder<Integer> roleADMIN;

  public RowBuilder<Integer> roleUSER;

  public RowBuilder<Integer> rainerRoleAdmin;

  public RowBuilder<Integer> rainerRoleUser;

  public RowBuilder<Integer> otherRoleUser;
  
  public RowBuilder<Integer> authRainer;

  public RowBuilder<Integer> talk1;
  
  public RowBuilder<Integer> suggestion1;
  
  public RowBuilder<Integer> conference1;


  public DatabaseWithContent()
  {
    super();
    { 
      // users
      userRainer = userTable.addRow();
      userTable.id.set(1);
      userTable.name.set("Rainer Weinhold");
      userOther = userTable.addRow();
      userTable.id.set(2);
      userTable.name.set("Another");
      userAnonym = userTable.addRow();
      userTable.id.set(3);
      userTable.name.set("Anonymous");

      // Roles
      roleADMIN = userRoleTable.addRow();
      userRoleTable.id.set(1);
      userRoleTable.name.set("ADMIN");

      roleUSER = userRoleTable.addRow();
      userRoleTable.id.set(2);
      userRoleTable.name.set("USER");

      // User 2 Roles
      rainerRoleAdmin = user2UserRoleTable.addRow();
      user2UserRoleTable.rolesId.set(roleADMIN);
      user2UserRoleTable.userModelId.set(userRainer);
      rainerRoleUser = user2UserRoleTable.addRow();
      user2UserRoleTable.rolesId.set(roleUSER);
      user2UserRoleTable.userModelId.set(userRainer);

      otherRoleUser = user2UserRoleTable.addRow();
      user2UserRoleTable.rolesId.set(roleUSER);
      user2UserRoleTable.userModelId.set(userOther);
      
      // AuthMapping
      authRainer = authMappingTable.addRow();
      authMappingTable.id.set(1);
      authMappingTable.authId.set("rainer.weinhold");
      authMappingTable.authType.set("ldap");
      
      // Talks
      talk1 = talkTable.addRow();
      talkTable.id.set(1);
      talkTable.autor.set("Author : Rainer");
      talkTable.description.set("SDC from scratch");
      talkTable.title.set("SDC Vortrag");
      talkTable.owner.set(userRainer.getId());
      talkTable.conference.setNull();
      
      // suggestions
      suggestion1 = whishModelTable.addRow();
      whishModelTable.id.set(1);
      whishModelTable.description.set("SDC from scratch");
      whishModelTable.title.set("SDC Vortrag");
      whishModelTable.ownerId.set(userRainer.getId());
      
      // Conference
      conference1 = conferenceTable.addRow();
      conferenceTable.id.set(1);
      conferenceTable.description.set("Die Jaehliche Konferenz");
      conferenceTable.shortName.set("SDC 2011");
      conferenceTable.title.set("SDC");
      
      staticContentModelTable.addRow();
      staticContentModelTable.id.set(1);
      staticContentModelTable.name.set("rainer");
      staticContentModelTable.html.set("<html/>");
    }
  }
}
