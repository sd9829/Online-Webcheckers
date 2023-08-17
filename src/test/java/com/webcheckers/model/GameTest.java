package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {



    @Test
    void containsPlayer() {
        Player player1 = new Player("Bob");
        Player player2 = new Player(("Joe"));
        Player player3 = new Player("Ted");
        Game game = new Game(player1,player2);
        assertTrue(game.containsPlayer(player1));
        assertTrue(game.containsPlayer(player2));
        assertFalse(game.containsPlayer(player3));
    }

    @Test
    void getProgress() {

    }

    @Test
    void result() {
    }


    @Test
    void getPlayer1() {
        Player player1 = new Player("Bob");
        Player player2 = new Player(("Joe"));
        Game game = new Game(player1, player2);
        assertEquals(player1, game.getRedPlayer());

    }

    @Test
    void getPlayer2() {
        Player player1 = new Player("Bob");
        Player player2 = new Player(("Joe"));
        Game game = new Game(player1, player2);
        assertEquals(player2, game.getWhitePlayer());
    }


}