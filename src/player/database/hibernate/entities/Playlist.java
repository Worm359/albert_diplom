package player.database.hibernate.entities;

import java.util.Set;

public class Playlist
{
    private Integer playlistID;
    private String playlistName;
    private User user;
    private Set<Song> songs;

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPlaylistName() {

        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
