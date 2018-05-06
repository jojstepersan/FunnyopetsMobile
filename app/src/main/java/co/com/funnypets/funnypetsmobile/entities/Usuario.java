package co.com.funnypets.funnypetsmobile.entities;

public class Usuario {

    private String usuario;
    private String correo;
    private long cntFotos;
    private long cntFollowers;
    private long cntFollowing;
    private String Urlfoto;

    public String getUrlfoto() {
        return Urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        Urlfoto = urlfoto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getCntFotos() {
        return cntFotos;
    }

    public void setCntFotos(long cntFotos) {
        this.cntFotos = cntFotos;
    }

    public long getCntFollowers() {
        return cntFollowers;
    }

    public void setCntFollowers(long cntFollowers) {

        this.cntFollowers = cntFollowers;
    }

    public long getCntFollowing() {
        return cntFollowing;
    }

    public void setCntFollowing(long cntFollowing) {


        this.cntFollowing = cntFollowing;
    }
}