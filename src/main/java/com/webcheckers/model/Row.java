package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * represents one Row in the game on the Board
 */

public class Row implements Iterable<Space>{
    private ArrayList<Space> spaces;

    private int index;


    /**
     * get the row index
     * @return this.index
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * Constructs a row using one method
     * @param spaces - the List of space objects
     * @param index the index of the row
     */
    public Row(ArrayList<Space> spaces, int index) {
        this.spaces = spaces;

        this.index = index;
    }





    /**
     * Other method for constructor
     * @param spaces - the list of space objects
     */
    public Row(ArrayList<Space> spaces) { //we weren't sure which one was which
        this.spaces = spaces;

    }


    /**
     * get the spaces
     * @return this.spaces
     */
    public ArrayList<Space> getSpaces() {
        return this.spaces;
    }

    /**
     * creating an iterator of the spaces
     * @return iterator
     */
    @Override
    public Iterator<Space> iterator(){
        return spaces.listIterator();
    }

    @Override
    public String toString() {
        return "Row{spaces:" + this.spaces + " index:" + this.index + "}";
    }
}

