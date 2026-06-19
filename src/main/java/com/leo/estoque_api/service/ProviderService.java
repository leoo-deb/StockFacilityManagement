package com.leo.estoque_api.service;

import com.leo.estoque_api.dto.response.ProviderResponseDTO;
import com.leo.estoque_api.exceptions.ResourceNotFoundException;
import com.leo.estoque_api.mapper.ProviderMapper;
import com.leo.estoque_api.model.Provider;
import com.leo.estoque_api.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ProviderService {

    private ProviderRepository providerRepository;
    private ProviderMapper providerMapper;

    public ProviderResponseDTO findDtoById(Long id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not  found."));

        return providerMapper.toDto(provider);
    }

    public Provider findById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not  found."));
    }

}
