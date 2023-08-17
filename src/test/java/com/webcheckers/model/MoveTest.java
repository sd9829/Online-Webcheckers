package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void getIsValid() {
    }

    @Test
    void setIsValid() {
    }

    @Test
    void getStart() {
        Position start = new Position(1,2);
        Position end = new Position(3, 5);
        Move move = new Move(start, end);
        assertEquals(start, move.getStart());
    }

    @Test
    void getEnd() {
        Position start = new Position(1,2);
        Position end = new Position(3, 5);
        Move move = new Move(start, end);
        assertEquals(end, move.getEnd());
    }
}