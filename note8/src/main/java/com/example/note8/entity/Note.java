package com.example.note8.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "note")
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  private LocalDateTime created;

  public Note() {
  }

  public Note(String content, LocalDateTime created) {
    this.content = content;
    this.created = created;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  @Override
  public String toString() {
    return "Note{" +
        "id=" + id +
        ", content='" + content + '\'' +
        ", created=" + created +
        '}';
  }
}

