package com.webcheckers.model;

/**
 * The space class represents a single cell
 */
public class Space {
    public enum Color {
        WHITE, BLACK
    }
    //represents the different types of spaces on a board
    private int cellIdx;
    private Piece piece;
    private Color color;

    /**
     * The constructor for the space
     * @param cellIdx - the unique id for the space
     * @param piece - the piece occupying the space
     */
    public Space(int cellIdx, Piece piece, Color color) {
        this.cellIdx = cellIdx;
        this.piece = piece;
        this.color = color;
    }

    /**
     * Constructor for a space with no piece currently occupying it
     * @param cellIdx - the unique id for the space
     */
    public Space(int cellIdx, Color color) {
        this.cellIdx = cellIdx;
        this.color = color;
    }

    /**
     * Checks if a current space is valid for a move based on following
     * conditions
     * 1) if space is white, then automatically false
     * 2) if space is black and occupied, then false
     * 3) true otherwise
     * @return - true if valid, false if invalid
     */
    public boolean isValid(){
        if(this.cellIdx % 2 == 0){ //represents all white cells due to how indexing works
            return false;
        }
        else return this.piece == null; // if piece attribute is null, then this space is
                                        //empty and valid
                                        //otherwise it is invalid
    }
    /**
     * Returns the cell id
     * @return - the value of the cell
     */
    public int getCellIdx() {
        return cellIdx;
    }

    /**
     * Returns the piece occupying the cell
     * @return - the Piece occupying the cell, null if no piece is there
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * Returns the color of the cell
     * @return - the color of the cell
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the piece attribute
     * @param piece - the piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Space{cellIdx:" + cellIdx + ", piece:" + piece + ", color:" + color+  "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Space){
            if(((Space) obj).getPiece() != null && this.piece != null){
                return this.piece.equals(((Space) obj).getPiece()) &&
                        this.cellIdx == ((Space) obj).getCellIdx() &&
                        this.color.equals(((Space) obj).getColor());
            }
            return this.cellIdx == ((Space) obj).getCellIdx() &&
                    this.color.equals(((Space) obj).getColor());


        }
        return false;
    }
}
