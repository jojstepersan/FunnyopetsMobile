package co.com.funnypets.funnypetsmobile.entities;

public class Post {

    private String name;
    private Usuario usuario;
    private String  descripcion;
    private int numOfLikes;
    private int photo;

    public Post(String name, Usuario usuario, String descripcion, int numOfLikes, int photo) {
        this.name = name;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.numOfLikes = numOfLikes;
        this.photo = photo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
