package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 */
public class GetHomeRoute implements Route {
    public static final String PLAYERLOBBY = "Player Lobby";
  public static final String TITLE_ATTR = "Player Lobby";
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private final static Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  private static final String VIEW_NAME = "home.ftl";
  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");


    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    System.out.println("GetHomeRoute is invoked.");
    Map<String, Object> vm = new HashMap<>();
    
    //current http session
    final Session session = request.session();

    String welcomeMessage = "Welcome!";

    // if this is a brand new browser session or a session that timed out
    if(session.attribute(UIConstants.AttributeKeys.PLAYER_COUNT) == null) {
      //store count of active players in session
      session.attribute(UIConstants.AttributeKeys.PLAYER_COUNT, playerLobby.getPlayerCount());
    }

    // if this is a brand new browser session or a session that timed out
    if(session.attribute(UIConstants.AttributeKeys.CURRENT_PLAYERS) == null) {
      //store list of player names in session
      session.attribute(UIConstants.AttributeKeys.CURRENT_PLAYERS, playerLobby.getPlayersArray());
    }
    
    //gets playerCount from playerLobby
    int playerCount = playerLobby.getPlayerCount();

    //gets list of current player names from playerLobby
    Player[] currentPlayers = playerLobby.getPlayersArray();

    //if user is signed in, add their name to welcome message
    Player currentUser = session.attribute(UIConstants.AttributeKeys.CURRENT_USER);
    if(currentUser != null) {
      //personalize welcome message
      welcomeMessage = "Welcome " + currentUser.getName() + "!";
      
      //if any waiting games are waitng for currentUser
      Game currentGame = playerLobby.searchWaitingGames(currentUser);
      if(currentGame != null) {
        response.redirect("./game");
      }
    }

    // display player name if signed in
    vm.put(UIConstants.AttributeKeys.CURRENT_USER, currentUser);

    // display current player count
    vm.put(UIConstants.AttributeKeys.PLAYER_COUNT, playerCount);

    // display current player list
    vm.put(UIConstants.AttributeKeys.CURRENT_PLAYERS, currentPlayers);

    // display a welcome message
    vm.put(UIConstants.AttributeKeys.TITLE, welcomeMessage);

    // display a user message
    vm.put(UIConstants.AttributeKeys.MESSAGE, WELCOME_MSG);



    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}
