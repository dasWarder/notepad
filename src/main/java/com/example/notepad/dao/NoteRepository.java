package com.example.notepad.dao;

import com.example.notepad.model.Note;
import com.example.notepad.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.List;

@EnableJpaRepositories
public interface NoteRepository extends JpaRepository<Note, Long> {

  List<Note> getNotesByActualFor(LocalDateTime actualFor);

  List<Note> getNotesByActualForEquals(LocalDateTime dateTime);

  List<Note> getNotesByReminder(boolean reminder);

  List<Note> getNotesByTagsContains(Tag tag);
}
