package com.webcheckers.model;


import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MoveValidationTest {


    @Test
    void validate() {
        String whiteResult = "Cannot move to white spaces.";
        String occupiedResult = "Cannot move to occupied spaces.";
        String bigDistanceResult = "Cannot move more than one unit horizontally or vertically";
        Player player1 = new Player("bob");
        Player player2 = new Player("joe");
        Game game = new Game(player1, player2);
        BoardView boardView = game.getBoard();
        Move move = new Move(new Position(2,1), new Position(3,2));

        //test valid move
        String result = MoveValidation.validate(boardView, move, false);
        assertEquals(null, result);

        //test invalid move to white space
        Move move2 = new Move(new Position(2, 1), new Position(3, 1));
        result = MoveValidation.validate(boardView, move2, false);
        assertEquals(whiteResult, result);

        //test invalid move to occupied space
        Move move3 = new Move(new Position(1,0), new Position(2,1));
        result = MoveValidation.validate(boardView, move3, false);
        assertEquals(occupiedResult, result);


        //test move distance
        Move move4 = new Move(new Position(2,1), new Position(4,3));
        result = MoveValidation.validate(boardView, move4, false);
        assertEquals(bigDistanceResult, result);


    }

}