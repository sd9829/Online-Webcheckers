package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * The PlayerLobby stores all the players that have ever logged into the game in a hashmap
 * for easy lookup
 */
public class PlayerLobby {
    private HashMap <String, Player> playerList;
    private HashMap <String, Game> activeGames;
    private HashMap <String, Game> waitingGames;

    // /// constructor, think this is right but not sure if playerList object will be
    // /// created in class that calls PlayerLobby or if it is created here and returned.
    // public PlayerLobby(HashMap<String, Player> playerList) {
    //     this.playerList = playerList;
    // }

    /**
     * additional constructor for playerlobby init
     */
    public PlayerLobby() {
        this.playerList = new HashMap<String, Player>();
        this.activeGames = new HashMap<String, Game>();
        this.waitingGames = new HashMap<String, Game>();
    }

    /**
     * Creates a new game when two players have agreed to play
     *
     * @param player1 - the first player
     * @param player2 - the second player
     * @return - true if players are valid, false are otherwise
     */
    public Game createGame(Player player1, Player player2) {
        //validates selectedPlayer with playerLobby
        if(isPlayerValid(player1) && isPlayerValid(player2)) {
            //create new game with both players
            Game game = new Game(player1, player2);
    
            //add new game to waiting games
            this.addWaitingGame(game);
            player1.setCurrentGameID(game.getID());
            player2.setCurrentGameID(game.getID());
            return game;
        }
        return null;
    }

    //moves given game from waitingGames to activeGames
    public void startGame(String gameId) {
        if(waitingGames.containsKey(gameId)) {
            Game game = waitingGames.remove(gameId);
            activeGames.put(gameId, game);
        }
        else {
            throw new IllegalArgumentException("Invalid game supplied");
        }
    }

    /**
     * Shows all waiting games requiring a waiting player
     * @param player - the player object of the waiting player
     * @return - a game that is waiting on a player
     */

    public Game searchWaitingGames(Player player) {
        //only retrieve waiting games if user is signed in
        if(waitingGames.size() > 0) {
            //iterate over waitingGames
            for (Game game : this.getWaitingGames()) {
                //if player is in any waitingGame
                if(game.containsPlayer(player)) {
                    return game;
                }
            }
        }
        return null;
    }

    //#region Player List

    /**
     * validates players based on the possibility of synchronized games
     * i.e. if a player is invited to one game but then another player invites the same player
     * this would be not allowed  //we think this is required
     * @param player - the player to be validated
     * @return - true if validated, false if otherewise
     */
    public synchronized boolean isPlayerValid(Player player) {
        //checks if player is in playerList
        if(this.playerList.containsValue(player)) {
            //checks if player is in a game currently
            if(player.getCurrentGameID().equals("")) {
                //if both checks pass, return true
                return true;
            }
        }
        //if either check fails, return false
        return false;
    }

    /**
     * Looks up a player in the hashmap
     * @param name - the Player, represented as a string
     * @return - The player if found, null if not
     */
    public synchronized Player getPlayer(String name){

        Player player = playerList.get(name);
        if(player != null) {
            return player;
        }
        else{
            return null;
        }
    }

    /**
     * returns current player count
     * @return this.playerList.size
     */
    public synchronized int getPlayerCount() {
        return this.playerList.size();
    }

    /**
     * returns current array of player names
     * @return this.playerList.keySet
     */
    public synchronized String[] getPlayerNamesArray() {
        //get set of keys (playernames)
        Set<String> keySet = this.playerList.keySet();

        //return array of keys (playernames)
        return keySet.toArray(new String[this.playerList.size()]);
    }

    /**
     * returns current array of player objects
     * @return arrays of keys of this.playerList.size
     */
    public synchronized Player[] getPlayersArray() {
        //get set of keys (playernames)
        Collection<Player> values = this.playerList.values();

        //return array of keys (playernames)
        return values.toArray(new Player[this.playerList.size()]);
    }

    /**
     * Adds a player to the hashmap
     * @param player - the player to be added
     */
    public synchronized void addPlayer(Player player){
        if(!playerList.containsKey(player.getName())) {
            playerList.put(player.getName(), player);
            return;
        }
        throw new IllegalArgumentException("Invalid user to add");
    }

    /**
     * Removes a player from the hashmap
     * @param player - the player to be removed
     */
    public synchronized void removePlayer(Player player){
        if(playerList.containsKey(player.getName())) {
            playerList.remove(player.getName());
            return;
        }
        throw new IllegalArgumentException("Invalid user to remove");
    }
    
    //optional override (not sure if needed yet)

