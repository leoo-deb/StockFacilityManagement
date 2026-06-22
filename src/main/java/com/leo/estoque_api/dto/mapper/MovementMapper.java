package com.leo.estoque_api.dto.mapper;

import com.leo.estoque_api.dto.request.MovementRequestDTO;
import com.leo.estoque_api.dto.response.MovementResponseDTO;
import com.leo.estoque_api.model.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    Movement toMovement(MovementRequestDTO movementRequest);

    MovementResponseDTO toMovementDTO(Movement movement);

    List<MovementResponseDTO> toCollectionMovementDTO(List<Movement> movements);

}
