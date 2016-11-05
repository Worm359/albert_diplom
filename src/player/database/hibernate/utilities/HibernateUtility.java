package player.database.hibernate.utilities;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtility
{
    public static final SessionFactory sessionFactory = (new SessionFactoryRegisterer()).getJndiSessionFactory();
    private Session hibernate_session = null;
    Query returnQuery = null;
    SQLQuery returnSQLQuery = null;


    public Query execHQLQuery(String query)
    {
        hibernate_session = sessionFactory.openSession();
        hibernate_session.beginTransaction();
        returnQuery = hibernate_session.createQuery(query);
        hibernate_session.getTransaction().commit();
        return returnQuery;
    }

    public SQLQuery execSQLQuery (String query)
    {
        hibernate_session = sessionFactory.openSession();
        hibernate_session.beginTransaction();
        returnSQLQuery = hibernate_session.createSQLQuery(query);
        hibernate_session.getTransaction().commit();
        return returnSQLQuery;
    }

    public void saveEntity(Object obj)
    {

    }

}
