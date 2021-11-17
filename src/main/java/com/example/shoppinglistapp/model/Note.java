package com.example.shoppinglistapp.model;

public class Note {
    private Long id;
    private final String text;
    private boolean isBought;
    private final String sessionId;

    public Note(String text, boolean isBought, String sessionId) {
        this.text = text;
        this.isBought = isBought;
        this.sessionId = sessionId;
    }

    public String getText() {
        return text;
    }

    public boolean getIsBought() {
        return isBought;
    }

    public void setNoteState(boolean isBought) {
        this.isBought = isBought;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
