package com.example.etapa1DAC.service;

import com.example.etapa1DAC.controller.request.GambiarraRequest;
import com.example.etapa1DAC.domain.CategoryEventType;
import com.example.etapa1DAC.repository.CategoryEventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GambiarraService {

    @Autowired
    CategoryEventTypeRepository repository;

    public CategoryEventType createCategoryEventType(GambiarraRequest request) {
        CategoryEventType newCategory = new CategoryEventType();
        newCategory.setName(request.getName());
        return repository.save(newCategory);
    }
}
