package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Logger;

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
 * The {@code POST /signout} route handler.
 *
 */
public class PostSignOutRoute implements Route {

  //
  // Constants
  //
  private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());
  private static final String VIEW_NAME = "signout.ftl";
  private static final String TITLE = "Sign Out";
  private final static Message SIGNOUT_MSG = Message.info("Sign out of the world of online Checkers.");
  private final static Message SIGNOUT_FAIL_MSG = Message.info("No user to sign out, try signing in first!");

  //
  // Attributes
  //

  private TemplateEngine templateEngine;
  private PlayerLobby playerLobby;

  //
  // Constructor
  //

  /**
   * The constructor for the {@code POST /signout} route handler.
   *
   * @param templateEngine
   *    template engine to use for rendering HTML page
   *
   * @throws NullPointerException
   *    when the {@code gameCenter} or {@code templateEngine} parameter is null
   */
  public PostSignOutRoute(TemplateEngine templateEngine, PlayerLobby playerLobby) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerlobby is required");
//    this.templateEngine = templateEngine;
//    this.playerLobby = playerLobby;
    LOG.config("PostSignOutRouote is initialized.");
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

    // display a title in the Sign Out page
    vm.put(UIConstants.AttributeKeys.TITLE, TITLE);

    // display a user message in the Sign Out page
    vm.put(UIConstants.AttributeKeys.MESSAGE, SIGNOUT_MSG);

    final Player player = session.attribute(UIConstants.AttributeKeys.CURRENT_USER);

    //if player is not null
    if(player != null) {
      try {
        //attempt to sign player out
        playerLobby.removePlayer(player);

        // store player object in vm
        vm.put(UIConstants.AttributeKeys.CURRENT_USER, player);

        //store player object in current http session
        session.attribute(UIConstants.AttributeKeys.CURRENT_USER, null);

        //unsure of, might be useful prob not
        session.attribute(UIConstants.AttributeKeys.SIGNED_IN, false);

        //if singout success, redirect to home
        response.redirect("./signin");
      } catch (Exception e) {
        //if singout fails
        vm.put(UIConstants.AttributeKeys.MESSAGE, SIGNOUT_FAIL_MSG);
      }
    }

    //create currentUser data field
    vm.put(UIConstants.AttributeKeys.CURRENT_USER, player);

    return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
  }
}
