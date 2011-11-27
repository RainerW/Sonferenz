/*****************************************
Quelltexte zum Buch: Praxisbuch Wicket
(http://www.hanser.de/978-3-446-41909-4)

Autor: Michael Mosmann
(michael@mosmann.de)
 *****************************************/
package de.bitnoise.sonferenz.web.component.confpan;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;


import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.service.v2.exceptions.GeneralConferenceException;
import de.bitnoise.sonferenz.web.pages.HomePage;

public class CurrentConferencePanel extends Panel
{
  public CurrentConferencePanel(String id)
  {
    super(id);
    initPanel();
  }

  @SpringBean
  UiFacade facade;
  
  protected void initPanel()
  {
    try
    {
      ConferenceModel conf = facade.getActiveConference();
      if (conf != null)
      {
        createContent(conf.getShortName(), HomePage.class);
      }
      else
      {
        createContent(" ", HomePage.class);
      }
    }
    catch (GeneralConferenceException e)
    {
      e.printStackTrace();
    }
  }

  private void createContent(String linkText,
      Class<? extends WebPage> linkTarget)
  {
    BookmarkablePageLink<WebPage> link = new BookmarkablePageLink<WebPage>(
        "link", linkTarget);
    add(link);
    link.add(new Label("name", Model.of(linkText)));
  }
}
