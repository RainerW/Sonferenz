package de.bitnoise.sonferenz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bitnoise.sonferenz.model.LocalUserModel;

public interface LocalUserRepository extends JpaRepository<LocalUserModel, Integer>
{
  LocalUserModel findByName(String name);
}
