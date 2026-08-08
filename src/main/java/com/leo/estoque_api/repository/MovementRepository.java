package com.leo.estoque_api.repository;

import com.leo.estoque_api.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByUserId(Long id);

}
