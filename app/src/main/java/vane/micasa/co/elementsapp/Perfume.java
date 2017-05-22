package vane.micasa.co.elementsapp;

/**
 * Created by Michael Garcia on 10/05/2017.
 */

public class Perfume {
    private String nombre;
    private String genero;

    public Perfume(String nombre, String genero) {
        this.nombre = nombre;

        this.genero = genero;
    }

    public Perfume() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
