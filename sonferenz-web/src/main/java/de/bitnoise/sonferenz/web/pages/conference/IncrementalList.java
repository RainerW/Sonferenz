package de.bitnoise.sonferenz.web.pages.conference;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class IncrementalList<E> extends AbstractList<E> implements List<E>,
    Serializable
{
  List<E> _current = new ArrayList<E>();
  List<E> _removed = new ArrayList<E>();
  ArrayList<E> _original;

  public IncrementalList(List<E> cur)
  {
    _current = cur;
    _original = new ArrayList<E>(cur);
  }

  @Override
  public E get(int index)
  {
    return _current.get(index);
  }

  @Override
  public int size()
  {
    return _current.size();
  }

  public void add(int index, E element)
  {
    _current.add(index, element);
  }

  public E remove(int index)
  {
    E old = _current.get(index);
    _removed.add(old);
    return _current.remove(index);
  }

  public List<E> getRemovedItems()
  {
    List<E> temp = new ArrayList<E>();
    for (E obj : _original)
    {
      if (!_current.contains(obj))
      {
        temp.add(obj);
      }
    }
    return temp;
  }

  public List<E> getAllItems()
  {
    return _current;
  }
}
