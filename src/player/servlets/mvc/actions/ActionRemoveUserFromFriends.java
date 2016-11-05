package player.servlets.mvc.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import player.database.hibernate.entities.Subscription;
import player.database.hibernate.entities.User;
import player.database.hibernate.utilities.SessionWrapper;
import player.servlets.utilities.ServletUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionRemoveUserFromFriends implements player.servlets.mvc.Action
{
    private static final Logger logger = LogManager.getLogger(ActionRemoveUserFromFriends.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        logger.debug("ActionRemoveUserFromFriends ENTRY");
        User user;
        User otherUser;
        Query query;
        String userID = ServletUtilities.getAuthorizedUserID(request);
        String otherID = (String) request.getParameter("sub");
        Session hibernate_session;

        logger.debug("Going to delete Subscription:"+" UserID:"+userID+" otherID:"+otherID);
        SessionWrapper sessionWrapper = new SessionWrapper();
        if(!ServletUtilities.openSessionIfNullRedirect(request, response, sessionWrapper))
        {
            //hibernate_session = sessionWrapper.getSession();
            logger.debug("opened session");
            /*sessionWrapper.getSession().beginTransaction();*/
            /*hibernate_session.beginTransaction();*/
            sessionWrapper.getSession().beginTransaction();
            user = sessionWrapper.getSession().get(User.class, userID);
            otherUser = sessionWrapper.getSession().get(User.class, otherID);
            Subscription subscription;// = new Subscription(user, otherUser); and then session.delete(subscription works too

            logger.debug("ID values of persistent objects: sub:"+user.getUserID()+" usr:"+otherUser.getUserID());

            query = sessionWrapper.getSession().createQuery("from Subscription s where (s.subscriber= :subscriber ) and (s.user= :user )");
            query.setParameter("subscriber", user);
            query.setParameter("user", otherUser);
            if(query.list().size()!=0)
            {
                subscription = (Subscription) query.list().get(0);
                sessionWrapper.getSession().delete(subscription);
            }

            /*
            SQLQuery querysql;
            logger.debug("delete from subscribers where SUBSCRIBER_ID=\""+userID+"\" and USER_ID=\""+otherID+"\"");*/




            /*querysql = sessionWrapper.getSession().createSQLQuery("delete from subscribers where SUBSCRIBER_ID=\""+userID+"\n and USER_ID=\""+otherID+"\n");*/
            /*querysql = hibernate_session.createSQLQuery("delete from subscribers where SUBSCRIBER_ID=\""+userID+"\n and USER_ID=\""+otherID+"\n");*/
            /*querysql = hibernate_session.createSQLQuery("delete from users where USER_ID=\""+userID+"\n");*/

            /*query = sessionWrapper.getSession().createQuery("from Subscription s where (s.subscriber= :subscriber ) and (s.user= :user )");
            query.setParameter("subscriber", user);
            query.setParameter("user", otherUser);
            subscription = (Subscription) query.list().get(0);
            logger.debug("subscription value after query in subscriber:" + subscription.getSubscriber().getUserID());
            sessionWrapper.getSession().saveOrUpdate(subscription);
            sessionWrapper.getSession().delete(subscription);*/


            /*query = sessionWrapper.getSession().createQuery("from Subscription s where (s.subscriber= :subscriber) and (s.user= :user)");
            query.setProperties(subscription);*/
            /*query.setParameter("sub", userID);
            query.setParameter("usr", otherID);*/
            /*if(query.list().get(0)!=null)
            {
                subscription = (Subscription) query.list().get(0);
                sessionWrapper.getSession().delete(subscription);
            }*/

/*
            new Subscription(user, otherUser);
*/


            /*
            Stock stock = new Stock();
            stock.setStockCode("7277");
            String hql = "from Stock s where s.stockCode = :stockCode";
            List result = session.createQuery(hql)
                    .setProperties(stock)
                    .list();*/
            /*query .setParameter("usID", login);
            List users = query_s.list();*/
            /*subcription = (User) users.get(0);*/


            sessionWrapper.getSession().getTransaction().commit();
            /*sessionWrapper.getSession().getTransaction().commit();*/
            sessionWrapper.closeSession();
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return null;
        }
        else
        {
            return null;
        }
    }
}
