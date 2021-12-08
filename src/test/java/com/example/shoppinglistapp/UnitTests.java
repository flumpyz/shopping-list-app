package com.example.shoppinglistapp;

import com.example.shoppinglistapp.database.DAO.PurchaseDAO;
import com.example.shoppinglistapp.database.DBException;
import com.example.shoppinglistapp.database.DatabaseService;
import com.example.shoppinglistapp.database.entity.Purchase;
import com.example.shoppinglistapp.model.Note;
import com.example.shoppinglistapp.service.ShoppingService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class UnitTests {

    @Autowired
    private ShoppingService shoppingService;

    @MockBean
    private DatabaseService databaseService;

    @Test
    public void create() throws DBException {
        String sessionId = "A3967D16FCEAD5E938C318FBEE9BAD54";
        Note note = shoppingService.create("something", true, sessionId);

        Assertions.assertNotNull(note);
        Assertions.assertNotNull(note.getId());

        shoppingService.update(note.getId(), false);

        Mockito.verify(databaseService, Mockito.times(1)).addPurchase(note);
        Mockito.verify(databaseService, Mockito.times(1)).updatePurchase(note.getId(), false);
    }

    @Test
    public void getAllNotes() throws DBException {
        String sessionId = "A3967D16FCEAD5E938C318FBEE9BAD54";
        List<Note> notes = shoppingService.getAllNotes(sessionId);

        Assertions.assertNotNull(notes);

        Mockito.verify(databaseService, Mockito.times(1)).getPurchases(sessionId);
    }
}
