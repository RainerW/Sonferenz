package de.bitnoise.sonferenz.service.v2.services.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;


import de.bitnoise.sonferenz.model.StaticContentModel;
import de.bitnoise.sonferenz.repo.StaticContentRepository;
import de.bitnoise.sonferenz.service.v2.exceptions.GeneralConferenceException;
import de.bitnoise.sonferenz.service.v2.services.impl.StaticContentService2Impl;

public class StaticContentFacadeImplTest
{
  StaticContentRepository repoMock = mock(StaticContentRepository.class);
  StaticContentModel _content = new StaticContentModel() {
    {
      setHtml("The HTML");
    }
  };
  StaticContentService2Impl sut = new StaticContentService2Impl() {
    {
      repo = repoMock;
    }
  };

  @Rule
  public ExpectedException expectException = ExpectedException.none();

  @Test
  public void testText_Found()
  {
    // prepare
    when(repoMock.findByName("key.id")).thenReturn(_content);

    // execute
    String result = sut.text("key.id");

    // verify
    assertThat(result).isEqualTo("The HTML");
  }

  @Test
  public void testText_ParamEmpty()
  {
    // prepare
    when(repoMock.findByName("")).thenReturn(null);

    // execute
    String result = sut.text("");

    // verify
    assertThat(result).isNull();
  }

  @Test
  public void testText_ParamNull()
  {
    // prepare
    when(repoMock.findByName(null)).thenReturn(null);

    // execute
    String result = sut.text(null);

    // verify
    assertThat(result).isNull();
  }

  @Test
  public void testText_NotFound()
  {
    // prepare
    when(repoMock.findByName("key.id")).thenReturn(null);

    // execute
    String result = sut.text("key.id");

    // verify
    assertThat(result).isNull();
  }

  @Test
  public void testText_Fail()
  {
    // prepare
    when(repoMock.findByName("key.id")).thenThrow(RuntimeException.class);

    // expect exception
    expectException.expect(GeneralConferenceException.class);

    // execute
    sut.text("key.id");
  }

  @Test
  public void testText2_Found()
  {
    // prepare
    when(repoMock.findByName("key.id")).thenReturn(_content);

    // execute
    String result = sut.text("key.id", "fallback");

    // verify
    assertThat(result).isEqualTo("The HTML");
  }

  @Test
  public void testText2_Param1Empty()
  {
    // prepare
    when(repoMock.findByName("")).thenReturn(null);

    // execute
    String result = sut.text("", "fallback");

    // verify
    assertThat(result).isEqualTo("fallback");
  }

  @Test
  public void testText2_Param1Null()
  {
    // prepare
    when(repoMock.findByName(null)).thenReturn(null);

    // execute
    String result = sut.text(null, "fallback");

    // verify
    assertThat(result).isEqualTo("fallback");
  }

  @Test
  public void testText2_Param2Empty()
  {
    // prepare
    when(repoMock.findByName("")).thenReturn(null);

    // execute
    String result = sut.text("key.id", "");

    // verify
    assertThat(result).isEqualTo("");
  }

  @Test
  public void testText2_Param2Null()
  {
    // prepare
    when(repoMock.findByName(eq("key.id"))).thenReturn(null);

    // execute
    String result = sut.text("key.id", null);

    // verify
    assertThat(result).isEqualTo(null);

    verify(repoMock).findByName("key.id");
  }

  @Test
  public void testText2_NotFound()
  {
    // prepare
    when(repoMock.findByName("key.id")).thenReturn(null);

    // execute
    String result = sut.text("key.id", "fallback");

    // verify
    assertThat(result).isEqualTo("fallback");
  }

  @Test
  public void testText2_Fail()
  {
    // prepare
    when(repoMock.findByName("key.id")).thenThrow(RuntimeException.class);

    // expect exception
    expectException.expect(GeneralConferenceException.class);

    // execute
    sut.text("key.id", "fallback");
  }
  
}
