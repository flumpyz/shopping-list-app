package com.example.shoppinglistapp.service;

import com.example.shoppinglistapp.database.DBException;
import com.example.shoppinglistapp.database.DatabaseService;
import com.example.shoppinglistapp.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingService {
    DatabaseService databaseService;

    public ShoppingService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Note create(String text, boolean isBought, String sessionId) throws DBException {
        Note note = new Note(text, isBought, sessionId);
        note.setId(databaseService.addPurchase(note));

        return note;
    }

    public void update(Long noteId, Boolean isBought) throws DBException {
        databaseService.updatePurchase(noteId, isBought);
    }

    public void delete(Long noteId) throws DBException {
        databaseService.deletePurchase(noteId);
    }

    public List<Note> getAllNotes(String sessionId) throws DBException {
        List<Note> notes = new ArrayList<>();
        databaseService.getPurchases(sessionId)
                .forEach(purchase -> {
                    Note note = new Note(purchase.getText(), purchase.getIsBought(), purchase.getSessionId());
                    note.setId(purchase.getId());
                    notes.add(note);
                });

        return notes;
    }
}
