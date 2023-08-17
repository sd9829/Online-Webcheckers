package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.util.Message;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.TemplateEngine;

/**
 * The UI Controller to GET the signin page.
 */
public class GetSigninRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

  private final static Message SIGNIN_MSG = Message.info("Sign in to the world of online Checkers.");

  private static final String VIEW_NAME = "signin.ftl";
  private final TemplateEngine templateEngine;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /signin} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetSigninRoute(final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    //
    LOG.config("GetSigninRoute is initialized.");
  }

  /**
   * Render the WebCheckers Sign In page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Sign In page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetSigninRoute is invoked.");
    System.out.println("GetSigninRoute is invoked.");
    // start building the View-Model
    Map<String, Object> vm = new HashMap<>();

    final Session session = request.session();

    // display a title in the Sign In page
    vm.put(UIConstants.AttributeKeys.TITLE, "Sign In");

    // display a user message in the Sign In page
    vm.put(UIConstants.AttributeKeys.MESSAGE, SIGNIN_MSG);

    // if this is a brand new browser session or a session that timed out
    if(session.attribute(UIConstants.AttributeKeys.INPUT_USERNAME) == null) {
      session.attribute(UIConstants.AttributeKeys.INPUT_USERNAME, "");
    }

    //redirect to home if signed in
    if(session.attribute(UIConstants.AttributeKeys.CURRENT_USER) != null) {
      System.out.println("GetSignInRoute: Redirect to home");
      response.redirect("./");
    }

    // render the View
    return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
  }
}
