package com.notebook.controller;

import com.notebook.model.Note;
import com.notebook.model.User;
import com.notebook.repository.NoteRepository;
import com.notebook.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    private final NoteRepository noteRepo;
    private final UserRepository userRepo;

    public WebController(NoteRepository noteRepo, UserRepository userRepo) {
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String home(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String workplace,
            Model model) {

        User user = userRepo.findByUsername(userDetails.getUsername());

        List<Note> notes = noteRepo.findByUser(user);

        if (lastName != null && !lastName.isBlank())
            notes.retainAll(noteRepo.findByUserAndLastNameContainingIgnoreCase(user, lastName));
        if (phone != null && !phone.isBlank())
            notes.retainAll(noteRepo.findByUserAndPhoneContaining(user, phone));
        if (email != null && !email.isBlank())
            notes.retainAll(noteRepo.findByUserAndEmailContainingIgnoreCase(user, email));
        if (address != null && !address.isBlank())
            notes.retainAll(noteRepo.findByUserAndAddressContainingIgnoreCase(user, address));
        if (workplace != null && !workplace.isBlank())
            notes.retainAll(noteRepo.findByUserAndWorkplaceContainingIgnoreCase(user, workplace));

        model.addAttribute("notes", notes);
        model.addAttribute("filters", new Note());
        return "index";
    }

    @PostMapping("/notes")
    public String addNote(@ModelAttribute Note note, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername());
        note.setUser(user);
        noteRepo.save(note);
        return "redirect:/";
    }

    @PostMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Note note = noteRepo.findById(id).orElse(null);
        if (note != null && note.getUser().getUsername().equals(userDetails.getUsername())) {
            noteRepo.delete(note);
        }
        return "redirect:/";
    }

    @GetMapping("/reset")
    public String resetFilters() {
        return "redirect:/";
    }
}
