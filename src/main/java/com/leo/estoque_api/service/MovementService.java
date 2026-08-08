package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.movement.MovementMapper;
import com.leo.estoque_api.dto.movement.MovementResponseDTO;
import com.leo.estoque_api.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;

    public List<MovementResponseDTO> listAllMovements() {
        return movementMapper.toCollectionMovementDTO(movementRepository.findAll());
    }


}
