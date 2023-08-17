package com.webcheckers.appl;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.Arrays;

@Testable
class PlayerLobbyTest {

    @Test
    void createGame() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("PLAYER1");
        Player player2 = new Player("PLAYER2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);

        assertNotNull(game);
        assertTrue(game.containsPlayer(player1));
        assertTrue(game.containsPlayer(player2));
    }

    @Test
    void startGame() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("PLAYER1");
        Player player2 = new Player("PLAYER2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);
        playerLobby.startGame(game.getID());

        //make sure game is added to activeGames
        assertNotNull(playerLobby.getActiveGame(game.getID()));

        //make sure game is removed from waitingGames
        assertNull(playerLobby.getWaitingGame(game.getID()));

        //make sure game cannot be started more than once
        try {
            playerLobby.startGame(game.getID());
            fail("Cannot start game that has already been started.");
        } catch (Exception e) { }
    }

    @Test
    void searchWaitingGames() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        Player player4 = new Player("player4");
        Player player5 = new Player("player5");
        Player player6 = new Player("player6");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        playerLobby.addPlayer(player3);
        playerLobby.addPlayer(player4);
        playerLobby.addPlayer(player5);
        playerLobby.addPlayer(player6);
        Game game1 = playerLobby.createGame(player1, player2);
        Game game2 = playerLobby.createGame(player3, player4);
        Game game3 = playerLobby.createGame(player5, player6);
        playerLobby.startGame(game1.getID());
        playerLobby.startGame(game2.getID());
        assertEquals(game3, playerLobby.searchWaitingGames(player5));
        assertEquals(game3, playerLobby.searchWaitingGames(player6));
        assertEquals(null, playerLobby.searchWaitingGames(player1));
        assertEquals(null, playerLobby.searchWaitingGames(player2));
    }

    @Test
    void isPlayerValid() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        playerLobby.addPlayer(player3);
        Game game = playerLobby.createGame(player1, player2);
        playerLobby.startGame(game.getID());
        assertEquals(false, playerLobby.isPlayerValid(player2));
    }

    @Test
    void getPlayer() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        playerLobby.addPlayer(player1);
        assertEquals(null, playerLobby.getPlayer("player2"));
        String name = playerLobby.getPlayer("player1").getName();
        assertEquals("player1", name);

    }

    @Test
    void getPlayerCount() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        playerLobby.addPlayer(player3);
        int size = playerLobby.getPlayerCount();
        assertEquals(3, size);

    }

    @Test
    void getPlayerNamesArray() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        playerLobby.addPlayer(player3);
        String [] array = playerLobby.getPlayerNamesArray();
        String [] expected = {player1.getName(), player2.getName(), player3.getName()};
        assertArrayEquals(expected, array);
    }

    @Test
    void getPlayersArray() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        playerLobby.addPlayer(player3);
        Player [] array = playerLobby.getPlayersArray();
        Player [] expected = {player1, player2, player3};
        assertArrayEquals(expected, array);
    }

    @Test
    void addPlayer() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        playerLobby.addPlayer(player1);

        //make sure player cannot be added more than once
        try {
            playerLobby.addPlayer(player1);
            fail("Can't add same player twice.");
        } catch (Exception e) { }

    }

    @Test
    void removePlayer() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        playerLobby.addPlayer(player1);
        playerLobby.removePlayer(player1);

        //make sure player cannot be removed more than once
        try {
            playerLobby.removePlayer(player1);
            fail("Can't remove same player twice.");
        } catch (Exception e) { }

    }


    @Test
    void getActiveGame() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("PLAYER1");
        Player player2 = new Player("PLAYER2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);
        playerLobby.startGame(game.getID());
        Game actual = playerLobby.getActiveGame(game.getID());
        assertEquals(game, actual);
    }

    @Test
    void getActiveGameCount() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        Player player4 = new Player("player4");
        Player player5 = new Player("player5");
        Player player6 = new Player("player6");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        playerLobby.addPlayer(player3);
        playerLobby.addPlayer(player4);
        playerLobby.addPlayer(player5);
        playerLobby.addPlayer(player6);
        Game game1 = playerLobby.createGame(player1, player2);
        Game game2 = playerLobby.createGame(player3, player4);
        Game game3 = playerLobby.createGame(player5, player6);
        playerLobby.startGame(game1.getID());
        playerLobby.startGame(game2.getID());
        playerLobby.startGame(game3.getID());
        int size = 3;
        int gamesActive = playerLobby.getActiveGameCount();
        assertEquals(3, gamesActive);

    }

    @Test
    void getActiveGameIDs() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        Player player4 = new Player("player4");
        Player player5 = new Player("player5");
        Player player6 = new Player("player6");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        playerLobby.addPlayer(player3);
        playerLobby.addPlayer(player4);
        playerLobby.addPlayer(player5);
        playerLobby.addPlayer(player6);
        Game game1 = playerLobby.createGame(player1, player2);
        Game game2 = playerLobby.createGame(player3, player4);
        Game game3 = playerLobby.createGame(player5, player6);
        playerLobby.startGame(game1.getID());
        playerLobby.startGame(game2.getID());
        playerLobby.startGame(game3.getID());
        String [] activeIDs = playerLobby.getActiveGameIDs();
        Arrays.sort(activeIDs);
        String [] expected = {game1.getID(), game2.getID(), game3.getID()};
        Arrays.sort(expected);
        assertArrayEquals(expected, activeIDs);
    }

    @Test
    void getActiveGames() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        Player player4 = new Player("player4");
        Player player5 = new Player("player5");
        Player player6 = new Player("player6");
        Player player7 = new Player("player7");
        Player player8 = new Player("player8");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        playerLobby.addPlayer(player3);
        playerLobby.addPlayer(player4);
        playerLobby.addPlayer(player5);
        playerLobby.addPlayer(player6);
        playerLobby.addPlayer(player7);
        playerLobby.addPlayer(player8);
        Game game1 = playerLobby.createGame(player1, player2);
        Game game2 = playerLobby.createGame(player3, player4);
        Game game3 = playerLobby.createGame(player5, player6);
        Game game4 = playerLobby.createGame(player7, player8);
        playerLobby.startGame(game1.getID());
        playerLobby.startGame(game2.getID());
        playerLobby.startGame(game3.getID());
        Game[] activeGames = playerLobby.getActiveGames();
        Game [] expected = {game1, game2, game3};
        //assertEquals(expected, activeGames);
    }

    @Test
    void addActiveGame() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);
        playerLobby.startGame(game.getID());
        assertThrows(IllegalArgumentException.class, () -> {
            playerLobby.addActiveGame(game);
        }, "can't add same game twice");
    }

    @Test
    void removeActiveGame() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);
        playerLobby.startGame(game.getID());
        playerLobby.removeActiveGame(game);
        assertThrows(IllegalArgumentException.class, () -> {
            playerLobby.removeActiveGame(game);
        }, "can't remove same game twice");
    }


    @Test
    void getWaitingGame() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);
        Game lookup = playerLobby.getWaitingGame(game.getID());
        assertEquals(game, lookup);
    }

    @Test
    void getWaitingGameCount() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);
        int lookup = playerLobby.getWaitingGameCount();
        assertEquals(1, lookup);
    }

    @Test
    void getWaitingGameIDs() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);
        String [] lookup = playerLobby.getWaitingGameIDs();
        String [] expected = {game.getID()};
        assertArrayEquals(expected, lookup);
    }

    @Test
    void getWaitingGames() {
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        playerLobby.addPlayer(player1);
        playerLobby.addPlayer(player2);
        Game game = playerLobby.createGame(player1, player2);
        Game [] lookup = playerLobby.getWaitingGames();
        assertArrayEquals(new Game [] {game}, lookup);
    }
}