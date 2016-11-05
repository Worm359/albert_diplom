package player.servlets.mvc.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import player.database.hibernate.entities.User;
import player.database.hibernate.utilities.SessionWrapper;
import player.servlets.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionDisplayPage implements player.servlets.mvc.Action
{
    private static final Logger logger = LogManager.getLogger(ActionDisplayAllFriends.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.debug("ActionDisplayPage ENTRY");
        String userID = null;
        SessionWrapper sessionWrapper = new SessionWrapper();
        User usr = null;
        if(request.getParameter("other_user")==null||request.getParameter("other_user").equals(""))
        {
            userID = ServletUtilities.getAuthorizedUserID(request);
        }
        else {userID = request.getParameter("other_user");}
        if((!ServletUtilities.openSessionIfNullRedirect(request, response, sessionWrapper))&&(userID!=null))
        {
            usr = sessionWrapper.getSession().get(User.class, userID);
            if(usr==null)
            {
                return null;
            }
            request.setAttribute("userData", usr);
            sessionWrapper.closeSession();
            return "/app/users/user_page.jsp";
        }
        else
        {
            return null;
        }

    }
}
