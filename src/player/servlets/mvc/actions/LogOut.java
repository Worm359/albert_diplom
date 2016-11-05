package player.servlets.mvc.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements player.servlets.mvc.Action
{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session;
        session = request.getSession();
        synchronized(session) {session.invalidate();}
        return "/authentication/authentication.jsp";
    }
}
