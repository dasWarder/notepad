package com.example.notepad.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "note")
public class Note {

  @Id
  @SequenceGenerator(
      name = "note_seq",
      sequenceName = "note_seq",
      initialValue = 1,
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_seq")
  private Long id;

  private String description;

  @Column(name = "added_at")
  private LocalDateTime addedAt;

  @Column(name = "actual_for")
  private LocalDateTime actualFor;

  private boolean reminder;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
  @JoinTable(
      name = "note_tag",
      joinColumns = {@JoinColumn(name = "note_id")},
      inverseJoinColumns = {@JoinColumn(name = "tag_id")})
  private Set<Tag> tags;
}
