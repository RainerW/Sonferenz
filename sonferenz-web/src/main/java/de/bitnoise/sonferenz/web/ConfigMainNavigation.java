/*****************************************
Quelltexte zum Buch: Praxisbuch Wicket
(http://www.hanser.de/978-3-446-41909-4)

Autor: Michael Mosmann
(michael@mosmann.de)
 *****************************************/
package de.bitnoise.sonferenz.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;

import de.bitnoise.sonferenz.Right;
import de.bitnoise.sonferenz.web.component.navigation.AbstractNavCallback;
import de.bitnoise.sonferenz.web.component.navigation.NavCallbackInterface;
import de.bitnoise.sonferenz.web.component.navigation.PageNavCallback;
import de.bitnoise.sonferenz.web.component.state.AllwaysVisible;
import de.bitnoise.sonferenz.web.component.state.OnStateVoting;
import de.bitnoise.sonferenz.web.component.state.VisibleOnRights;
import de.bitnoise.sonferenz.web.pages.HomePage;
import de.bitnoise.sonferenz.web.pages.auth.LoginPage;
import de.bitnoise.sonferenz.web.pages.auth.LogoutPage;
import de.bitnoise.sonferenz.web.pages.calculate.CalculateOverviewPage;
import de.bitnoise.sonferenz.web.pages.conference.ConferenceOverviewPage;
import de.bitnoise.sonferenz.web.pages.talks.TalksOverviewPage;
import de.bitnoise.sonferenz.web.pages.timetable.TimeTablePage;
import de.bitnoise.sonferenz.web.pages.timetable.TimeTablePanel;
import de.bitnoise.sonferenz.web.pages.users.UserOverviewPage;
import de.bitnoise.sonferenz.web.pages.voting.VotingOverviewPage;
import de.bitnoise.sonferenz.web.pages.whish.WhishOverviewPage;

public class ConfigMainNavigation
{
  public static List<NavCallbackInterface> getPages()
  {
    List<NavCallbackInterface> ret = new ArrayList<NavCallbackInterface>();
    ret.add(new PageNavCallback(HomePage.class, "Home", new AllwaysVisible()));
    ret.add(new PageNavCallback(UserOverviewPage.class, "Users",
        new VisibleOnRights(Right.User.List)));
    ret.add(new PageNavCallback(ConferenceOverviewPage.class, "Conference",
        new VisibleOnRights(Right.Conference.List)));
    ret.add(new PageNavCallback(WhishOverviewPage.class, "Whishes",
        new VisibleOnRights(Right.Whish.List)));
    ret.add(new PageNavCallback(TalksOverviewPage.class, "Talks",
        new VisibleOnRights(Right.Talk.List)));
    ret.add(new PageNavCallback(VotingOverviewPage.class, "Voting",
        new VisibleOnRights(Right.Vote.canVote), new OnStateVoting()));
    ret.add(new PageNavCallback(CalculateOverviewPage.class, "Calculate",
        new VisibleOnRights(Right.Admin.ViewCalculation), new OnStateVoting()));
    ret.add(new PageNavCallback(TimeTablePage.class, "Timetable"));
    return ret;
  }

  public static NavCallbackInterface getMainNaviagtion()
  {
    return new AbstractNavCallback(null) {
      public List<NavCallbackInterface> getChilds(Page page)
      {
        return getPages();
      }

      public void onClick(Page page)
      {
      }
    };
  };
}
