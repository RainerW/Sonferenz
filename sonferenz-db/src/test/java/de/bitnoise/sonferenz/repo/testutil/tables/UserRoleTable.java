package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class UserRoleTable extends BaseDBTable
{
  public ValueBuilder<Integer> id;

  public ValueBuilder<String> name;

  public UserRoleTable()
  {
    super("userrole");
    id = addColumn("id", DataType.BIGINT, true);
    name = addColumn("name", DataType.VARCHAR);
  }
}
