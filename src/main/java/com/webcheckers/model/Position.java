package com.webcheckers.model;

/**
 * The position class represents a pair of coordinates on the
 * board
 */
public class Position {
    //attributes
    private final int row, cell;

    /**
     * Constructs a position object
     * @param row  the row of the coordinates
     * @param cell the cell (column) of the object
     */
    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * Gets the row
     * @return
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the cell/column of the position
     * @return - the cell/column of the position
     */
    public int getCell() {
        return this.cell;
    }

    @Override
    public String toString() {
        return "Postition{row:" + row + ", cell:" + cell + "}";
    }
}
