package player.servlets.mvc.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import player.database.hibernate.entities.Subscription;
import player.database.hibernate.entities.User;
import player.database.hibernate.utilities.SessionWrapper;
import player.servlets.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionDisplayAllFriends implements player.servlets.mvc.Action
{
    private static final Logger logger = LogManager.getLogger(ActionDisplayAllFriends.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.debug("ActionDisplayAllFriends ENTRY");
        String userID = ServletUtilities.getAuthorizedUserID(request);
        User usr;
        SessionWrapper sessionWrapper = new SessionWrapper();
        if(!ServletUtilities.openSessionIfNullRedirect(request, response, sessionWrapper))
        {
            usr = sessionWrapper.getSession().get(User.class, userID);
            /*if(usr==null)
            {
                throw new RuntimeException("User trying to display paige, but he is not in database");
            }*/
            logger.debug("I found User"+usr.getUserID());
            for(Subscription i:usr.getSubscriptions())
            {
             logger.debug("Subscription found:"+i.getUser().getUserID());
            }
            request.setAttribute("userData", usr.getSubscriptions());
            //session.setAttribute("user", usr.getUserID());
            sessionWrapper.closeSession();
            return "/app/communication/user_friends.jsp";
        }
        else
        {
            return null;
        }
    }
}
