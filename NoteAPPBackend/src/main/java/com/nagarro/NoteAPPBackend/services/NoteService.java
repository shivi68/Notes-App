package com.nagarro.NoteAPPBackend.services;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.NoteAPPBackend.models.Notes;
import com.nagarro.NoteAPPBackend.models.User;
import com.nagarro.NoteAPPBackend.repository.NoteRepository;


@Service

public class NoteService {

    @Autowired
    private NoteRepository noteRepository;


    public List<Notes> getAllNotesByUser(User user) {
        return noteRepository.findByUser(user);

    }

    public Notes addNewNote(User user, Notes note) {
        note.setUser(user);
        return noteRepository.save(note);
    }

    
    public List<Notes> getLatestNotesForUser(User user) {
        return noteRepository.findTop10ByUserOrderByCreatedDtDesc(user);

    }

    public void deleteNoteById(User user, Integer id) throws AccessDeniedException {
        Optional<Notes> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent() && optionalNote.get().getUser().equals(user)) {
            noteRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }
    
    
}

 

 