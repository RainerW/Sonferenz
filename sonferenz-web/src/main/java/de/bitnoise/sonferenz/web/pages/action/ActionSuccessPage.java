package de.bitnoise.sonferenz.web.pages.action;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.panel.Panel;

import de.bitnoise.sonferenz.web.pages.KonferenzPage;
import de.bitnoise.sonferenz.web.pages.StaticContentEditPage;
import de.bitnoise.sonferenz.web.pages.StaticContentPanel;

public class ActionSuccessPage extends KonferenzPage
{
  @Override
  protected Panel getPageContent(String id)
  {
    return new StaticContentPanel(id, "action.success");
  }

}
