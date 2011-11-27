package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class StaticContentTable extends BaseDBTable
{

  public ValueBuilder<Integer> id;

  public ValueBuilder<String> name;
  
  public ValueBuilder<String> html;

  public StaticContentTable()
  {
    super("staticcontentmodel");
    id = addColumn("id", DataType.BIGINT, true);
    name = addColumn("name", DataType.VARCHAR);
    html = addColumn("html", DataType.VARCHAR);
  }

}
