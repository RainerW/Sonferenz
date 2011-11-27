package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class LikeModelTable extends BaseDBTable
{
  public ValueBuilder<Integer> id;

  public ValueBuilder<Integer> likes;

  public ValueBuilder<Integer> userId;

  public ValueBuilder<Integer> whishId;

  public LikeModelTable()
  {
    super("likemodel");
    id = addColumn("id", DataType.BIGINT, true);
    likes = addColumn("likes", DataType.BIGINT);
    userId = addColumn("user_id", DataType.BIGINT);
    whishId = addColumn("whish_id", DataType.BIGINT);
  }
}
