package com.example.notepad.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@ToString(exclude = "tags, notes")
public class Customer {

  @Id
  @SequenceGenerator(
      name = "customer_seq",
      sequenceName = "customer_seq",
      initialValue = 1,
      allocationSize = 1)
  private Long id;

  private String email;

  private String login;

  private String password;

  private boolean enabled;

  private LocalDateTime registrationDate;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "role_customer",
      joinColumns = {@JoinColumn(name = "customer_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private Set<Role> roles;

  @OneToMany(
      fetch = FetchType.LAZY,
      orphanRemoval = true,
      cascade = CascadeType.ALL,
      mappedBy = "customer")
  private List<Note> notes;
}
