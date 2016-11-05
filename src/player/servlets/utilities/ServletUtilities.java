package player.servlets.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import player.database.hibernate.utilities.SessionFactoryRegisterer;
import player.database.hibernate.utilities.SessionWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtilities
{
    private static final Logger logger = LogManager.getLogger(ServletUtilities.class);

    /**
     * redirect request to a different jsp page or servlet.
     * if fails, than prints a message to response.
     * @param req
     * @param resp
     * @param path
     */
    public static boolean redirectToPath(HttpServletRequest req, HttpServletResponse resp, String path)
    {
        RequestDispatcher rd = req.getRequestDispatcher(path);
        try {
            rd.forward(req, resp);
            return true;
        }
        catch (ServletException|IOException e)
            {
                printErrorPage(req, resp, path);
                logger.error(e);
                return false;
            }
    }


    public static boolean redirectToPath(HttpServletRequest req, HttpServletResponse resp, String path, String par)
    {
        logger.debug("redirectToPath trying overload");
        try {
            logger.debug(req.getContextPath()+path);
            resp.sendRedirect(req.getContextPath()+path);
        } catch (IOException e) {
            printErrorPage(req, resp, path);
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * for session in a servlet
     * extends SessionFactoryRegisterer with redirecting.
     * returns false if session opens correctly
     * @param req
     * @param resp
     * @param session
     * @return
     */
    public static boolean openSessionIfNullRedirect(HttpServletRequest req, HttpServletResponse resp, SessionWrapper session)
    {
        if(!SessionFactoryRegisterer.openSessionCheckNotNull(session)) {
            logger.error("CANNOT OPEN SESSION");
            req.setAttribute("error", "You are trying to display paige, but server cannot find you in the DB. Sorry");
            ServletUtilities.redirectToPath(req, resp, "/app/errors/error.jsp");
            return true;
        }
        return false;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    public static String getAuthorizedUserID(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        synchronized(session)
        {
            String userID =(String) session.getAttribute("user");
            /*if (userID != null)
            {*/
            return userID;
            /*else
            {
                request.setAttribute("error", "Servlet tries to access ID of non-authorized user");
                ServletUtilities.redirectToPath(request, response, "/app/errors/error.jsp");
                return null;
            }*/
        }
    }
    private static void printErrorPage(HttpServletRequest req, HttpServletResponse resp, String path)
    {
        resp.setContentType("text/html");
        try {
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Sample Application Servlet Page</title>");
            out.println("<title>App Error</title>");
            out.println("</head>");
            out.println("<body bgcolor=white>");
            out.println("<h1>Internal application error occurred: cannot redirect you to:"+path+"</h1>");
            out.println("<h3>Your request was:"+req.getRequestURL()+"</h3>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        } catch (IOException e1) {
            logger.error(e1);
            e1.printStackTrace();
            throw new RuntimeException("ServletUtilities cannot redirect, nor print error page");
        }
    }
}
