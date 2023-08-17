package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;
import java.util.Arrays;

import com.webcheckers.model.Space.Color;

@Testable
public class BoardViewTest {

    @Test
    void flipped() {

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Game game = new Game(player1, player2);
        BoardView board = game.getBoard();
        BoardView flipped = board.flipped();

        assertEquals(flipped.getSpace(new Position(0, 0)).getCellIdx(), 7);
        assertEquals(board.getSpace(new Position(0, 0)).getCellIdx(), 0);
    }

    @Test
    void getSpaceTest() {
        Position position = new Position(4,4);  //randomly assigned
        ArrayList<Space> spaces;
        Player player1 = new Player("bob");
        Player player2 = new Player("joe");
        Game game = new Game(player1, player2);
        spaces = game.getBoard().getRows().get(4).getSpaces();
        Space space = spaces.get(4);


        BoardView boardView = game.getBoard();

        assertEquals(space, boardView.getSpace(position));
    }

    @Test
    void addPossibleMove() {


    }


}
