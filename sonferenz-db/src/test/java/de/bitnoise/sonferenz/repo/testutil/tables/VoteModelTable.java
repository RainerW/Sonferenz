package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class VoteModelTable extends BaseDBTable
{
  public ValueBuilder<Integer> id;
  public ValueBuilder<Integer> rating;
  public ValueBuilder<Integer> talkId;
  public ValueBuilder<Integer> userId;

  public VoteModelTable()
  {
    super("votemodel");
    id = addColumn("id", DataType.BIGINT, true);
    rating = addColumn("rateing", DataType.VARCHAR);
    talkId = addColumn("talk_id", DataType.VARCHAR);
    userId = addColumn("user_id", DataType.VARCHAR);
  }
}
