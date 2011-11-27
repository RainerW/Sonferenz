package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class TalkTable extends BaseDBTable
{

  public ValueBuilder<Integer> id;

  public ValueBuilder<String> autor;

  public ValueBuilder<String> description;

  public ValueBuilder<String> title;

  public ValueBuilder<Integer> owner;

  public ValueBuilder<Integer> conference;

  public TalkTable()
  {
    super("talkmodel");
    id = addColumn("id", DataType.BIGINT, true);
    autor = addColumn("author", DataType.VARCHAR);
    description = addColumn("description", DataType.VARCHAR);
    title = addColumn("title", DataType.VARCHAR);
    owner = addColumn("owner_id", DataType.BIGINT);
    conference = addColumn("conference_id", DataType.BIGINT);
  }

}
