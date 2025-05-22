package com.notebook.controller;

import com.notebook.model.Note;
import com.notebook.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Note> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Note create(@RequestBody Note note) {
        return service.add(note);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search/lastname")
    public List<Note> searchByLastName(@RequestParam String value) {
        return service.searchByLastName(value);
    }

    @GetMapping("/search/phone")
    public List<Note> searchByPhone(@RequestParam String value) {
        return service.searchByPhone(value);
    }
}