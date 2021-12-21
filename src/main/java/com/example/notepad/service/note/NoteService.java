package com.example.notepad.service.note;

import com.example.notepad.model.Note;
import com.example.notepad.service.exception.NoteNotFoundException;

import java.util.List;
import java.util.Set;

public interface NoteService {

    Note saveNote(Note note);

    Note updateNote(Long id, Note note) throws NoteNotFoundException;

    Note getNoteById(Long id) throws NoteNotFoundException;

    void deleteNoteById(Long id);

    List<Note> getReminderNotes();

    List<Note> getNotes(String actualFor, Set<String> tagNames);

    List<Note> getTodayNotes();
}
