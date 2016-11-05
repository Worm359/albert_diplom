package player.servlets.mvc.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import player.database.hibernate.entities.User;
import player.database.hibernate.utilities.SessionWrapper;
import player.servlets.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActionSearchUsers implements player.servlets.mvc.Action
{
    private static final Logger logger = LogManager.getLogger(ActionSearchUsers.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //TODO change to asynchronous xmlhttprequest
        String userName = ServletUtilities.getAuthorizedUserID(request);
        logger.debug("ActionSearchUsers ENTRY");
        SessionWrapper sessionWrapper = new SessionWrapper();
        ActionDisplayAllFriends otherAction = new ActionDisplayAllFriends();
        if(otherAction.execute(request, response)!=null)
        {
            logger.debug("ActionDisplayAllFriends executed");
            String searchString = request.getParameter("searchString");
            logger.debug("ActionDisplayAllFriends searchString equals "+searchString+" while userName="+userName);
            //request.getAttribute("userData");
            //walk trough it and keep only mathcing to search string
            if(!ServletUtilities.openSessionIfNullRedirect(request, response, sessionWrapper))
            {
                logger.debug("ActionDisplayAllFriends session opened");
                sessionWrapper.getSession().beginTransaction();
                List<User> users = sessionWrapper.getSession().createCriteria(User.class)
                        .add(Restrictions.like("userID", "%"+searchString+"%"))
                        .add(Restrictions.not(Restrictions.like("userID", userName)))
                        .list();
                /*Criteria crit = sessionWrapper.getSession().createCriteria(User.class)
                        .add(Restrictions.like("userID", "%"+searchString+"%"));*/

                sessionWrapper.getSession().getTransaction().commit();


                if(users.size()>0)
                {
                    for(User i:users)
                    {
                        logger.debug("Search query found user:" + i.getUserID());
                    }
                }
                else {logger.debug("Search query found no one :(((");}
                /*HttpSession httpSession = request.getSession();
                httpSession.setAttribute("searchData", users);*/
                request.setAttribute("searchData", users);
                sessionWrapper.getSession().close();
                return "/app/communication/user_friends.jsp";
            }
            else
            {
                logger.debug("Troubles opening session");
                return null;
            }
        }
        else
        {
            //MISTAKE - ActionDisplayAllFriends already redirected to mistake page
            return null;
        }

    }
}
