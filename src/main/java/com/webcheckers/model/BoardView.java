package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * the class represents a Board in the game.
 */


public class BoardView implements Iterable<Row>{
    //possible moves - 
    //queued moves - moves queued for execution on turn submission
    private ArrayList<Move> possibleMoves;
    private Stack<Move> queuedMoves;
    private ArrayList<Row> rows;            //arrayList of the rows of the board


    /**
     * Creates a new board based on no parameters
     */
    public BoardView(){
        this.rows = makeRows();
        this.queuedMoves = new Stack<>();
    }

    /**
     * Creates a new board based on parameter of a collection of rows
     * @param rows - ArrayList of Rows
     */
    public BoardView(ArrayList<Row> rows){
        this.rows = rows;
        this.queuedMoves = new Stack<>();
    }

    /**
     * Flips the board for the perspective of the other player
     * @return - the fliped board
     */
    public BoardView flipped() {
        ArrayList<Row> flippedRows = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Row temp1 = rows.get(7-i);
            ArrayList<Space> spaces = new ArrayList<>();
            for(int j = 0; j < 8; j++){
                spaces.add(j, temp1.getSpaces().get(7-j));
            }
            flippedRows.add(i, new Row(spaces, i));
        }
        BoardView flipped = new BoardView(flippedRows);
        return flipped;
    }

    /**
     * private method to help make the  rows
     * @return - the array of rows
     */
    private ArrayList<Row> makeRows(){
        ArrayList<Row> rows = new ArrayList<>();
        for(int i = 0; i < 8; i ++){
            int val = 1;
            val *= i % 2 == 0 ? 1 : -1;
            ArrayList<Space> tempRow = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                val *= j % 2 == 0 ? -1 : 1;
                Space space = new Space(j, val > 0 ? Space.Color.BLACK : Space.Color.WHITE);
                tempRow.add(space);
                val = i % 2 == 0 ? 1 : -1;
            }
            rows.add(new Row(tempRow, i));
        }
        return rows;
    }

    public boolean containsPiece(Piece piece) {
        if(piece == null) { return false; }
		ArrayList<Space> spaces;
        Piece tempPiece;
		for (int i = 0; i < rows.size(); i++) {
			spaces = rows.get(i).getSpaces();
			for (int j = 0; j < spaces.size(); j++) {
                tempPiece = spaces.get(j).getPiece();
				if(tempPiece != null && tempPiece.equals(piece)) { return true; }
			}
		}
        return false;
    }

    public void movePiece(Piece piece, Move move) {
        if(this.containsPiece(piece)) {
            Space start = this.getSpace(move.getStart());
            Space end = this.getSpace(move.getEnd());
            if(end != null && start != null) {
                end.setPiece(piece);
                start.setPiece(null);
            }
        }
    }


    //queue system for moves to be executed on turn submission

    public boolean enqueueMove(Move move) {
        if(!this.queuedMoves.contains(move)) {
            this.queuedMoves.add(move);
            System.out.println("Enqueued. " + queuedMoves.toString());
            return true;
        }
        return false;
    }

    public boolean dequeueMove() {
        if(this.queuedMoves.size() > 0) {
            this.queuedMoves.pop();
            System.out.println("Dequeued. " + queuedMoves.toString());
            return true;
        }
        return false;
    }

    public void clearQueuedMoves() {
        System.out.println("Cleared. " + queuedMoves.toString());
        this.queuedMoves.clear();
    }

    public void submitMoves() {
        for (Move move : queuedMoves) {
            this.movePiece(this.getSpace(move.getStart()).getPiece(), move);
        }
        System.out.println("Submitted. " + queuedMoves.toString());
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    /**
     * Gets the spaces for the board based on a given position
     * @param position - a position object
     * @return - the row containing the given position
     */
    public Space getSpace(Position position) {
        Row selectedRow = this.rows.get(position.getRow());
        return selectedRow.getSpaces().get(position.getCell());
    }

    /**
     * Adds a move to possibleMoves
     * @param possibleMove - Possible move to add
     */
    public void addPossibleMove(Move possibleMove) {
        this.possibleMoves.add(possibleMove);
    }

    /**
     * creating an iterator of the rows
     * @return iterator
     */
    @Override
    public Iterator<Row> iterator(){
        return rows.listIterator();
    }


    /**
     * Used for testing if a board layout equals another board
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BoardView){
            return ((BoardView) obj).getRows().equals(this.rows);
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "";
        for (Row row : rows) {
            for (Space space : row) {
                string += space.getColor().toString().substring(0, 1);
                string += (space.getPiece() != null ? 1 : 0) + " ";
            }
            string += "\n";
        }
        return string;
    }
}
