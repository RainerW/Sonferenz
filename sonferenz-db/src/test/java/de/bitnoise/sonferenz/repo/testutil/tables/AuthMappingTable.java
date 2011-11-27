package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class AuthMappingTable extends BaseDBTable
{

  public ValueBuilder<Integer> id;

  public ValueBuilder<String> authType;

  public ValueBuilder<String> authId;

  public AuthMappingTable()
  {
    super("authmapping");
    id = addColumn("id", DataType.BIGINT, true);
    authType = addColumn("authType", DataType.VARCHAR);
    authId = addColumn("authId", DataType.VARCHAR);
  }

}
