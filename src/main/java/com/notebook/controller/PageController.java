package com.notebook.controller;

import com.notebook.model.Note;
import com.notebook.model.User;
import com.notebook.repository.UserRepository;
import com.notebook.service.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    private final NoteService noteService;
    private final UserRepository userRepository;

    public PageController(NoteService noteService, UserRepository userRepository) {
        this.noteService = noteService;
        this.userRepository = userRepository;
    }

//    @GetMapping("/")
//    public String notebookPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        String username = userDetails.getUsername();
//        User user = userRepository.findByUsername(username);
//        List<Note> notes = noteService.getAllForUser(user); // Додай цей метод у NoteService
//        model.addAttribute("notes", notes);
//        return "notebook";
//    }
}
