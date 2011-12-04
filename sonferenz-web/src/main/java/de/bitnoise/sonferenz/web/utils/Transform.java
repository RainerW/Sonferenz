package de.bitnoise.sonferenz.web.utils;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.markup.repeater.util.ModelIteratorAdapter;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


public class Transform
{

  public static <T extends Serializable> Iterator<IModel<T>> iteratorToModel(Iterator<T> iterator)
  {
    return new ModelIteratorAdapter<T>(iterator) {
      @Override
      protected IModel<T> model(T object)
      {
        return Model.of(object);
      }
    };
  }

}
