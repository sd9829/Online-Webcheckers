package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Move;
import com.webcheckers.model.MoveValidation;
import com.webcheckers.util.Message;
import spark.*;

/**
* Sends ajax request to server after player moves a piece from point A to B
*
*/
// If the move is valid then the client-side code transitions to the Stable Turn state.
// In this state the Game View only has the starting piece active; no other piece can be moved
// (dragged-and-dropped). Goes to submitTurn action
public class PostValidateMoveRoute implements Route {
   private final Gson gson;

   public PostValidateMoveRoute() {
       this.gson = new Gson();
   }

   @Override
   public Object handle(Request request, Response response) throws Exception {
       System.out.println("PostValidateMoveRoute is invoked.");
       Message message = Message.info("Valid move"); //success response
       BoardView board = request.session().attribute(UIConstants.AttributeKeys.GAME_BOARD);
       Move move = gson.fromJson(request.queryParams("actionData"), Move.class);
       move.setIsValid(true);
       String errorMessage = MoveValidation.validate(board, move, false);
       if(errorMessage != null) {
           message = Message.error(errorMessage); //failure response
       }
       //if move is valid
       else {
            //if moveEnqueuing fails, set failure response and clear queue
            if(!board.enqueueMove(move)) {
                message = Message.error("Move already played, did you refresh mid-turn?");
                board.clearQueuedMoves();
            }
       }
       return gson.toJson(message);
   }
}
