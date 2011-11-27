package de.bitnoise.sonferenz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bitnoise.sonferenz.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>
{
  UserModel findByName(String Name);
}
