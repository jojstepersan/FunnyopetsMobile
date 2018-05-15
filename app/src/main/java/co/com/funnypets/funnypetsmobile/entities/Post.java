package co.com.funnypets.funnypetsmobile.entities;

public class Post {

    private String name;
    private Usuario usuario;
    private String  descripcion;
    private int numOfLikes;
    private String Urlphotopost;

    public Post(){}
    public Post(String name, Usuario usuario, String descripcion, int numOfLikes, String UrlPost) {
        this.name = name;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.numOfLikes = numOfLikes;
        this.Urlphotopost=UrlPost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public String getUrlphotopost() {
        return Urlphotopost;
    }

    public void setUrlphotopost(String urlphotopost) {
        Urlphotopost = urlphotopost;
    }
}
