package de.bitnoise.sonferenz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bitnoise.sonferenz.model.ConferenceModel;

public interface ConferenceRepository extends JpaRepository<ConferenceModel, Integer>
{
  ConferenceModel findByActive(Boolean active);
}
