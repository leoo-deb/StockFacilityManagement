package com.leo.estoque_api.controller;

import com.leo.estoque_api.dto.request.MovementRequestDTO;
import com.leo.estoque_api.dto.response.MovementResponseDTO;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.EntityNotFoundException;
import com.leo.estoque_api.model.Movement;
import com.leo.estoque_api.service.MovementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping
    public ResponseEntity<List<MovementResponseDTO>> listAll() {
        return ResponseEntity.ok(movementService.listAllMovements());
    }

    @PostMapping("/entry-registration")
    public ResponseEntity<MovementResponseDTO> registerEntry(@RequestBody @Valid MovementRequestDTO movementRequestDTO) {
        try {
            MovementResponseDTO movementResponseDTO = movementService
                    .registerEntry(movementRequestDTO, movementRequestDTO.quantity());

            return ResponseEntity.ok(movementResponseDTO);
        } catch (EntityNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    @DeleteMapping("/exit-registration")
    public ResponseEntity<MovementResponseDTO> registerExit(@RequestBody @Valid MovementRequestDTO movementRequestDTO) {
        try {
            MovementResponseDTO movementResponseDTO = movementService
                    .registerExit(movementRequestDTO, movementRequestDTO.quantity());

            return ResponseEntity.ok(movementResponseDTO);
        } catch (EntityNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

}
