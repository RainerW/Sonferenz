package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class User2UserRoleTable extends BaseDBTable
{

  public ValueBuilder<Integer> userModelId;
  public ValueBuilder<Integer> rolesId;

  public User2UserRoleTable()
  {
    super("usermodel_userrole");
    userModelId = addColumn("UserModel_id", DataType.BIGINT);
    rolesId = addColumn("roles_id", DataType.VARCHAR);
  }

}
