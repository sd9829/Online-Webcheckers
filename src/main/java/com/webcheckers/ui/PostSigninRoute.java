package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;

/**
 * The {@code POST /signin} route handler.
 */
public class PostSigninRoute implements Route {

  //
  // Constants
  //

  private static final String VIEW_NAME = "signin.ftl";
  private static final String TITLE = "Sign In";
  private final static Message SIGNIN_MSG = Message.info("Sign in to the world of online Checkers.");
  private final static Message USERNAME_TAKEN_MSG = Message.info("Invalid username: Username already taken!");
  private final static Message INVALID_USERNAME_MSG = Message.info("Invalid username: No special characters allowed!");


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
  public PostSigninRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
    // validation
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine must not be null");;
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

    // display a title in the Sign In page
    vm.put(UIConstants.AttributeKeys.TITLE, TITLE);

    // display a user message in the Sign In page
    vm.put(UIConstants.AttributeKeys.MESSAGE, SIGNIN_MSG);
    
    // retrieve the game object
    final String username = request.queryParams(UIConstants.AttributeKeys.INPUT_USERNAME);
    final Player player = new Player(username);
    
    //if username field is not blank
    if(!username.equals("") && username.matches("[a-zA-Z0-9 ]*")) {
      try {
        //attempt to sign player in
        playerLobby.addPlayer(player);

        // store player object in vm
        vm.put(UIConstants.AttributeKeys.CURRENT_USER, player);

        //store player object in current http session
        session.attribute(UIConstants.AttributeKeys.CURRENT_USER, player);

        //unsure of, might be useful prob not
        session.attribute(UIConstants.AttributeKeys.SIGNED_IN, true);

        //if signin success, redirect to home
        System.out.println("PostSignInRoute: Redirect to home");
        response.redirect("./");
      } catch (Exception e) {
        //if signin fails
        vm.put(UIConstants.AttributeKeys.MESSAGE, USERNAME_TAKEN_MSG);
      }
    }
    else {
      vm.put(UIConstants.AttributeKeys.MESSAGE, INVALID_USERNAME_MSG);
    }

    return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
  }
}
