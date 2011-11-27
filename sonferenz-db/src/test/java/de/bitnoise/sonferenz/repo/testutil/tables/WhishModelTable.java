package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class WhishModelTable extends BaseDBTable
{
  public ValueBuilder<Integer> id;
  public ValueBuilder<String> title;
  public ValueBuilder<Integer> ownerId;
  public ValueBuilder<Integer> likes;
  public ValueBuilder<String> description;

  public WhishModelTable()
  {
    super("whishmodel");
    id = addColumn("id", DataType.BIGINT, true);
    title = addColumn("title", DataType.VARCHAR);
    ownerId = addColumn("owner_id", DataType.BIGINT);
    likes = addColumn("likes", DataType.BIGINT);
    description = addColumn("description", DataType.LONGVARCHAR);
  }
}
