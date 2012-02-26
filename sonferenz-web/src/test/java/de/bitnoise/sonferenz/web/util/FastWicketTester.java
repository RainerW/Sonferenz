package de.bitnoise.sonferenz.web.util;

import static org.mockito.Mockito.when;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;

import de.bitnoise.sonferenz.KonferenzSession;
import de.bitnoise.testing.mockito.MockitoRule;

public class FastWicketTester
{

  @Rule
  public MockitoRule mock = new MockitoRule();
  
  @Mock
  public KonferenzSession session;
  
  protected WicketTester tester;

  @Before
  public void setupWicketTester()
  {
    tester = new WicketTester();
    KonferenzSession.setMock(session);
  }

}
