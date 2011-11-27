package de.bitnoise.sonferenz.web.action;

import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

import de.bitnoise.sonferenz.web.component.navigation.NavCallbackInterface;
import de.bitnoise.sonferenz.web.pages.KonferenzPage;

public abstract class WebMenuAction<T extends IModel<?>> implements
    NavCallbackInterface, IWebAction<T>
{
  private String _title;
  private Object _activeState;

  public WebMenuAction(String title, Object actionState)
  {
    _activeState = actionState;
    _title = title;
  }

  public String getName()
  {
    return _title;
  }

  public List<NavCallbackInterface> getChilds(Page page)
  {
    return null;
  }
  
  public boolean isVisible() {return true;}

  public boolean isActive(Page page)
  {
    if (page instanceof KonferenzPage && _activeState != null)
    {
      Object currentAction = ((KonferenzPage) page).getCurrentAction();
      if (_activeState.equals(currentAction))
      {
        return true;
      }
    }
    return false;
  }

  public void onClick(Page page)
  {
    page.setResponsePage(doAction(null));
  }

}
