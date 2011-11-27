package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class LocalUserModelTable extends BaseDBTable
{
  public ValueBuilder<Integer> id;

  public ValueBuilder<String> name;

  public ValueBuilder<String> password;


  public LocalUserModelTable()
  {
    super("localusermodel");
    id = addColumn("id", DataType.BIGINT, true);
    name = addColumn("name", DataType.VARCHAR);
    password = addColumn("password", DataType.VARCHAR);
  }
}
