package co.com.funnypets.funnypetsmobile.entities;

public class Usuario {

    private String usuario;
    private String correo;
    private String password;
    private long cntFotos;
    private long cntFollowers;
    private long cntFollowing;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCntFotos() {
        return cntFotos;
    }

    public void setCntFotos(long cntFotos) {
        cntFotos=0;
        this.cntFotos = cntFotos;
    }

    public long getCntFollowers() {
        cntFollowers=0;
        return cntFollowers;
    }

    public void setCntFollowers(long cntFollowers) {
        this.cntFollowers = cntFollowers;
    }

    public long getCntFollowing() {
        cntFollowing=0;
        return cntFollowing;
    }

    public void setCntFollowing(long cntFollowing) {
        this.cntFollowing = cntFollowing;
    }
}
