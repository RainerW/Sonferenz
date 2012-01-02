package de.bitnoise.sonferenz.service.v2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import de.bitnoise.sonferenz.model.ConfigurationModel;
import de.bitnoise.sonferenz.repo.ConfigurationRepository;

public interface ConfigurationService
{
  public String getStringValue(String... keysToSearch);
  
  public Boolean isAvaiable(String... keysToSearch);

  public Page<ConfigurationModel> getAllConfigurations(PageRequest request);

  public Integer getIntegerValue(String... keysToSearch);

  Integer getIntegerValue(int defaultValue, String... keysToSearch);
}
