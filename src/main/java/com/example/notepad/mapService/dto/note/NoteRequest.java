package com.example.notepad.mapService.dto.note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteRequest {

  private String description;

  private LocalDate actualFor;

  private boolean reminder;

  private Set<String> tags;
}
