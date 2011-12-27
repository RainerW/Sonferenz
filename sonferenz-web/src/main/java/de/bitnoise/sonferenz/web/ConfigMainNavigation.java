/*****************************************
 * Quelltexte zum Buch: Praxisbuch Wicket
 * (http://www.hanser.de/978-3-446-41909-4)
 * 
 * Autor: Michael Mosmann (michael@mosmann.de)
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
import de.bitnoise.sonferenz.web.component.state.IsActiveConference;
import de.bitnoise.sonferenz.web.component.state.IsLoggedIn;
import de.bitnoise.sonferenz.web.component.state.OnStateVoting;
import de.bitnoise.sonferenz.web.component.state.VisibleOnRights;
import de.bitnoise.sonferenz.web.pages.HomePage;
import de.bitnoise.sonferenz.web.pages.calculate.CalculateOverviewPage;
import de.bitnoise.sonferenz.web.pages.conference.ConferenceOverviewPage;
import de.bitnoise.sonferenz.web.pages.config.EditConfigurationPage;
import de.bitnoise.sonferenz.web.pages.config.EditTextePage;
import de.bitnoise.sonferenz.web.pages.config.EditUserRolesPage;
import de.bitnoise.sonferenz.web.pages.profile.MyProfilePage;
import de.bitnoise.sonferenz.web.pages.talks.TalksOverviewPage;
import de.bitnoise.sonferenz.web.pages.timetable.TimeTablePage;
import de.bitnoise.sonferenz.web.pages.users.UserOverviewPage;
import de.bitnoise.sonferenz.web.pages.voting.VotingOverviewPage;
import de.bitnoise.sonferenz.web.pages.whish.WhishOverviewPage;

public class ConfigMainNavigation
{
  public static List<NavCallbackInterface> getPages()
  {
    List<NavCallbackInterface> ret = new ArrayList<NavCallbackInterface>();
    ret.add(new PageNavCallback(HomePage.class, "Home", new AllwaysVisible()));

    // admin
    ret.add(new PageNavCallback(UserOverviewPage.class, "Users",
        new VisibleOnRights(Right.User.List)));
    ret.add(new PageNavCallback(ConferenceOverviewPage.class, "Conference",
        new VisibleOnRights(Right.Conference.List)));
    ret.add(new PageNavCallback(EditUserRolesPage.class, "Roles",
        new VisibleOnRights(Right.Admin.Configure)));
    ret.add(new PageNavCallback(EditConfigurationPage.class, "Config",
        new VisibleOnRights(Right.Admin.Configure)));
    ret.add(new PageNavCallback(EditTextePage.class, "Texte",
        new VisibleOnRights(Right.Admin.Configure)));

    // users
    ret.add(new PageNavCallback(WhishOverviewPage.class, "Whishes",
        new VisibleOnRights(Right.Whish.List)
        ));
    ret.add(new PageNavCallback(TalksOverviewPage.class, "Talks",
        new VisibleOnRights(Right.Talk.List),
        new IsActiveConference()
        ));
    ret.add(new PageNavCallback(VotingOverviewPage.class, "Voting",
        new VisibleOnRights(Right.Vote.canVote),
        new IsActiveConference(),
        new OnStateVoting()
        ));
    ret.add(new PageNavCallback(CalculateOverviewPage.class, "Calculate",
        new VisibleOnRights(Right.Admin.ViewCalculation),
        new IsActiveConference(),
        new OnStateVoting()
        ));
    ret.add(new PageNavCallback(MyProfilePage.class, "MyProfile",
        new IsLoggedIn()
        ));
    ret.add(new PageNavCallback(TimeTablePage.class, "Timetable"));
    return ret;
  }

  public static NavCallbackInterface getMainNaviagtion()
  {
    return new AbstractNavCallback(null)
    {
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
