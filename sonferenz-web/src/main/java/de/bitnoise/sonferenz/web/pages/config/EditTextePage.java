package de.bitnoise.sonferenz.web.pages.config;

import org.apache.wicket.markup.html.panel.Panel;

import de.bitnoise.sonferenz.web.pages.KonferenzPage;

public class EditTextePage extends KonferenzPage
{
  @Override
  protected Panel getPageContent(String id)
  {
    return new ListTextePanel(id);
  }
}
