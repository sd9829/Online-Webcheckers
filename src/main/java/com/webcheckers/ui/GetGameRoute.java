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
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the game page.
 */
public class GetGameRoute implements Route {
  public static final String TITLE = "GetGameRoute";
  public static final String GAME_BEGINS_ATTR = "Game has started";
  private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

  private final static Message MESSAGE = Message.info("Welcome to the world of online Checkers.");

  public static final String VIEW_NAME = "game.ftl";
  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /game} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetGameRoute(final TemplateEngine templateEngine, final PlayerLobby playerLobby) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    LOG.config("GetGameRoute is initialized.");
  }

  public GetGameRoute(TemplateEngine engine) {
    this.templateEngine = engine;
    this.playerLobby = null;
  }

    /**
   * Render the WebCheckers game page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the game page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetGameRoute is invoked.");
    System.out.println("GetGameRoute is invoked.");
    Map<String, Object> vm = new HashMap<>();

    //current http session
    final Session session = request.session();

    //if user is signed in, add their name to welcome message
    Player player = session.attribute(UIConstants.AttributeKeys.CURRENT_USER);

    //if player not signed in, do not render and redirect to home
    if(player == null) { response.redirect("./"); return null; }

    //get current game from player
    Game game = playerLobby.getWaitingGame(player.getCurrentGameID());

    session.attribute(UIConstants.AttributeKeys.GAME_BOARD, game.getBoard());
      
    // display a welcome message
    vm.put(UIConstants.AttributeKeys.TITLE, "TITLE");
    
    // display a user message
    vm.put(UIConstants.AttributeKeys.MESSAGE, MESSAGE);

    vm.put(UIConstants.AttributeKeys.GAME_ID, game.getID());

    // display player name if signed in
    vm.put(UIConstants.AttributeKeys.CURRENT_USER, player);
    
    vm.put(UIConstants.AttributeKeys.VIEW_MODE, "PLAY");
    
    vm.put(UIConstants.AttributeKeys.MODE_OPTIONS, "{}");

    vm.put(UIConstants.AttributeKeys.RED_PLAYER, game.getRedPlayer());
    
    vm.put(UIConstants.AttributeKeys.WHITE_PLAYER, game.getWhitePlayer());
    
    vm.put(UIConstants.AttributeKeys.ACTIVE_COLOR, "RED");

    //if current player is not player1 show flipped board otherwise, show normal board
    BoardView board = player.equals(game.getRedPlayer()) ? game.getBoard() : game.getBoard().flipped();

    //clear any queued moves on page refresh to prevent bugs
    board.clearQueuedMoves();

    //add board to vm
    vm.put(UIConstants.AttributeKeys.GAME_BOARD, board);

    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}

