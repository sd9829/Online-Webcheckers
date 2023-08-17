//package com.webcheckers.model;
//
//import org.junit.jupiter.api.Test;
//import org.junit.platform.commons.annotation.Testable;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@Testable
//public class SpaceTest {
//    @Test
//    void isValidTest() {
//        Piece piece = null;
//        int cellIdx = 5;    //randomly assigned
//        Space space = new Space(cellIdx, piece);
//        assertTrue(space.isValid());
//        assertTrue(space.isValid());
//        //passes
//
//    }
//
//    @Test
//    void getCellIdxTest() {
//        Piece piece = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE);
//        int cellIdx = 5;    //randomly assigned
//        Space space = new Space(cellIdx, piece);
//        int expected = 5;
//        assertEquals(expected, space.getCellIdx());
//        //passes
//    }
//
//    @Test
//    void getPieceTest() {
//        Piece piece = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE);
//        int cellIdx = 5;    //randomly assigned
//        Space space = new Space(cellIdx, piece);
//        Piece expected = piece;
//        assertEquals(expected, space.getPiece());
//        //passes
//
//    }
//
//    @Test
//    void setPieceTest() {
//        Piece piece = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE);
//        int cellIdx = 5;    //randomly assigned
//        Space space = new Space(cellIdx, piece);
//        space.setPiece(piece);
//        Piece expected = piece;
//        Piece result = space.getPiece();
//        assertEquals( expected, result);
//        //passes
//
//
//    }
//}
