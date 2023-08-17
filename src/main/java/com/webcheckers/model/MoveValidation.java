package com.webcheckers.model;


import com.webcheckers.model.Space.Color;


/**
 *The MoveValidation class validates a single move based on the rules of checkers
 *
 */
public class MoveValidation {

    /**
     * Validates a move based on the current state of the board and the move specified
     * @param board the current state of the board
     * @param move  the given move
     * @return null if the move is valid, otherwise a string containing why the move isn't valid
     */
    public static String validate(BoardView board, Move move, Boolean isKing) {
        String result = null;
        //System.out.println("Validating: " + move);
        if(isSpaceOccupied(board, move)) { result = "Cannot move to occupied spaces."; }
        if(isSpaceWhite(board, move)) { result = "Cannot move to white spaces."; }
        if(isMoveDistanceInvalid(move) && isCaptureDistanceInvalid(move)) { result = "Cannot move more than one unit horizontally or vertically unless capturing"; }
        return result;
    }

    /**
     * Helper method checks the ending positions color to see if its
     * white(bad) or black(good)
     * @param board the board parameter
     * @param move the move
     * @return true if it is black, false if otherwise
     */
    private static boolean isSpaceWhite(BoardView board, Move move) {
        Space space = board.getSpace(move.getEnd());
        Color color = space.getColor();
        if(color.equals(Color.WHITE)) { return true; }
        return false;
    }

    /**
     * Checks to see if the ending space is occupied
     * @param board - board object
     * @param move - the move object
     * @return - if piece is null and space unoccupied then returns true, false
     * if otherwise
     */
    private static boolean isSpaceOccupied(BoardView board, Move move) {
        Space space = board.getSpace(move.getEnd());
        if(space.getPiece() != null) { return true; }
        return false;
    }

    /**
     * Checks the distance between the starting and ending position
     * to ensure that the starting and end positions are no more than one unit apart
     * @param move - the move object
     * @return - true if greater than one distance, false if otherwise
     */
    private static boolean isMoveDistanceInvalid(Move move){
        if(move.getEnd().getRow() - move.getStart().getRow() != 1 || Math.abs(move.getEnd().getCell() - move.getStart().getCell()) != 1) {
            return true;
        }
        return false;
    }

    /**
     * Checks the distance between the starting and ending position
     * to ensure that the starting and end positions are no more than two units apart
     * for capturing a piece
     * @param move - the move object
     * @return - true if greater than one distance, false if otherwise
     */
    private static boolean isCaptureDistanceInvalid(Move move) {
        if(move.getEnd().getRow() - move.getStart().getRow() != 2 || Math.abs(move.getEnd().getCell() - move.getStart().getCell()) != 2) {
            return true;
        }
        return false;
    }

    /**
     * Checks the distance between the starting and ending position
     * to ensure that the starting and end positions are no more than one unit apart
     * @param move - the move object
     * @return - true if greater than one distance, false if otherwise
     */
    private static boolean isKingMoveDistanceInvalid(Move move){
        if(Math.abs(move.getEnd().getRow() - move.getStart().getRow()) > 1 || Math.abs(move.getEnd().getCell() - move.getStart().getCell()) > 1)
        {
            return true;
        }
        return false;
    }
}
