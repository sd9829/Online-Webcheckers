package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;

/**
 * The UI Controller to GET the signout page.
 */
public class GetSignOutRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetSignOutRoute.class.getName());
  
  private final static Message SIGNOUT_MSG = Message.info("Sign out of the world of online Checkers.");

  private static final String VIEW_NAME = "signout.ftl";
  private final TemplateEngine templateEngine;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /signout} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetSignOutRoute(final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    LOG.config("GetSignOutRoute is initialized.");
  }

  /**
   * Render the WebCheckers Sign Out page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Sign Out page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetSignOutRoute is invoked.");
    System.out.println("GetSignOutRoute is invoked.");
    // start building the View-Model
    Map<String, Object> vm = new HashMap<>();

    final Session session = request.session();

    // display a title in the Sign Out page
    vm.put(UIConstants.AttributeKeys.TITLE, "Sign Out");

    // display a user message in the Sign Out page
    vm.put(UIConstants.AttributeKeys.MESSAGE, SIGNOUT_MSG);

    final Player player = session.attribute(UIConstants.AttributeKeys.CURRENT_USER);

    // store player object in vm
    vm.put(UIConstants.AttributeKeys.CURRENT_USER, player);

    // render the View
    return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
  }

  public static String getViewName() {
    return VIEW_NAME;
  }
}
