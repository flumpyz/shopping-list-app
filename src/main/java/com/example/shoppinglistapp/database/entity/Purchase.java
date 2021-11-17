package com.example.shoppinglistapp.database.entity;

import com.example.shoppinglistapp.model.Note;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchases")
public class Purchase implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Session_id", nullable = false)
    private String sessionId;

    @Column(name = "Note_text")
    private String text;

    @Column(name = "Is_bought", nullable = false)
    private boolean isBought;

    public Purchase() {}

    public Purchase(String sessionId, String text, boolean isBought) {
        this.sessionId = sessionId;
        this.text = text;
        this.isBought = isBought;
    }

    public Purchase(Note note) {
        this.sessionId = note.getSessionId();
        this.text = note.getText();
        this.isBought = note.getIsBought();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsBought() {
        return isBought;
    }

    public void setState(boolean isBought) {
        this.isBought = isBought;
    }
}
