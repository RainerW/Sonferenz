package de.bitnoise.sonferenz.service.v2.services.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.fest.assertions.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import de.bitnoise.sonferenz.model.ConfigurationModel;
import de.bitnoise.sonferenz.repo.ConfigurationRepository;
import de.bitnoise.sonferenz.service.v2.BaseTest;

public class ConfigurationFromDbImplTest extends BaseTest
{

  ConfigurationFromDbImpl sut;
  ConfigurationRepository repo;

  @Before
  public void setup()
  {
    repo = strictMock(ConfigurationRepository.class);
    sut = new ConfigurationFromDbImpl();
    sut.repo = repo;
  }

  @Test
  public void getIntegerValue_p1_GetOne()
  {
    // prepare
    ConfigurationModel model = new ConfigurationModel();
    model.setValueString("1");
    when(repo.findByName("Rainer")).thenReturn(model);

    // call
    Integer result = sut.getIntegerValue("Rainer");

    // verify
    assertThat(result).isEqualTo(1);
  }

  @Test
  public void saveStringValue_p1_SaveOne()
  {
    // prepare
    ConfigurationModel model = new ConfigurationModel();
    when(repo.findByName("Rainer")).thenReturn(model);

    // call
    sut.saveStringValue("Rainer", "Wert");

    // verify
    verify(repo).save(model);
    assertThat(model.getValueString()).isEqualTo("Wert");
  }

}
