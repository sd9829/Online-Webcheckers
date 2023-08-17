package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getRow() {
        Position position = new Position(3,2);
        assertEquals(3, position.getRow());
    }

    @Test
    void getCell() {
        Position position = new Position(3,4);
        assertEquals(4, position.getCell());
    }
}