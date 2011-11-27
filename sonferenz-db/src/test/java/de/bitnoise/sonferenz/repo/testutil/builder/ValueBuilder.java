package de.bitnoise.sonferenz.repo.testutil.builder;

import java.util.Date;


public class ValueBuilder<T>
{
  int _index;

  BaseDBTable _table;

  public ValueBuilder(BaseDBTable table, int index)
  {
    _index = index;
    _table = table;
  }

  public void set(T value)
  {
    _table.setValue(_index, value);
  }
  
  public void setNull()
  {
    set((T)null);
  }

  public void set(RowBuilder row)
  {
    T id = (T) row.getId();
    set(id);
  }

  public void setRaw(Object value)
  {
    _table.setValue(_index, value);
  }

  public RowSetter<T> forRow(RowBuilder row)
  {
    return new RowSetter<T>(this);
  }

  public static class RowSetter<T>
  {

    private ValueBuilder<T> _target;

    public RowSetter(ValueBuilder<T> cellBuilder)
    {
      _target = cellBuilder;
    }

    public void to(T value)
    {
      _target.set(value);
    }
  }


  @SuppressWarnings("unchecked")
  public T get(RowBuilder dbRow)
  {
    return (T) dbRow.getValue(_index);
  }

}
