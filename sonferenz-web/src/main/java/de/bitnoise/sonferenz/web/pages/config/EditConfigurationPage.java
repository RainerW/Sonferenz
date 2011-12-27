package de.bitnoise.sonferenz.web.pages.config;

import org.apache.wicket.markup.html.panel.Panel;

import com.visural.wicket.aturl.At;

import de.bitnoise.sonferenz.web.pages.KonferenzPage;

@At(url = "/admin/configuration")
public class EditConfigurationPage extends KonferenzPage
{
  @Override
  protected Panel getPageContent(String id)
  {
    return new ListConfigPanel(id);
  }
}
