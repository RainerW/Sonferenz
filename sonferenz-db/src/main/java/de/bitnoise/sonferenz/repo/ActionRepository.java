package de.bitnoise.sonferenz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bitnoise.sonferenz.model.ActionModel;

public interface ActionRepository extends JpaRepository<ActionModel, Integer>
{

  ActionModel findByActionAndToken(String action, String token);
}
