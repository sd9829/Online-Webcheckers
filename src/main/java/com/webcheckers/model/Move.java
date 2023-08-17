package com.webcheckers.model;


/**
 * The move class stores a move based on a start and end position
 *
 */
public class Move {
    // attributes
    private Position start, end;
    private boolean isValid;

    /**
     * Constructs a move object based on the start and end positions
     * @param start the starting position
     * @param end  the end position
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
        this.isValid = false;
    }

    public boolean getIsValid() {
        return this.isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * Gets the starting position
     * @return The starting position
     */
    public Position getStart() {
        return start;
    }

    /**
     * Gets the ending position
     * @return the ending position
     */
    public Position getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Move{start:" + start + ", end:" + end + "}";
    }
}
