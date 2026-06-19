package com.leo.estoque_api.mapper;

import com.leo.estoque_api.dto.request.ProviderRequestDTO;
import com.leo.estoque_api.dto.response.ProviderResponseDTO;
import com.leo.estoque_api.model.Provider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ProviderMapper {

    private ModelMapper modelMapper;

    public ProviderResponseDTO toDto(Provider provider) {
        return modelMapper.map(provider, ProviderResponseDTO.class);
    }

    public Provider toModel(ProviderRequestDTO providerRequestDTO) {
        return modelMapper.map(providerRequestDTO, Provider.class);
    }

}
