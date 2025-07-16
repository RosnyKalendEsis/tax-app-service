package com.province.taxappservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.province.taxappservice.model.Inspection;
import com.province.taxappservice.model.Note;
import com.province.taxappservice.service.ImageStorageService;
import com.province.taxappservice.service.NoteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService service;
    private final ImageStorageService imageStorageService;

    public NoteController(NoteService service, ImageStorageService imageStorageService) {
        this.service = service;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping
    public List<Note> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable String id) {
        Note n = service.getById(id);
        return n != null ? ResponseEntity.ok(n) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Note create(@RequestBody Note n) {
        return service.create(n);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable String id, @RequestBody Note n) {
        Note updated = service.update(id, n);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PutMapping("/valider")
    public ResponseEntity<Note> validerNote(@RequestBody Note n) {
        return ResponseEntity.ok(service.create(n));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/valider",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Note valider(@RequestPart("data") String data,
                              @RequestPart("signature") MultipartFile file) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Note note = objectMapper.readValue(data, Note.class);

        String savedFileName = imageStorageService.save(file, "perceptions");
        note.setSignature(savedFileName);
        note.setValide(true);
        return service.create(note);
    }
}
