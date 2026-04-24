package com.leo.estoque_api.repository;

import com.leo.estoque_api.model.Movements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementsRepository extends JpaRepository<Movements, Long> {
}
