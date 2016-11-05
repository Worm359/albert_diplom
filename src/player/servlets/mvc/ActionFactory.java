package player.servlets.mvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import player.servlets.mvc.actions.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ActionFactory
{
    private static final Logger logger = LogManager.getLogger(ActionFactory.class);
    private static ActionFactory instance = null;
    HashMap<String, Action> actions = new HashMap<String, Action>();
    private ActionFactory()
    {
        //filling factory with all actions
        actions.put("displayUserPage", new ActionDisplayPage());
        actions.put("displayAllFriends", new ActionDisplayAllFriends());
        actions.put("removeUserFromFriends", new ActionRemoveUserFromFriends());
        actions.put("logOut", new LogOut());
        actions.put("searchUsers", new ActionSearchUsers());
    }
    public Action getAction(HttpServletRequest request)
    {
        logger.debug("ActionFactory ENTRY");
        //getting action string name from request
        String action_req = request.getParameter("action");
        logger.debug("Obtained action name string: " + action_req);
        //obtain object which corresponds to an action name
        Action action = actions.get(action_req);
        if (action == null) {
        //если команды не существует в текущем объекте
            action = new ActionDisplayPage();
        }
        return action;
    }
    //создание единственного объекта по шаблону Singleton
    public static ActionFactory getInstance()
    {
        if (instance == null) {
            instance = new ActionFactory();
        }
        return instance;
    }
}

