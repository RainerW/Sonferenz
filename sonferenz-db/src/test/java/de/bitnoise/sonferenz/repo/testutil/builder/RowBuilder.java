package de.bitnoise.sonferenz.repo.testutil.builder;

public class RowBuilder<ID_TYPE>
{
  private Integer _idxIdColumn;

  Object[] _values;

  public RowBuilder(int size)
  {
    _values = new Object[size];
  }
  
  public RowBuilder(int size, Integer idColumn)
  {
    this(size);
    _idxIdColumn = idColumn;
  }

  public void setValue(int index, Object value)
  {
    _values[index] = value;
  }

  public Object[] getValues()
  {
    return _values;
  }

  public Object getValue(int index)
  {
    return _values[index] ;
  }
  

  @SuppressWarnings("unchecked")
  public ID_TYPE getId()
  {
    return (ID_TYPE) getValue(_idxIdColumn);
  }
}
