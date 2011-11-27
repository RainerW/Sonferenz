package de.bitnoise.sonferenz.web.pages.timetable;

import org.apache.wicket.markup.html.panel.Panel;

import com.visural.wicket.aturl.At;

import de.bitnoise.sonferenz.web.pages.KonferenzPage;

@At(url = "/timetable")
public class TimeTablePage extends KonferenzPage
{
  @Override
  protected Panel getPageContent(String id)
  {
      return new TimeTablePanel(id);
  }

}
