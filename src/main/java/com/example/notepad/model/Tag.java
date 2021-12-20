package com.example.notepad.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
@ToString(exclude = {"id", "notes"})
public class Tag {

  @Id
  @SequenceGenerator(
      name = "tag_seq",
      sequenceName = "tag_seq",
      initialValue = 1,
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
  private Long id;

  @Column(name = "tag_name")
  private String tagName;

  @ManyToMany(mappedBy = "tags", targetEntity = Note.class, fetch = FetchType.LAZY)
  private List<Note> notes;
}
