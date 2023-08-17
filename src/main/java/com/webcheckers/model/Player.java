package com.webcheckers.model;

import java.util.Objects;

/**
 * The player class represents a single player of the game,
 */
 ///right now only holds name but thinking later on functionality will increase
public class Player {
    //attributes
    private final String name;
    private String currentGameID;

    /**
     * Constructs the player
     * @param name - the players
     */
    public Player(String name) {
        this.name = name;
        this.currentGameID = "";
    }

    /**
     * Overrides the equals method to compare two players
     * @param o - an object
     * @return - true if the object is a player object and
     * equals the other player, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    /**
     * Generates a toString representing the player
     * @return - the string providing details for the entire player
     */
    @Override
    public String toString() {
        return "Player{name:" + this.name + " currentGameID:" + this.currentGameID + "}";
    }

    /**
     * Gets the name
     * @return - the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the getCurrentGameID
     * @return - the getCurrentGameID
     */
    public String getCurrentGameID() {
        return currentGameID;
    }

    /**
     * Sets the getCurrentGameID
     */
    public void setCurrentGameID(String currentGameID) {
        this.currentGameID = currentGameID;
    }
}
