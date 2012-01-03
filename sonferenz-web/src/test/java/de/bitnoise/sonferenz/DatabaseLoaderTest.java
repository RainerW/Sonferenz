package de.bitnoise.sonferenz;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;


public class DatabaseLoaderTest
{
  DatabaseLoader sut = new DatabaseLoader();
  
  @Test
  public void loadStringResource2_validName() {
    String result = sut.loadStringResource(new Panel("22"), "key");
  }
}
