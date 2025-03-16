package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.controller.request.GambiarraRequest;
import com.example.etapa1DAC.domain.CategoryEventType;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.service.GambiarraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gambiarra/criar-categorias")
public class GambiarraController {

    @Autowired
    GambiarraService gambiarraService;

    @PostMapping()
    CategoryEventType createCategory(@RequestBody GambiarraRequest request) {
        return this.gambiarraService.createCategoryEventType(request);
    }

}
