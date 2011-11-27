package de.bitnoise.sonferenz.repo.testutil.tables;

import org.dbunit.dataset.datatype.DataType;

import de.bitnoise.sonferenz.repo.testutil.builder.BaseDBTable;
import de.bitnoise.sonferenz.repo.testutil.builder.ValueBuilder;

public class ConferenceTable extends BaseDBTable
{

  public ValueBuilder<Integer> id;

  public ValueBuilder<String> shortName;

  public ValueBuilder<String> title;

  public ValueBuilder<String> description;

  public ConferenceTable()
  {
    super("conferencemodel");
    id = addColumn("id", DataType.BIGINT, true);
    shortName = addColumn("shortName", DataType.VARCHAR);
    title = addColumn("title", DataType.VARCHAR);
    description = addColumn("description", DataType.VARCHAR);
  }

}
