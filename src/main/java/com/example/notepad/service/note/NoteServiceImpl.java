package com.example.notepad.service.note;

import com.example.notepad.dao.NoteRepository;
import com.example.notepad.dao.TagRepository;
import com.example.notepad.model.Note;
import com.example.notepad.service.exception.NoteNotFoundException;
import com.example.notepad.service.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
  public List<Note> getNotes(String actualFor, Set<String> tagNames) {

    if (Objects.nonNull(actualFor)) {

      log.info("In NoteServiceImpl.getNotes - Get notes by actual for = {}", actualFor);

      LocalDate parsedDate = LocalDate.parse(actualFor);
      return noteRepository.getNotesByActualFor(parsedDate.atStartOfDay()).stream()
          .collect(Collectors.toList());
    }

    if (Objects.nonNull(tagNames)) {

      log.info("In NoteServiceImpl.getNotes - Get notes by tags");
      return this.getNotesByTags(tagNames).stream().collect(Collectors.toList());
    }

    log.info("In NoteServiceImpl.getNotes - Get notes");

    return noteRepository.findAll().stream().collect(Collectors.toList());
  }

  @Override
  public List<Note> getTodayNotes() {

    log.info("In NoteServiceImpl.getTodayNotes - Get today notes");

    return noteRepository.findAll().stream()
        .filter(n -> DateTimeUtil.noteDateDueToday(n.getActualFor()))
        .collect(Collectors.toList());
  }

  private List<Note> getNotesByTags(Set<String> tagNames) {

    List<Note> notes = new ArrayList<>();

    tagNames.stream()
        .map(t -> tagRepository.getTagByTagName(t).orElse(null))
        .forEach(tag -> notes.addAll(noteRepository.getNotesByTagsContains(tag)));

    return notes;
  }
}
