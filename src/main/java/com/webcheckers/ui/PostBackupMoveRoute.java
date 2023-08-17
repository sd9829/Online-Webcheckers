package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.BoardView;
import com.webcheckers.util.Message;
import spark.*;

public class PostBackupMoveRoute implements Route {
   private final Gson gson;

   public PostBackupMoveRoute() {
       this.gson = new Gson();
   }

   @Override
   public Object handle(Request request, Response response) throws Exception {
    System.out.println("PostBackupMoveRoute is invoked.");
    Message message = Message.info("Turn submitted"); //success response
    BoardView board = request.session().attribute(UIConstants.AttributeKeys.GAME_BOARD);
    String errorMessage = null;
    if(!board.dequeueMove()) {
        errorMessage = "Error backing up move, did you refresh?";
        board.clearQueuedMoves();
    }
    if(errorMessage != null) {
        message = Message.error(errorMessage); //failure response
    }
    return gson.toJson(message);
   }


}
