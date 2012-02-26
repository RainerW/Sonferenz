package de.bitnoise.sonferenz.web.v2.page.login;

import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import de.bitnoise.sonferenz.web.v2.page.root.BasePage;

public class LoginPageTest extends FastWicketTester
{
  @Before
  public void setup()
  {
    when(session.authenticate("username", "userpwd")).thenReturn(null);
    when(session.authenticate("username-wrong", "userpwd")).thenReturn("invalid username");
    when(session.authenticate("username", "userpwd-wrong")).thenReturn("invalid password");
  }

  @Test
  public void testSuccessfull()
  {
    LoginPage sut = new LoginPage();
    tester.startPage(sut);
    tester.assertNoErrorMessage();
    FormTester form = tester.newFormTester("form");
    form.setValue("username", "username");
    form.setValue("password", "userpwd");
    form.submit();
    tester.assertNoErrorMessage();
    tester.assertRenderedPage(BasePage.class);
  }

  @Test
  public void testInvalidUser()
  {
    LoginPage sut = new LoginPage();
    tester.startPage(sut);
    tester.assertNoErrorMessage();
    FormTester form = tester.newFormTester("form");
    form.setValue("username", "username-wrong");
    form.setValue("password", "userpwd");
    form.submit();
    tester.assertErrorMessages(new String[]
      { "Login failed : invalid username" });
    tester.assertRenderedPage(LoginPage.class);
  }

  @Test
  public void testInvalidPassword()
  {
    LoginPage sut = new LoginPage();
    tester.startPage(sut);
    tester.assertNoErrorMessage();
    FormTester form = tester.newFormTester("form");
    form.setValue("username", "username");
    form.setValue("password", "userpwd-wrong");
    form.submit();
    tester.assertErrorMessages(new String[]
      { "Login failed : invalid password" });
    tester.assertRenderedPage(LoginPage.class);
  }
}
