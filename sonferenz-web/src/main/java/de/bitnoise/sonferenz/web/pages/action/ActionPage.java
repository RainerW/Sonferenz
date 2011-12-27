package de.bitnoise.sonferenz.web.pages.action;

import java.util.Map;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.visural.wicket.aturl.At;
import com.visural.wicket.aturl.URLType;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.sonferenz.Right;
import de.bitnoise.sonferenz.facade.UiFacade;
import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.service.actions.ActionData;
import de.bitnoise.sonferenz.service.actions.Aktion;
import de.bitnoise.sonferenz.web.pages.KonferenzPage;
import de.bitnoise.sonferenz.web.pages.UnauthorizedPanel;
import de.bitnoise.sonferenz.web.pages.conference.EditConferenceWizard;
import de.bitnoise.sonferenz.web.pages.conference.ListConferencesPanel;

@At(url = "/action", type = URLType.Indexed)
public class ActionPage extends KonferenzPage
{
  String action;

  String token;

  @SpringBean
  UiFacade facade;

  public ActionPage(PageParameters params)
  {
    super(params);
    if (params.size() == 2)
    {
      action = params.getString("0", null);
      token = params.getString("1", null);
    }
  }

  @Override
  protected Panel getPageContent(String id)
  {
    Panel panel = validate(action, token);
    if (panel == null)
    {
      return new UnauthorizedPanel(id);
    }
    return new EmptyPanel(id);
  }

  Panel validate(String action, String token)
  {
    Aktion aktion = facade.validateAction(action,token);
    return null;
  }
}
