package player.database.hibernate.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public final class SessionFactoryRegisterer
{

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final Logger logger = LogManager.getLogger(SessionFactoryRegisterer.class);

    public static SessionFactory getJndiSessionFactory()
    {
            return sessionFactory;
    }

    /**
     * Using SessionWrapper for reassigning session from servlet.
     * @param session
     * @return
     */
    public static boolean openSessionCheckNotNull(SessionWrapper session)
    {
        session.setSession(getJndiSessionFactory().openSession());
        if(session.getSession()==null)
        {
            logger.error("CANNOT OPEN SESSION");
            return false;
        }
        return true;
    }
}

