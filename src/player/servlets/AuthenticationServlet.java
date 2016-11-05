package player.servlets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import player.database.hibernate.entities.User;
import player.database.hibernate.utilities.SessionWrapper;
import player.servlets.utilities.ServletUtilities;

import javax.servlet.http.*;

public class AuthenticationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AuthenticationServlet.class);
    //private SessionFactory session_factory = null;
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        logger.debug("AuthenticationServlet entry");
        String login = null;
        String password = null;
        SessionWrapper sessionWrapper = new SessionWrapper();
        Session hibernate_session = null;
        Query query_s = null;
        User usr = null;
        boolean isSuchUserExists = false;

        login = req.getParameter("login_auth");
        password = req.getParameter("password_auth");
        logger.debug("Entered login:" + login + " entered password: " + password);
        if(!ServletUtilities.openSessionIfNullRedirect(req, resp, sessionWrapper))
        {
            hibernate_session = sessionWrapper.getSession();
            //hibernate_session = sessionFactory.getCurrentSession();
            hibernate_session.beginTransaction();
            /*query_s = hibernate_session.createQuery("from User where userID= :usID ");
            query_s.setParameter("usID", login);
            List users = query_s.list();*/
            usr = sessionWrapper.getSession().get(User.class, login);
            /*logger.debug("Amount of records:" + users.size());*/
            hibernate_session.getTransaction().commit();
            hibernate_session.close();
            /*if ((users != null) && (users.size() == 1)) */
            if (usr!=null)
            {
                /*usr = (User) users.get(0);*/
                logger.debug("usr.getUserPassword() = "+usr.getUserPassword());
                logger.debug("Input password = "+password);
                if (usr.getUserPassword().equals(password))
                {
                    Cookie formLoginAutoFilling = new Cookie("user_login", usr.getUserID());
                    formLoginAutoFilling.setMaxAge(60 * 60 * 24); // set autofilling of authentication form
                    resp.addCookie(formLoginAutoFilling);
                    HttpSession session = req.getSession();
                    session.setAttribute("user", usr.getUserID());
                    req.setAttribute("action", "displayUserPage");
                    logger.debug("Logged In");
                    ServletUtilities.redirectToPath(req, resp, "/app/controller");
                }
                else
                {
                    logger.debug("Password is incorrect - authorization filed");
                    ServletUtilities.redirectToPath(req, resp, "authentication/authentication.jsp");
                }
            }
            else
            {
                logger.debug("There are no such users in DB - authorization failed");
                ServletUtilities.redirectToPath(req, resp, "authentication/authentication.jsp");
            }
        }
    }

}
