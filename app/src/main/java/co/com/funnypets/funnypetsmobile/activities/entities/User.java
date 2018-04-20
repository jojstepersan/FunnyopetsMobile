package co.com.funnypets.funnypetsmobile.activities.entities;

import java.util.List;

import co.com.funnypets.funnypetsmobile.activities.entities.Post;

public class User {
    String Username;
    String email;
    int photos;
    int Followers;
    int Following;
    List<Post> post;

    public User(String username, String email, int photos, int followers, int following) {
        Username = username;
        this.email = email;
        this.photos = photos;
        Followers = followers;
        Following = following;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhotos() {
        return photos;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }

    public int getFollowers() {
        return Followers;
    }

    public void setFollowers(int followers) {
        Followers = followers;
    }

    public int getFollowing() {
        return Following;
    }

    public void setFollowing(int following) {
        Following = following;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
