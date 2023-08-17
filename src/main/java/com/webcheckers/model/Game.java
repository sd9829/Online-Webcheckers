package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Game.java
 * 
 * A class to handle a single game and perform actions such as looking up a
 * player in the game, and constructing the board, and designing a
 *
 */
public class Game {

	// attributes
	private boolean inProgress = false;
	private String gameId, activeColor;
	private int numberRedPieces;
	private int numberWhitePieces;

	private ArrayList<Piece> redPieces = new ArrayList<Piece>();
	private ArrayList<Piece> whitePieces = new ArrayList<Piece>();
	private BoardView board;
	private final Player redPlayer, whitePlayer;
	public boolean player1Joined, player2Joined;

	/**
	 * Game constructor creates a game instance
	 * 
	 * @param redPlayer - the name of player 1
	 * @param whitePlayer - the name of player 2
	 */
	public Game(Player redPlayer, Player whitePlayer) {
		this.gameId = generateUUID();
		this.board = new BoardView();
		redPlayer.setCurrentGameID(this.gameId);
		whitePlayer.setCurrentGameID(this.gameId);
		this.redPlayer = redPlayer;
		this.whitePlayer = whitePlayer;
		this.numberRedPieces = 12;
		this.numberWhitePieces = 12;
		this.player1Joined = false;
		this.player2Joined = false;
		this.activeColor = "RED";

		makePieces();
		fillBoard();

		inProgress = false;
	}


	/**
	 * Private method to fill the board with spaces and the correct amount of pieces
	 */

	private synchronized void fillBoard() {
		Space space;
		Position[] redPiecePositions = getRedPieceLocations();
		Position[] whitePiecePositions = getWhitePieceLocations();

		for (int i = 0; i < redPieces.size(); i++) {
			space = board.getSpace(redPiecePositions[i]);
			space.setPiece(redPieces.get(i));
		}

		for (int i = 0; i < whitePieces.size(); i++) {
			space = board.getSpace(whitePiecePositions[i]);
			space.setPiece(whitePieces.get(i));
		}
	}

	/**
	 * Gets the white piece locations on the board
	 * 
	 * @return - the collection of location objects
	 */

	private static Position[] getWhitePieceLocations() {
		ArrayList<Position> piecePositions = new ArrayList<>();
		piecePositions.add(new Position(0, 1));
		piecePositions.add(new Position(0, 3));
		piecePositions.add(new Position(0, 5));
		piecePositions.add(new Position(0, 7));
		piecePositions.add(new Position(1, 0));
		piecePositions.add(new Position(1, 2));
		piecePositions.add(new Position(1, 4));
		piecePositions.add(new Position(1, 6));
		piecePositions.add(new Position(2, 1));
		piecePositions.add(new Position(2, 3));
		piecePositions.add(new Position(2, 5));
		piecePositions.add(new Position(2, 7));

		return piecePositions.toArray(new Position[piecePositions.size()]);
	}

	/**
	 * Gets the red piece locations on the board
	 * 
	 * @return- the collection of location objects
	 */
	private static Position[] getRedPieceLocations() {
		ArrayList<Position> piecePositions = new ArrayList<>();
		piecePositions.add(new Position(5, 0));
		piecePositions.add(new Position(5, 2));
		piecePositions.add(new Position(5, 4));
		piecePositions.add(new Position(5, 6));
		piecePositions.add(new Position(6, 1));
		piecePositions.add(new Position(6, 3));
		piecePositions.add(new Position(6, 5));
		piecePositions.add(new Position(6, 7));
		piecePositions.add(new Position(7, 0));
		piecePositions.add(new Position(7, 2));
		piecePositions.add(new Position(7, 4));
		piecePositions.add(new Position(7, 6));

		return piecePositions.toArray(new Position[piecePositions.size()]);
	}

	/**
	 * Generates an unique id for each game
	 * 
	 * @return - A string of the id
	 */
	private static String generateUUID() {
		return Integer.toString(new Random().nextInt(10000000));
	}

	/**
	 * Determines if a board contains a player
	 * 
	 * @param player - the player to be searched for in the board
	 * @return -true if the player is contained false if otherewise
	 */
	public boolean containsPlayer(Player player) {
		return this.redPlayer.equals(player) || this.whitePlayer.equals(player);
	}

	/**
	 * Makes all the pieces for the board
	 *
	 */
	private void makePieces() {
		for (int i = 0; i < 12; i++) {
			Piece red = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.RED);
			Piece white = new Piece(Piece.TYPE.SINGLE, Piece.COLOR.WHITE);
			redPieces.add(red);
			whitePieces.add(white);
		}
	}

	/**
	 * returns if a game is in progress or not
	 * 
	 * @return - true if yes, false if not
	 */
	public boolean getProgress() {
		return inProgress;
	}

	/**
	 * Returns the result of the game
	 * 
	 * @return - the winning team represented as a string message
	 */
	public String result() {
		if (this.numberWhitePieces == 0) {
			return "Red Wins!";
		} else if (this.numberRedPieces == 0) {
			return "White Wins!";
		}
		return "Finish the game before asking the result.";
	}

	/**
	 * Gets the id for the game
	 * 
	 * @return - The id represented as a string
	 */
	public String getID() {
		return this.gameId;
	}

	/**
	 * Gets the first player for the game
	 * 
	 * @return - the first player object
	 */
	public Player getRedPlayer() {
		return this.redPlayer;
	}

	/**
	 * The player object representing the second player
	 * 
	 * @return - the second player object
	 */
	public Player getWhitePlayer() {
		return this.whitePlayer;
	}

	/**
	 * Retrieves the board for this game
	 * 
	 * @return - the board object
	 */
	public BoardView getBoard() {
		return this.board;
	}
	  
	/**
	 * Removes a piece from the board if jumped. Updates the piece arrays and
	 * board view after a move.
	 * @param pos, jumped
	 */
	public void jumpedPiece(Position pos, Piece jumped, Move move, Space space) {
		if (move.getIsValid()) {
			if (jumped.getColor() == Piece.COLOR.RED) {
				for (int i = 0; i < redPieces.size(); i++) {
					// if (redPieces.get(i).getPosition().equals(pos)) {
					// 	redPieces.remove(i);
					// 	space.setPiece(null);
					// 	break;
					// }
				}
			}
			else {
				for (int i = 0; i < whitePieces.size(); i++) {
					// if (whitePieces.get(i).getPosition().equals(pos)) {
					// 	whitePieces.remove(i);
					// 	space.setPiece(null);
					// 	break;
					// }
				}
			} 
		}
	}
	
	public String getActiveColor() {
		return this.activeColor;
	}

	public void switchActiveColor() {
		this.activeColor = this.activeColor.equals("RED") ? "WHITE" : "RED" ;
	}
	
}
