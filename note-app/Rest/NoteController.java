package com.learning.noteapp.Rest;

import com.learning.noteapp.Dao.NoteRepository;
import com.learning.noteapp.Entity.Note;
import com.learning.noteapp.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {
    NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable(value = "id") Long id) {
        return noteRepository.findById(id).orElseThrow(()-> new NotFoundException("Note","id",id));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long id, @RequestBody Note note) {
        Note newNote = noteRepository.findById(id).orElseThrow(()-> new NotFoundException("Note","id",id));

        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());

        Note updatedNote = noteRepository.save(newNote);

        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note", "id", id));

        noteRepository.deleteById(note.getId());

        return ResponseEntity.ok().build();
    }


}
