package de.bitnoise.sonferenz.web.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.model.WhishModel;
import de.bitnoise.sonferenz.web.pages.home.NoUserLoggedInPanel;
import de.bitnoise.sonferenz.web.pages.home.UserLoggedInPanel;
import de.bitnoise.sonferenz.web.pages.whish.action.EditOrViewWhish;

public class HomePage extends KonferenzPage
{
  private static final long serialVersionUID = 1L;

  @SpringBean
  protected UiFacade facade;

  public HomePage(PageParameters parameters)
  {
    super();
    if (parameters != null)
    {
      int id = parameters.getInt("conference", -1);
      if (id != -1)
      {
        ConferenceModel conference = facade.getConference(id);
        KonferenzSession.get().setCurrentConference(conference);
      }
    }
  }

  @Override
  protected Panel getPageContent(String id)
  {
    if (KonferenzSession.noUserLoggedIn())
    {
      return new NoUserLoggedInPanel(id);
    }
    else
    {
      return new UserLoggedInPanel(id);
    }
  }

  public static PageParameters createParameters(ConferenceModel iModel)
  {
    PageParameters pp = new PageParameters();
    pp.add("conference", String.valueOf(iModel.getId()));
    return pp;
  }
}
