package player.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import player.servlets.mvc.Action;
import player.servlets.mvc.ActionFactory;
import player.servlets.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ControllerServlet.class);
    ActionFactory actionFactory = ActionFactory.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*ServletUtilities.redirectToPath(request, response, "second.jsp");*/
        processRequest(request, response);
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        logger.debug("ControllerServlet ENTRY");
        String path = null;
        try
        {
            Action action = actionFactory.getAction(request);
            path = action.execute(request, response);
            logger.debug("action completed");
            //TODO you may expect special parameter from action here, which specify, redirect, or sendRedirect() - about post-get method
            if(path!=null)
            {
                ServletUtilities.redirectToPath(request, response, path);
            }
            else
            {
                logger.error("Controller doesn't redirect, action takes care of output or error redirection");
            }
        }
        catch (ServletException|IOException e)
        {
            logger.error(e);
            //TODO don't forget to redirect to error page or servlet
            e.printStackTrace();
        }

    }
}
