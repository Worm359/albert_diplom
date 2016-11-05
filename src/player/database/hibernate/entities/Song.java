package player.database.hibernate.entities;

import java.io.Serializable;
import java.util.Set;

public class Song implements Serializable
{
    private Integer songID;
    private String groupName;
    private String songName;
    private Set<User> listeners;

    public Song() {}
    public Song(String groupName, String songName)
    {
        this.groupName = groupName;
        this.songName = songName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Set<User> getListeners() {
        return listeners;
    }

    public void setListeners(Set<User> listeners) {
        this.listeners = listeners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return songID == song.songID;

    }

    @Override
    public int hashCode()
    {
        return songID;
    }
}
