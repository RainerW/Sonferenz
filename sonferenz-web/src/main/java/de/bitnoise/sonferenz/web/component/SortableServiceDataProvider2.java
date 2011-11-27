package de.bitnoise.sonferenz.web.component;
//package de.bitnoise.sonferenz.web.component;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
//import org.apache.wicket.model.IModel;
//import org.apache.wicket.model.Model;
//
//import de.bitnoise.sonferenz.dao.base.PageableDao;
//import de.bitnoise.sonferenz.service.exceptions.KonferenzServiceException;
//
//public abstract class SortableServiceDataProvider2<TYPE_DB, TYPE_UI extends Serializable>
//    extends SortableDataProvider<TYPE_UI>
//{
//  private ListProvider<TYPE_DB> _provider;
//
//  public SortableServiceDataProvider2(ListProvider<TYPE_DB> provider)
//  {
//    super();
//    _provider = provider;
//  }
//  
//
//  public Iterator<? extends TYPE_UI> iterator(int first, int count)
//  {
//    boolean ascending = true;
//    String prop = null;
//    if (getSort() != null)
//    {
//      ascending = getSort().isAscending();
//      prop = getSort().getProperty();
//    }
//    List<TYPE_UI> daten = loadListFromBackend(first, count, ascending, prop);
//    return daten.iterator();
//  }
//
//  protected List<TYPE_UI> loadListFromBackend(int first, int count,
//      boolean ascending, String propertyToSort)
//      throws KonferenzServiceException
//  {
//    List<TYPE_DB> rawUsers = _provider.getAllItemList(first, count,
//        propertyToSort, ascending);
//    List<TYPE_UI> result = new ArrayList<TYPE_UI>();
//    for (TYPE_DB rawUser : rawUsers)
//    {
//      TYPE_UI conf = transferType(rawUser);
//      if (conf != null)
//      {
//        result.add(conf);
//      }
//    }
//    return result;
//  }
//
//  public int size()
//  {
//    return _provider.getAllItemCount();
//  }
//
//  public IModel<TYPE_UI> model(TYPE_UI object)
//  {
//    return Model.of(object);
//  }
//
//  abstract protected TYPE_UI transferType(TYPE_DB dbObject);
//
//
//}
