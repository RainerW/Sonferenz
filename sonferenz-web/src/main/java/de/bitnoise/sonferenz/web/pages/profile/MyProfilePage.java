package de.bitnoise.sonferenz.web.pages.profile;

import org.apache.wicket.markup.html.panel.Panel;

import com.visural.wicket.aturl.At;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.model.UserModel;
import de.bitnoise.sonferenz.web.pages.KonferenzPage;
import de.bitnoise.sonferenz.web.pages.UnauthorizedPanel;

@At(url = "/profile")
public class MyProfilePage extends KonferenzPage
{
  @Override
  protected Panel getPageContent(String id)
  {
    UserModel user = KonferenzSession.get().getCurrentUser();
    if (user != null)
    {
      return  new MyProfilePanel(id, user);
    }
    else
    {
      return new UnauthorizedPanel(id);
    }
  }
}
