package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testEquals() {
        Player player1 = new Player("joe");
        Player player2 = new Player("joe");
        Player player3 = new Player("bob");
        String name = "bob";
        assertTrue(player1.equals(player2));
        assertFalse(player1.equals(player3));
        assertFalse(player1.equals(name));
    }


    @Test
    void getName() {
        Player player = new Player("bob");
        assertEquals("bob", player.getName());
    }

    @Test
    void getCurrentGameID() {
        Player player1 = new Player("joe");
        Player player2 = new Player("bob");
        Game game = new Game(player1,player2);
        String expected = game.getID();
        String actual = player1.getCurrentGameID();
        assertEquals(expected, actual);
    }


}