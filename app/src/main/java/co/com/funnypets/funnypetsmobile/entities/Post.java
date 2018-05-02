package co.com.funnypets.funnypetsmobile.entities;

public class Post {

    private String name;
    private int numOfLikes;
    private int photo;

    public Post(String name, int numOfLikes, int photo) {
        this.name = name;
        this.numOfLikes = numOfLikes;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
