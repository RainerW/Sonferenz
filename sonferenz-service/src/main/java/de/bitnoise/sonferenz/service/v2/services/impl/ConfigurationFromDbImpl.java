package de.bitnoise.sonferenz.service.v2.services.impl;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.bitnoise.sonferenz.model.ConfigurationModel;
import de.bitnoise.sonferenz.repo.ConfigurationRepository;
import de.bitnoise.sonferenz.service.v2.exceptions.ConfigurationError;
import de.bitnoise.sonferenz.service.v2.services.ConfigurationService;

@Service
public class ConfigurationFromDbImpl implements ConfigurationService
{
  @Autowired
  ConfigurationRepository repo;

  @Override
  @Transactional(readOnly = true)
  public String getStringValue(String... keysToSearch)
  {
    String value = getNextValue(keysToSearch);
    if (value == null)
    {
      throw new ConfigurationError("The Config key was not accessible "
          + Arrays.toString(keysToSearch));
    }
    return value;
  }

  @Override
  public Page<ConfigurationModel> getAllConfigurations(PageRequest request)
  {
    return repo.findAll(request);
  }

  protected String getNextValue(String... keysToSearch)
  {
    for (String key : keysToSearch)
    {
      ConfigurationModel row = repo.findByName(key);
      if (row == null)
      {
        continue;
      }
      String raw = row.getValueString();
      return raw;
    }
    return null;
  }

  @Override
  public Integer getIntegerValue(String... keysToSearch)
  {
    String value = getNextValue(keysToSearch);
    if (value == null)
    {
      throw new ConfigurationError("The Config key was not accessible "
          + Arrays.toString(keysToSearch));
    }
    try
    {
      return Integer.valueOf(value);
    }
    catch (NumberFormatException nfe)
    {
      throw new ConfigurationError("The Config key was not a integer "
          + value);
    }
  }

  @Override
  public Integer getIntegerValue(int defaultValue, String... keysToSearch)
  {
    String value = getNextValue(keysToSearch);
    if (value == null)
    {
      return defaultValue;
    }
    try
    {
      return Integer.valueOf(value);
    }
    catch (NumberFormatException nfe)
    {
      return defaultValue;
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isAvaiable(String... keysToSearch)
  {
    String value = getNextValue(keysToSearch);
    return value != null;
  }

  @Override
  public void initValue(String key, Integer value)
  {
    doInitValue(key, value);
  }
  
  @Override
  public void initValue(String key, String value)
  {
    doInitValue(key, value);
  }

  private void doInitValue(String key, Object value)
  {
    if (repo.findByName(key) == null)
    {
      ConfigurationModel config = new ConfigurationModel();
      config.setDataType(value.getClass().getSimpleName());
      config.setName(key);
      config.setValueString(value.toString());
      repo.save(config);
    }
  }

}
