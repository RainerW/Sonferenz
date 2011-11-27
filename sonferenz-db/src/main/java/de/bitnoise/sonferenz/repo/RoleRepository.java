package de.bitnoise.sonferenz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bitnoise.sonferenz.model.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Integer>
{
  UserRole findByName(String name);
}
