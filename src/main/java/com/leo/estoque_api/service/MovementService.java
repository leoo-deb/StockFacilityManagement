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
    private final ProductService productService;
    private final MovementMapper movementMapper;

    public List<MovementResponseDTO> listAllMovements() {
        return movementMapper.toCollectionMovementDTO(movementRepository.findAll());
    }

//    @Transactional
//    public MovementResponseDTO registerEntry(MovementRequestDTO movementRequestDTO, Long quantity) {
//        Movement movement = movementMapper.toMovement(movementRequestDTO);
//        validateEntry(movement, movementRequestDTO.productId(), quantity);
//
//        movement.setUser(new User());
//        movement.getUser().setId(1L);
//        movement.setType(TypeMovements.ENTRY);
//
//        return movementMapper.toMovementDTO(movementRepository.save(movement));
//    }
//
//    @Transactional
//    public MovementResponseDTO registerExit(MovementRequestDTO movementRequestDTO, Long quantity) {
//        Movement movement = movementMapper.toMovement(movementRequestDTO);
//        validateExit(movement, movementRequestDTO.productId(), quantity);
//
//        movement.setUser(new User());
//        movement.getUser().setId(1L);
//        movement.setType(TypeMovements.EXIT);
//
//        movement = movementRepository.save(movement);
//        return movementMapper.toMovementDTO(movement);
//    }
//
//    private void validateEntry(Movement movement, Long idProduct, Long quantity) {
//        Product product = productService.findById(idProduct);
//
//        if (!product.isActive()) {
//            throw new BusinessRuleException(String.format("Não é possível registrar uma " +
//                    "entrada com o produto de código %d, pois está inativo.", idProduct));
//        }
//
//        product.registerEntry(quantity);
//        product.sumTotalPrice();
//        movement.setProduct(product);
//    }
//
//    private void validateExit(Movement movement, Long idProduct, Long quantity) {
//        Product product = productService.findById(idProduct);
//
//        if (!product.isActive()) {
//            throw new BusinessRuleException(String.format("Não é possível registrar uma " +
//                    "saída com o produto de código %d, pois está inativo.", idProduct));
//        }
//
//        if (product.getQuantity() < quantity) {
//            throw new BusinessRuleException(String.format("Produto de código %d não possui quantidade " +
//                    "suficiente para registrar uma saída. Quantidade atual: %d", idProduct, product.getQuantity()));
//        }
//
//        product.registerExit(quantity);
//        product.sumTotalPrice();
//        movement.setProduct(product);
//    }

}
