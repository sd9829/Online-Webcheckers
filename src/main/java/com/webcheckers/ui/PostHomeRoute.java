package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;

/**
 * The {@code POST /home} route handler.
 */
public class PostHomeRoute implements Route {

  //
  // Constants
  //

  private static final String VIEW_NAME = "home.ftl";
  private final static Message MESSAGE = Message.info("Welcome to the world of online Checkers.");
  private final static Message FAIL_MESSAGE = Message.info("Error: Game cannot be created");
  
  //
  // Attributes
  //

  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;

  //
  // Constructor
  //

  /**
   * The constructor for the {@code POST /signin} route handler.
   *
   * @param templateEngine
   *    template engine to use for rendering HTML page
   *
   * @throws NullPointerException
   *    when the {@code gameCenter} or {@code templateEngine} parameter is null
   */
  public PostHomeRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
    // validation
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    this.playerLobby = Objects.requireNonNull(playerLobby, "templateEngine must not be null");;
  }

  //
  // TemplateViewRoute method
  //

  /**
   * {@inheritDoc}
   *
   * @throws NoSuchElementException
   *    when an invalid result is returned after making a guess
   */
  @Override
  public String handle(Request request, Response response) {
    // start building the View-Model
    final Map<String, Object> vm = new HashMap<>();
    //store session reference
    final Session session = request.session();
    
    
    //gets playerCount from playerLobby
    int playerCount = playerLobby.getPlayerCount();
    
    //gets list of current player names from playerLobby
    Player[] currentPlayers = playerLobby.getPlayersArray();
    
    // retrieve the player object
    Player currentUser = session.attribute(UIConstants.AttributeKeys.CURRENT_USER);
    
    String welcomeMessage = "Welcome!";
    
    if(currentUser != null) {
      welcomeMessage = "Welcome " + currentUser.getName() + "!";
    }
    
    String selectedPlayerName = request.queryParams(UIConstants.AttributeKeys.SELECTED_PLAYER);
    Player selectedPlayer = playerLobby.getPlayer(selectedPlayerName);

    vm.put(UIConstants.AttributeKeys.MESSAGE, MESSAGE);

    //if createGame succeeds
    Game game = playerLobby.createGame(currentUser, selectedPlayer);
    if(game != null) {
      //redirect player to newly created game page
      
      response.redirect("./game");
    }
    else {
      vm.put(UIConstants.AttributeKeys.MESSAGE, FAIL_MESSAGE);
    }

    // display player name if signed in
    vm.put(UIConstants.AttributeKeys.CURRENT_USER, currentUser);

    // display a title in the Sign In page
    vm.put(UIConstants.AttributeKeys.TITLE, welcomeMessage);

    // display a user message in the Sign In page

    // display current player count
    vm.put(UIConstants.AttributeKeys.PLAYER_COUNT, playerCount);

    // display current player list
    vm.put(UIConstants.AttributeKeys.CURRENT_PLAYERS, currentPlayers);

    return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
  }
}

