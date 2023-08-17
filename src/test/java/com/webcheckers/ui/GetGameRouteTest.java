package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetGameRouteTest {
    private GetGameRoute CuT;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;

    GetGameRouteTest(){

    }
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        this.playerLobby = new PlayerLobby();
        CuT = new GetGameRoute(engine);
    }
    @Test
    public void new_game(){
        final PlayerLobby playerLobby = new PlayerLobby();
        when(session.attribute(GetHomeRoute.PLAYERLOBBY)).thenReturn(playerLobby);
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        CuT.handle(request,response);
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetGameRoute.TITLE);
        testHelper.assertViewModelAttribute(GetGameRoute.GAME_BEGINS_ATTR, Boolean.TRUE);
        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }

    @Test
    public void faulty_session(){
        when(session.attribute(GetHomeRoute.PLAYERLOBBY)).thenReturn(null);
        try{
            CuT.handle(request, response);
            fail("Redirects invoke halt exceptions.");


        } catch(HaltException e){
            //expected

        }

        verify(response).redirect(WebServer.HOME_URL);
    }


}
