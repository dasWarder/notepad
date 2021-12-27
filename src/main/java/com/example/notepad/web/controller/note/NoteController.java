package com.example.notepad.web.controller.note;

import com.example.notepad.mapService.dto.note.NoteRequest;
import com.example.notepad.mapService.dto.note.NoteResponse;
import com.example.notepad.mapService.note.NoteMapper;
import com.example.notepad.model.Note;
import com.example.notepad.service.exception.NoteNotFoundException;
import com.example.notepad.service.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {

  private final NoteMapper noteMapper;

  private final NoteService noteService;

  private static final String SAVE_URI = "http://localhost:8080/api/v1/notes";

  @PostMapping
  public ResponseEntity<NoteResponse> saveNote(@RequestBody NoteRequest request) {

    Note note = noteMapper.noteRequestToNote(request);
    Note storedNote = noteService.saveNote(note);
    NoteResponse response = noteMapper.noteToNoteResponse(storedNote);

    return ResponseEntity.created(URI.create(SAVE_URI)).body(response);
  }

  @PutMapping
  public ResponseEntity<NoteResponse> updateNote(
      @RequestParam("id") Long id, @RequestBody NoteRequest request) throws NoteNotFoundException {

    Note note = noteMapper.noteRequestToNote(request);
    Note updateNote = noteService.updateNote(id, note);
    NoteResponse response = noteMapper.noteToNoteResponse(updateNote);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<NoteResponse> getNoteById(@PathVariable("id") Long id)
      throws NoteNotFoundException {

    Note note = noteService.getNoteById(id);
    NoteResponse response = noteMapper.noteToNoteResponse(note);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNoteById(@PathVariable("id") Long id) {

    noteService.deleteNoteById(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<NoteResponse>> getNotes(
      @RequestParam(value = "actualFor", required = false) String actualFor,
      @RequestParam(value = "tag", required = false) String tagName,
      @RequestParam(value = "date", required = false) String date) {

    List<NoteResponse> response =
        noteService.getNotes(actualFor, tagName, date).stream()
            .map(noteMapper::noteToNoteResponse)
            .collect(Collectors.toList());

    return ResponseEntity.ok(response);
  }

  @GetMapping("/note")
  public ResponseEntity<List<NoteResponse>> getTodayNotes() {

    List<NoteResponse> response =
            noteService.getTodayNotes().stream()
                    .map(noteMapper::noteToNoteResponse)
                    .collect(Collectors.toList());

    return ResponseEntity.ok(response);
  }
}
