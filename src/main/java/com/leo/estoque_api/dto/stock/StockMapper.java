package com.leo.estoque_api.dto.stock;

import com.leo.estoque_api.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mapping(target = "product", ignore = true)
    Stock toStock(StockRequestDTO stockRequestDTO);

    @Mapping(source = "product.name", target = "productName")
    StockResponseDTO toStockDTO(Stock stock);

    List<StockResponseDTO> toCollectionStockDTO(List<Stock> stocks);

}
