package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.*;

public class PostSubmitTurnRoute implements Route {
   private final Gson gson;
   private final PlayerLobby playerLobby;

   public PostSubmitTurnRoute(PlayerLobby playerLobby) {
       this.gson = new Gson();
       this.playerLobby = new PlayerLobby();
   }

   @Override
   public Object handle(Request request, Response response) throws Exception {
    System.out.println("PostSubmitTurnRoute is invoked.");
    Message message = Message.info("Turn submitted"); //success response
    BoardView board = request.session().attribute(UIConstants.AttributeKeys.GAME_BOARD);
    Game game = playerLobby.getActiveGame(request.session().attribute(UIConstants.AttributeKeys.GAME_ID));
    board.submitMoves();
    game.switchActiveColor();
    return gson.toJson(message);
   }
}