    /**
     * Removes a player from the hashmap
     * @param playerName - the player to be removed
     */
    public synchronized void removePlayer(String playerName){
        if(playerList.containsKey(playerName)) {
            playerList.remove(playerName);
            return;
        }
        throw new IllegalArgumentException("Invalid user to remove");
    }

    //#endregion

    //#region Active Games

    /**
     * Looks up a game in the activeGames hashmap
     * @param gameID - the Game, represented as a string
     * @return - The game if found, null if not
     */
    public synchronized Game getActiveGame(String gameID){
        return this.activeGames.get(gameID);
    }

    /**
     * returns current active game count
     * @return this.activeGames.size
     */
    public synchronized int getActiveGameCount() {
        return this.activeGames.size();
    }

    /**
     * returns current array of active game IDs
     * @return this.activeGames.keySet
     */
    public synchronized String[] getActiveGameIDs() {
        //get set of keys
        Set<String> keySet = this.activeGames.keySet();

        //cast to array and return
        return keySet.toArray(new String[this.activeGames.size()]);
    }

    /**
     * returns current array of active game objects
     * @return arrays of keys of this.activeGames.size
     */
    public synchronized Game[] getActiveGames() {
        //get set of values
        Collection<Game> values = this.activeGames.values();

        //cast to array and return
        return values.toArray(new Game[this.activeGames.size()]);
    }

    /**
     * Adds a game to the activeGames hashmap
     * @param game - the game to be added
     */
    public synchronized void addActiveGame(Game game){
        if(!activeGames.containsKey(game.getID())) {
            activeGames.put(game.getID(), game);
            return;
        }
        throw new IllegalArgumentException("Invalid game to add");
    }

    /**
     * Removes a game from the activeGames hashmap
     * @param game - the game to be removed
     */
    public synchronized void removeActiveGame(Game game){
        if(activeGames.containsKey(game.getID())) {
            activeGames.remove(game.getID());
            return;
        }
        throw new IllegalArgumentException("Invalid game to remove");
    }
    
    //optional override (not sure if needed yet)

    /**
     * Removes a game from the activeGames hashmap
     * @param gameID - the ID of the game to be removed
     */
    public synchronized void removeActiveGame(String gameID){
        if(activeGames.containsKey(gameID)) {
            activeGames.remove(gameID);
            return;
        }
        throw new IllegalArgumentException("Invalid game to remove");
    }

    //#endregion

    //#region Waiting Games

    /**
     * Looks up a game in the waitingGames hashmap
     * @param gameID - the Game, represented as a string
     * @return - The game if found, null if not
     */
    public synchronized Game getWaitingGame(String gameID){
        return this.waitingGames.get(gameID);
    }

    /**
     * returns current waiting game count
     * @return this.waitingGames.size
     */
    public synchronized int getWaitingGameCount() {
        return this.waitingGames.size();
    }

    /**
     * returns current array of waiting game IDs
     * @return this.waitingGames.keySet
     */
    public synchronized String[] getWaitingGameIDs() {
        //get set of keys
        Set<String> keySet = this.waitingGames.keySet();

        //cast to array and return
        return keySet.toArray(new String[this.waitingGames.size()]);
    }

    /**
     * returns current array of waiting game objects
     * @return arrays of keys of this.waitingGames.size
     */
    public synchronized Game[] getWaitingGames() {
        //get set of values
        Collection<Game> values = this.waitingGames.values();

        //cast to array and return
        return values.toArray(new Game[this.waitingGames.size()]);
    }

    /**
     * Adds a game to the waitingGames hashmap
     * @param game - the game to be added
     */
    private synchronized void addWaitingGame(Game game){
        if(!waitingGames.containsKey(game.getID())) {
            waitingGames.put(game.getID(), game);
            return;
        }
        throw new IllegalArgumentException("Invalid game to add");
    }

    /**
     * Removes a game from the waitingGames hashmap
     * @param game - the game to be removed
     */
    private synchronized void removeWaitingGame(Game game){
        if(waitingGames.containsKey(game.getID())) {
            waitingGames.remove(game.getID());
            return;
        }
        throw new IllegalArgumentException("Invalid game to remove");
    }

    /**
     * Removes a game from the waitingGames hashmap
     * @param gameID - the gameID of the game to be removed
     */
    private synchronized void removeWaitingGame(String gameID){
        if(waitingGames.containsKey(gameID)) {
            waitingGames.remove(gameID);
            return;
        }
        throw new IllegalArgumentException("Invalid game to remove");
    }

    //#endregion
}
