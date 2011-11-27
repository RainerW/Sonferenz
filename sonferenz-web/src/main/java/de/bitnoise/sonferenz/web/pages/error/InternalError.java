package de.bitnoise.sonferenz.web.pages.error;

import org.apache.wicket.markup.html.panel.Panel;

import de.bitnoise.sonferenz.web.pages.KonferenzPage;

public class InternalError extends KonferenzPage
{
  @Override
  protected Panel getPageContent(String id)
  {
    return new InernalErrorPane(id);
  }
}
