package player.database.hibernate.dao;

import org.hibernate.Session;
import player.database.hibernate.dao.generic.GenericHibernateDAO;
import player.database.hibernate.dao.generic.UserDAO;
import player.database.hibernate.entities.User;

public class UserDAOHibernate extends GenericHibernateDAO<User, String> implements UserDAO
{
    public UserDAOHibernate(Session session) {
        super(User.class, session);
    }
}
