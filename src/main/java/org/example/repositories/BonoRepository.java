package org.example.repositories;

import org.example.entities.BonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonoRepository extends JpaRepository<BonoEntity, Float> {
}
