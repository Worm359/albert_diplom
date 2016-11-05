package player.database.hibernate.utilities;

import org.hibernate.Session;

public class SessionWrapper
{
    private Session session;
    public SessionWrapper()
    {
        session = null;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    public void closeSession() {this.session.close();}
}
