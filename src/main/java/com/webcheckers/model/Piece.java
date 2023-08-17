package com.webcheckers.model;

import java.util.Random;

/**
 * the piece model to be placed on the space
 */
public class Piece {

    /**
     * enum to define type of piece
     *
     */
    public enum TYPE {
        SINGLE, KING
    }

    /**
     * enum for color of piece
     */
    public enum COLOR{
        RED, WHITE
    }

    //attributes
    private TYPE pieceType;
    private COLOR pieceColor;
    private String Idx;
    private static Random random = new Random();

    /**
     * Constructs a piece object
     * @param type - the type of piece, either a single or type
     * @param color - the color of the piece, red or white
     */
    public Piece(TYPE type, COLOR color){
        this.pieceType = type;
        this.pieceColor = color;
        this.Idx = Integer.toString(random.nextInt(1000000000));
    }

    /**
     * get the type of piece
     * @return this.pieceType
     */
    public TYPE getType(){
        return this.pieceType;
    }

    public String getIdx() {
        return this.Idx;
    }

    /**
     * get the color of the piece
     * @return this.pieceColor
     */
    public COLOR getColor(){
        return this.pieceColor;
    }

    /**
     *  the equals function
     * @param p: a type of Piece class
     * @return boolean true or false
     */
    public boolean equals(Piece p){
        if(this.getColor() == p.getColor())
            return this.getType() == p.getType();
        return false;
    }

}
