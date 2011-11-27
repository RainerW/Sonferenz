package de.bitnoise.sonferenz.web.pages.whish;

import org.apache.wicket.PageParameters;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.visural.wicket.aturl.At;

import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.WhishModel;
import de.bitnoise.sonferenz.web.pages.KonferenzPage;
import de.bitnoise.sonferenz.web.pages.whish.action.EditOrViewWhish;

@At(url = "/need")
public class ViewWhishPage extends KonferenzPage
{
  public static final String PARAM_ID = "id";
  
  @SpringBean
  private UiFacade facade;

  public ViewWhishPage(PageParameters parameters)
  {
    super();
    int id = parameters.getInt(PARAM_ID);
    WhishModel whish = facade.getWhishById(id);
    Model model = new Model(whish);
    setResponsePage(new EditOrViewWhish().doAction(model));
  }
}
