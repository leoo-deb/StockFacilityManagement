package com.leo.estoque_api.controller;

import com.leo.estoque_api.model.Provider;
import com.leo.estoque_api.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @GetMapping
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Provider findById(@PathVariable Long id) {
    return providerRepository.findById(id).get();
    }

}
