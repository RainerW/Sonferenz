package de.bitnoise.sonferenz.web.component.drop;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

public abstract class DropDownEnumChoice<T> extends DropDownChoice<T>
{

  private List<T> _values = new ArrayList<T>();

  public DropDownEnumChoice(String id,IModel<T> model)
  {
    super(id);
    setChoices(_values);
    setChoiceRenderer(new EnumRenderer());
    setModel(model);
  }

  protected class EnumRenderer implements IChoiceRenderer
  {
    public Object getDisplayValue(Object methodObj)
    {
      return getString(methodObj.toString());
    }

    public String getIdValue(Object id, int index)
    {
      return id.toString();
    }
  }

  protected void addEnum(Class<T> class1)
  {
    if (!class1.isEnum())
    {
      throw new IllegalArgumentException("is not a enum");
    }
    T[] values = class1.getEnumConstants();
    addChoice(values);
  }

  protected void addChoice(T... values)
  {
    if (values != null)
    {
      for (T value : values)
      {
        _values.add(value);
      }
    }
  }
}
