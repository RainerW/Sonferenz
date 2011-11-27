package de.bitnoise.sonferenz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import de.bitnoise.sonferenz.model.StaticContentModel;

public interface StaticContentRepository extends JpaRepository<StaticContentModel, Integer>
{
  StaticContentModel findByName(String key);
}
