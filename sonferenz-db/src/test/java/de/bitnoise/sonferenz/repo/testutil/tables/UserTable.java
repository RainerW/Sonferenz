package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class UserTable extends BaseDBTable
{

  public ValueBuilder<Integer> id;

  public ValueBuilder<String> name;

  public UserTable()
  {
    super("usermodel");
    id = addColumn("id", DataType.BIGINT, true);
    name = addColumn("name", DataType.VARCHAR);
  }

}
