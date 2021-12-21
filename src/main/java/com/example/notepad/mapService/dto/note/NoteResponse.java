package com.example.notepad.mapService.dto.note;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponse {

    private Long id;

    private String description;

    @JsonProperty(value = "added_at")
    private LocalDate addedAt;

    @JsonProperty(value = "actual_for")
    private LocalDate actualFor;

    private boolean reminder;

    private Set<String> tags;
}
