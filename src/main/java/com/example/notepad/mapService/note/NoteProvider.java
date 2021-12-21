package com.example.notepad.mapService.note;

import com.example.notepad.dao.TagRepository;
import com.example.notepad.mapService.note.qualifiers.LocalDateConverter;
import com.example.notepad.mapService.note.qualifiers.LocalDateTimeConverter;
import com.example.notepad.mapService.note.qualifiers.StringTagConverter;
import com.example.notepad.mapService.note.qualifiers.TagReverseConverter;
import com.example.notepad.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteProvider {

  private final TagRepository tagService;

  @StringTagConverter
  public Set<Tag> convertStringToTags(Set<String> tagNames) {

    return tagNames.stream()
        .map(
            t -> {
              Tag tag = tagService.getTagByTagName(t).orElse(null);

              if (Objects.isNull(tag)) {
                tag = Tag.builder().tagName(t).build();
              }
              return tag;
            })
        .collect(Collectors.toSet());
  }

  @TagReverseConverter
  public Set<String> convertTagsToString(Set<Tag> tags) {
    return tags.stream().map(t -> t.getTagName()).collect(Collectors.toSet());
  }

  @LocalDateTimeConverter
  public LocalDateTime convertRequestToEntityDate(LocalDate localDate) {
    return localDate.plusDays(1L).atStartOfDay();
  }

  @LocalDateConverter
  public LocalDate convertNoteToResponseDate(LocalDateTime localDateTime) {
    return localDateTime.toLocalDate();
  }
}
