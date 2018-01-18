package com.greenfox.todoapplication.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Assignee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int idUser;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignee")
  private List<ToDo> toDos = new ArrayList<>();

  private String name;

  public Assignee() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  @Override
  public boolean equals(Object obj) {
    Assignee otherAssignee = (Assignee) obj;
    return this.name.equals(otherAssignee.name);
  }


}
