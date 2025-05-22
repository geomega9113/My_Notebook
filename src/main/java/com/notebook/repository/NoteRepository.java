package com.notebook.repository;

import com.notebook.model.Note;
import com.notebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
    List<Note> findByUserAndLastNameContainingIgnoreCase(User user, String lastName);
    List<Note> findByUserAndPhoneContaining(User user, String phone);
    List<Note> findByUserAndAddressContainingIgnoreCase(User user, String address);
    List<Note> findByUserAndEmailContainingIgnoreCase(User user, String email);
    List<Note> findByUserAndWorkplaceContainingIgnoreCase(User user, String workplace);
    List<Note> findByLastNameContainingIgnoreCase(String lastName);
    List<Note> findByPhoneContainingIgnoreCase(String phone);
    List<Note> findAllByUser(User user);
}
