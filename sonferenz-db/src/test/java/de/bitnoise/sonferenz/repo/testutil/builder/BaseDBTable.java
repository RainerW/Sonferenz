package de.bitnoise.sonferenz.repo.testutil.builder;

import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.DataType;

public class BaseDBTable
{
  String _name;

  List<Column> _columns = new ArrayList<Column>();

  List<RowBuilder> _tableRows = new ArrayList<RowBuilder>();

  DefaultTable _table;

  int _index = 0;

  RowBuilder _currentRow;

  boolean _initComplete;

  int indexColumn;

  Integer _indexColumn;

  public BaseDBTable(String name)
  {
    _name = name;
  }

  public RowBuilder addRow()
  {
    _initComplete = true;
    RowBuilder row = new RowBuilder (_columns.size(), _indexColumn);
    _tableRows.add(row);
    _currentRow = row;
    return row;
  }

  public void removeRow(RowBuilder row)
  {
    _tableRows.remove(row);
  }

  public void select(RowBuilder row)
  {
    _currentRow = row;
  }

  protected <TYPE> ValueBuilder<TYPE> addColumn(String name, DataType type,
      boolean primary)
  {
    _indexColumn = _index;
    ValueBuilder<TYPE> builder = addColumn(name, type);
    return builder;
  }

  protected <TYPE> ValueBuilder<TYPE> addColumn(String name, DataType type)
  {
    if (_initComplete)
    {
      throw new RuntimeException("Error addRow() was allready called");
    }
    ValueBuilder<TYPE> builder = new ValueBuilder<TYPE>(this, _index++);
    _columns.add(new Column(name, type));
    return builder;
  }

  public void setValue(int index, Object value)
  {
    _currentRow.setValue(index, value);
  }

  public ITable getTable()
  {
    DefaultTable table = new DefaultTable(_name, _columns.toArray(new Column[]
      {}));
    for (RowBuilder row : _tableRows)
    {
      try
      {
        table.addRow(row.getValues());
      }
      catch (DataSetException e)
      {
        throw new RuntimeException(e);
      }
    }
    return table;
  }

  public Object getValue(int index)
  {
    return _currentRow.getValue(index);
  }
}
