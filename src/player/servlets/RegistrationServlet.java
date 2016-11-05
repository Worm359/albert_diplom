package player.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import player.database.hibernate.entities.User;
import player.database.hibernate.utilities.SessionWrapper;
import player.servlets.utilities.ServletUtilities;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);
    //private SessionFactory session_factory = null;
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {

        logger.debug("RegistrationServlet entry");

        String login = null;
        String password = null;
        SessionWrapper sessionWrapper = new SessionWrapper();
        Session hibernate_session = null;
        Query query_s = null;
        User usr = null;
        boolean isSuchUserExists = false;
        login = req.getParameter("login_reg");
        password = req.getParameter("password_reg");


        if(!this.discoverInputError(req, resp))
        {
            if(!ServletUtilities.openSessionIfNullRedirect(req, resp, sessionWrapper))
            {
                hibernate_session = sessionWrapper.getSession();
                hibernate_session.beginTransaction();
                query_s = hibernate_session.createQuery("from User");
                List users = query_s.list();
                logger.debug("Amount of records:" + users.size());
                hibernate_session.getTransaction().commit();
                if ((users != null) && (users.size() != 0)) {
                    for (Object iter : users) {
                        usr = (User) iter;
                        logger.info("Found: UserID = " + usr.getUserID() + "UserPassword = " + usr.getUserPassword());
                        if (usr.getUserID().equals(login))
                            isSuchUserExists = true;
                    }
                }
                if (isSuchUserExists != true) {
                    logger.debug("There are no such user in DB");
                    hibernate_session.beginTransaction();
                    usr = new User(login, password);
                    hibernate_session.save(usr);
                    hibernate_session.getTransaction().commit();
                }
                else{
                    this.redirectToErrorPage(req, resp, "User with such login already exists - try something else", hibernate_session);
                    return;
                }
                hibernate_session.close();
                ServletUtilities.redirectToPath(req, resp, "/authentication/authentication.jsp");
            }
        }
    }
    private void redirectToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message)
    {

            HttpSession session = req.getSession();
            session.setAttribute("registrationError", message);
            logger.debug(message);
            ServletUtilities.redirectToPath(req, resp, "authentication/registration.jsp");
    }
    private void redirectToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message, Session hibernate_session)
    {
        this.redirectToErrorPage(req, resp, message);
        if(hibernate_session!=null)
            hibernate_session.close();
    }
    private boolean discoverInputError(HttpServletRequest req, HttpServletResponse resp)
    {
        String login = req.getParameter("login_reg");
        String password = req.getParameter("password_reg");
        logger.debug("Registration servlet. Login and password are not null and not empty");
        logger.debug("Entered login:" + login + " entered password: " + password);
        if((login.equals(""))||(password.equals(""))||(!password.matches("[a-zA-Z0-9\\u005F]+"))||(!login.matches("[a-zA-Z0-9\\u005F]+")))
        {
            redirectToErrorPage(req, resp, "Login or password typing rules violation");
            return true;
        }
        if((login==null)||(password==null))
        {
            redirectToErrorPage(req, resp, "Cannot read typed values");
            return true;
        }
        return false;
    }
}
