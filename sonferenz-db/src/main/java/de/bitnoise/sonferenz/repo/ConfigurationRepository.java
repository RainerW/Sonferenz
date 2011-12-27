package de.bitnoise.sonferenz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import de.bitnoise.sonferenz.model.ConfigurationModel;
import de.bitnoise.sonferenz.model.StaticContentModel;

public interface ConfigurationRepository extends JpaRepository<ConfigurationModel, Integer>
{
  ConfigurationModel findByName(String key);
}
