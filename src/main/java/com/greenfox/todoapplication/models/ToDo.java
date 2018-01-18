package com.greenfox.todoapplication.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Calendar;

@Entity
@Table
public class ToDo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Temporal(TemporalType.TIMESTAMP)
  private Calendar createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assignee_id")
  private Assignee assignee;

  private String title;
  private  boolean isUrgent;
  private boolean isDone;

  public ToDo() {
  }

  public ToDo(String title, boolean isUrgent, boolean isDone, Assignee assignee) {
    this.title = title;
    this.isUrgent = isUrgent;
    this.isDone = isDone;
    this.assignee = assignee;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean getIsUrgent() {
    return isUrgent;
  }

  public void setIsUrgent(boolean urgent) {
    isUrgent = urgent;
  }

  public boolean getIsDone() {
    return isDone;
  }

  public void setIsDone(boolean done) {
    isDone = done;
  }

  public Assignee getAssignee() {
    return assignee;
  }

  public void setAssignee(Assignee assignee) {
    this.assignee = assignee;
  }
}
