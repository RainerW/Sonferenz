package de.bitnoise.sonferenz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import de.bitnoise.sonferenz.model.WhishModel;

public interface WhishRepository extends JpaRepository<WhishModel, Integer>
{
}
