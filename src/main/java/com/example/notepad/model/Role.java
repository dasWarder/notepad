package com.example.notepad.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role {

  @Id
  @SequenceGenerator(
      name = "role_seq",
      sequenceName = "role_seq",
      initialValue = 100000,
      allocationSize = 1)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private List<Customer> customers;
}
