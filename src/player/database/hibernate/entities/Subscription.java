package player.database.hibernate.entities;

import java.io.Serializable;

public class Subscription implements Serializable {
    private User subscriber;
    private User user;

    public Subscription() {
    }

    public Subscription(User subscriber, User user) {
        this.setSubscriber(subscriber);
        this.setUser(user);
    }

    public void setSubscriber(User subscriber) {this.subscriber = subscriber;}

    public void setUser(User user) {
        this.user = user;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public User getUser() {
        return user;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Subscription) {
            Subscription subscription = (Subscription) obj;

            if (!subscription.getSubscriber().equals(subscriber)) {
                return false;
            }

            if (!subscription.getUser().equals(user)) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return subscriber.hashCode() + user.hashCode();
    }
}
    /*public Integer getSub_id() {
        return sub_id;
    }*/

    /*public void setSub_id(Integer sub_id) {
        this.sub_id = sub_id;
    }*/
