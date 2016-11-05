package player.database.hibernate.entities;


import java.io.Serializable;
import java.util.Set;

public class User implements Serializable
{

    private String userID;
    private String userPassword;
    private Set<Subscription> subscriptions;
    private Set<Subscription> subscribers;
    private Set<Playlist> playlist;

    public User()
    {

    }

    public User(String login, String password)
    {
        this.setUserID(login);
        this.setUserPassword(password);
    }

    public void setUserID(String id)
    {
        if((id!=null)&&(!id.equals("")))
            this.userID = id;
        else throw new IllegalArgumentException("User ID setter exception: empty or null");
    }

    public void setUserPassword(String password)
    {
        if((password!=null)&&(!password.equals("")))
            this.userPassword = password;
        else throw new IllegalArgumentException("User password setter exception: empty or null");
    }

    public String getUserID()
    {
        return this.userID;
    }

    public String getUserPassword()
    {
        return this.userPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User user = (User) obj;

            if(!user.getUserID().equals(userID)){
                return false;
            }
            /*if(!user.getUserPassword().equals(userPassword)){
                return false;
            }*/
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return userID.hashCode() + userPassword.hashCode();
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }


    public Set<Subscription> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<Subscription> subscribers) {
        this.subscribers = subscribers;
    }
}