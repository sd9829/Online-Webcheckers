package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.util.Message;
import spark.*;

public class PostResignGameRoute implements Route {
   private final Gson gson;

   public PostResignGameRoute() {
       this.gson = new Gson();
   }

   @Override
   public Object handle(Request request, Response response) throws Exception {
    System.out.println("PostResignGameRoute is invoked.");
    Message message = Message.info("Game resigned"); //success response
    System.out.println("Resign Success: Redirect to home.");
    response.redirect("./");
    return gson.toJson(message);
   }
}

