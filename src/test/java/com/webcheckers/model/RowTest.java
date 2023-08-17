package com.webcheckers.model;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testable
public class RowTest {
    @Test
    void getIndexTest() {
        int cellIdx = 3;    //random assignment
        ArrayList<Space> spaces = new ArrayList<>();
        Piece piece = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE);
        //Space spaces = new Space(cellIdx, piece);
        Row singleRow = new Row( spaces, 4 );
        int expected = 4;
        assertEquals(expected, singleRow.getIndex());
        //passes

    }

    @Test
    void getSpacesTest() {
        Row singleRow;
        ArrayList<Space> spaces = new ArrayList<>();
        singleRow = new Row( spaces, 4);
        ArrayList<Space> expected = new ArrayList<>();
        assertEquals(expected, singleRow.getSpaces());
        //passes
    }


}
