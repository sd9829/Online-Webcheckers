package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

public class PostCheckTurnRoute implements Route {
   private final Gson gson;
   private final PlayerLobby playerLobby;

   public PostCheckTurnRoute(PlayerLobby playerLobby) {
       this.gson = new Gson();
       this.playerLobby = playerLobby;
   }

   @Override
   public Object handle(Request request, Response response) throws Exception {
    System.out.println("PostCheckTurnRoute is invoked.");
    Message message = Message.info("false"); //success response
    Game game = playerLobby.getActiveGame(request.session().attribute(UIConstants.AttributeKeys.GAME_ID));
    String activeColor = game.getActiveColor();
    Player currentUser =  request.session().attribute(UIConstants.AttributeKeys.CURRENT_USER);
   
    if(currentUser == (activeColor.equals("RED") ? game.getRedPlayer() : game.getWhitePlayer())) {
        message = Message.info("true");
    }
    return gson.toJson(message);
   }
}
