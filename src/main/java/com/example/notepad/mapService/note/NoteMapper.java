package com.example.notepad.mapService.note;

import com.example.notepad.mapService.dto.note.NoteRequest;
import com.example.notepad.mapService.dto.note.NoteResponse;
import com.example.notepad.mapService.note.qualifiers.LocalDateConverter;
import com.example.notepad.mapService.note.qualifiers.LocalDateTimeConverter;
import com.example.notepad.mapService.note.qualifiers.StringTagConverter;
import com.example.notepad.mapService.note.qualifiers.TagReverseConverter;
import com.example.notepad.model.Note;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(uses = NoteProvider.class)
public interface NoteMapper {

  @Mapping(target = "tags", source = "tags", qualifiedBy = StringTagConverter.class)
  @Mapping(target = "actualFor", source = "actualFor", qualifiedBy = LocalDateTimeConverter.class)
  Note noteRequestToNote(NoteRequest request);

  @Mapping(target = "tags", source = "tags", qualifiedBy = TagReverseConverter.class)
  @Mapping(target = "addedAt", source = "addedAt", qualifiedBy = LocalDateConverter.class)
  @Mapping(target = "actualFor", source = "actualFor", qualifiedBy = LocalDateConverter.class)
  NoteResponse noteToNoteResponse(Note note);

  @AfterMapping
  default void setAddedDate(@MappingTarget Note.NoteBuilder note) {
    note.addedAt(LocalDateTime.now());
  }
}
