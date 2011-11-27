package de.bitnoise.sonferenz.repo.testutil;

import de.bitnoise.sonferenz.repo.testutil.builder.RowBuilder;

public class DatabaseWithUserRoles extends BasicDataSet
{
  public RowBuilder roleAdmin;
  public RowBuilder roleUser;

  public DatabaseWithUserRoles()
  {
    super();
    { 
      // Users Roles
      roleAdmin = userRoleTable.addRow();
      userRoleTable.id.set(1);
      userRoleTable.name.set("ADMIN");
      roleUser = userRoleTable.addRow();
      userRoleTable.id.set(2);
      userRoleTable.name.set("USER");
    }
  }

}
