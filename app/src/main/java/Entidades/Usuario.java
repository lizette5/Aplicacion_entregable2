package Entidades;

public class Usuario {

    private String nombre;
    private String contraseña;

    public Usuario( String nombre, String con){

        this.nombre=nombre;
        this.contraseña=con;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}

