package com.notebook.service;

import com.notebook.model.Note;
import com.notebook.model.User;
import com.notebook.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllForUser(User user) {
        return noteRepository.findAllByUser(user);
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Note add(Note note) {
        return noteRepository.save(note);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> searchByLastName(String value) {
        return noteRepository.findByLastNameContainingIgnoreCase(value);
    }

    public List<Note> searchByPhone(String value) {
        return noteRepository.findByPhoneContainingIgnoreCase(value);
    }
}
