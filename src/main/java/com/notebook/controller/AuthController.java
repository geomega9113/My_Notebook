package com.notebook.controller;

import com.notebook.model.User;
import com.notebook.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Користувач з таким ім'ям вже існує!");
            return "register";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
