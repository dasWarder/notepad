package com.example.notepad.service.note;

import com.example.notepad.dao.NoteRepository;
import com.example.notepad.dao.TagRepository;
import com.example.notepad.model.Note;
import com.example.notepad.model.Tag;
import com.example.notepad.service.exception.NoteNotFoundException;
import com.example.notepad.service.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

  private final TagRepository tagRepository;

  private final NoteRepository noteRepository;

  @Override
  public Note saveNote(Note note) {

    log.info("In NoteServiceImpl.saveNote - Store a new note");

    return noteRepository.save(note);
  }

  @Override
  public Note updateNote(Long id, Note note) throws NoteNotFoundException {

    log.info("In NoteServiceImpl.updateNote - Update note by id = {}", id);

    if (!noteRepository.existsById(id)) {

      log.error("In NoteServiceImpl.updateNote - Note with id = {} not found", id);
      throw new NoteNotFoundException(String.format("Note with id = %d not found", id));
    }

    note.setId(id);

    return noteRepository.save(note);
  }

  @Override
  public Note getNoteById(Long id) throws NoteNotFoundException {

    log.info("In NoteServiceImpl.getNoteById - Get note by id = {}", id);

    return noteRepository
        .findById(id)
        .orElseThrow(
            () -> new NoteNotFoundException(String.format("Note with id = %d not found", id)));
  }

  @Override
  public void deleteNoteById(Long id) {

    log.info("In NoteServiceImpl.deleteNoteById - Delete note by id = {}", id);

    noteRepository.deleteById(id);
  }

  @Override
  public List<Note> getReminderNotes() {

    log.info("In NoteServiceImpl.getReminderNotes - Get notes with reminder = true");

    return (List) noteRepository.getNotesByReminder(true);
  }

  @Override
  public List<Note> getNotes(String actualFor, String tagName, String date) {

    if (Objects.nonNull(actualFor)) {

      log.info("In NoteServiceImpl.getNotes - Get notes by actual for = {}", actualFor);
      LocalDate parsedDate = LocalDate.parse(actualFor);

      return noteRepository.getNotesByActualFor(parsedDate.atStartOfDay());
    }

    if (Objects.nonNull(tagName)) {

      log.info("In NoteServiceImpl.getNotes - Get notes by tag = {}", tagName);
      return this.getNotesByTag(tagName);
    }

    if (Objects.nonNull(date)) {

      log.info("In NoteServiceImpl.getNotes - Get notes by date = {}", date);
      return this.getNotesByDate(date);
    }

    log.info("In NoteServiceImpl.getNotes - Get notes");

    return noteRepository.findAll();
  }

  @Override
  public List<Note> getTodayNotes() {

    log.info("In NoteServiceImpl.getTodayNotes - Get today notes");

    return noteRepository.findAll().stream()
        .filter(n -> DateTimeUtil.noteDateDueToday(n.getActualFor()))
        .collect(Collectors.toList());
  }

  private List<Note> getNotesByDate(String date) {

    LocalDateTime localDate = LocalDate.parse(date).atStartOfDay().plusDays(1L);

    return noteRepository.getNotesByActualForEquals(localDate);
  }

  private List<Note> getNotesByTag(String tagName) {

    Tag tag = tagRepository.getTagByTagName(tagName).orElse(null);
    return noteRepository.getNotesByTagsContains(tag);
  }
}
