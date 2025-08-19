package com.learning.noteapp.Dao;

import com.learning.noteapp.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
