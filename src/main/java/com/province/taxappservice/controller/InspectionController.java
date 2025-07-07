package com.province.taxappservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.province.taxappservice.model.Inspection;
import com.province.taxappservice.service.ImageStorageService;
import com.province.taxappservice.service.InspectionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/inspections")
public class InspectionController {
    private final InspectionService service;
    private final ImageStorageService imageStorageService;

    public InspectionController(InspectionService service, ImageStorageService imageStorageService) {
        this.service = service;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping
    public List<Inspection> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inspection> getById(@PathVariable String id) {
        Inspection i = service.getById(id);
        return i != null ? ResponseEntity.ok(i) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Inspection create(@RequestBody Inspection i) {
        return service.create(i);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inspection> update(@PathVariable String id, @RequestBody Inspection i) {
        Inspection updated = service.update(id, i);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/valider",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Inspection valider(@RequestPart("data") String data,
                                                                @RequestPart("signature") MultipartFile file) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Inspection inspection = objectMapper.readValue(data, Inspection.class);

        String savedFileName = imageStorageService.save(file, "inspection");
        inspection.setSignature(savedFileName);
        return service.create(inspection);
    }
}
