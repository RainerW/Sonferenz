package de.bitnoise.sonferenz.web.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.visural.wicket.behavior.beautytips.BeautyTipBehavior;
import com.visural.wicket.behavior.beautytips.TipPosition;

import de.bitnoise.sonferenz.web.action.IWebAction;
import de.bitnoise.sonferenz.web.component.table.ClickableTablePropertyColumn;

public class TableBuilder<S>
{
  private List<TableBuilder.Column> _columns = new ArrayList<TableBuilder.Column>();
  private String _prefix;

  public TableBuilder(String resourceTableName)
  {
    _prefix = resourceTableName;
  }

  protected void addColumn(Column column)
  {
    _columns.add(column);
  }

  protected void add(IColumn<S> colModel)
  {
    _columns.add(new Column(colModel));
  }

  public List<IColumn<S>> getColumns()
  {
    List<IColumn<S>> result = new ArrayList<IColumn<S>>();
    for (Column column : _columns)
    {
      IColumn<S> newColumn;
      Boolean escape = column._escaping;
      if (escape == null)
      {
        escape = true;
      }
      IModel<String> hintText = null;
      if (column._showHint)
      {
        hintText = createText("hint", column);
      }
      
      
      if (column._column != null)
      {
        newColumn = column._column;
      }
      else if (column._action != null)
      {
        if (column._PropSort != null)
        {
          newColumn = new ClickableTablePropertyColumn<S>(createTitle(column),
              column._PropKey, column._PropSort, column._action);
        }
        else
        {
          newColumn = new ClickableTablePropertyColumn<S>(createTitle(column),
              column._PropKey, column._action);
        }
      }
      else if (column._PropSort != null)
      {
        newColumn = new PropertyColumn2<S>(createTitle(column),
            column._PropSort, column._PropKey, escape, hintText);
      }
      else
      {
        newColumn = new PropertyColumn2<S>(createTitle(column),
            column._PropKey, escape, hintText);
      }
      result.add(newColumn);
    }
    return result;
  }

  protected static class PropertyColumn2<T> extends PropertyColumn<T>
  {
    private IModel<String> _hint;

    public PropertyColumn2(IModel<String> displayModel,
        String propertyExpression, boolean escapen, IModel<String> hintText)
    {
      super(displayModel, propertyExpression);
      escapeMarkup = escapen;
      _hint = hintText;
    }

    public PropertyColumn2(IModel<String> displayModel, String sortProperty,
        String propertyExpression, boolean escapen, IModel<String> hintText)
    {
      super(displayModel, sortProperty, propertyExpression);
      escapeMarkup = escapen;
      _hint = hintText;
    }

    private boolean escapeMarkup;

    public Component getHeader(String componentId)
    {
      Label hdr = new Label(componentId, getDisplayModel());
      if (_hint != null)
      {
        hdr.add(new BeautyTipBehavior(_hint)
            .setPositionPreference(TipPosition.bottom));
      }
      return hdr;
    }

    public void populateItem(Item<ICellPopulator<T>> item, String componentId,
        IModel<T> rowModel)
    {
      item.add(new AttributeAppender("class", true, Model.of("column_"
          + item.getIndex()), " "));
      Label label = new Label(componentId, createLabelModel(rowModel));
      label.setEscapeModelStrings(escapeMarkup);
      item.add(label);
    }

  }

  private IModel<String> createTitle(Column column)
  {
    return createText("column", column);
  }

  private IModel<String> createText(String type, Column column)
  {
    String id = column._Title;
    return new StringResourceModel("table." + _prefix + "." + type + "." + id,
        Model.of(_prefix));
  }

  public class Column
  {
    String _Title;
    String _PropKey;
    String _PropSort;
    IWebAction<?> _action;
    IColumn<S> _column;
    Boolean _escaping;
    private boolean _showHint;

    public Column()
    {
    }

    public Column(IColumn<S> column)
    {
      _column = column;
    }

    protected void setEscaping(boolean escape)
    {
      _escaping = escape;
    }

    protected void setTitle(String title)
    {
      _Title = title;
    }

    protected void setHint(boolean b)
    {
      _showHint = b;
    }

    protected void setModelProperty(String propKey)
    {
      _PropKey = propKey;
    }

    protected void sortable()
    {
      _PropSort = _PropKey;
    }

    protected void sortable(String propKey)
    {
      _PropSort = propKey;
    }

    protected void action(IWebAction<?> action)
    {
      _action = action;
    }
  }
}
