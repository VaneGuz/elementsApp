package vane.micasa.co.elementsaplication.data;

/**
 * Created by Michael Garcia on 10/05/2017.
 */

public class PerfumePojo {
    private String nombre;
    private String genero;
    private String casa;
    private String fechaPreparacion;
    private String fechaDisponible;
    private long mililitrosTotal;
    private String key;

    public PerfumePojo(String nombre, String genero, String casa, String fechaPreparacion, String fechaDisponible, long mililitrosTotal, String key) {
        this.nombre = nombre;
        this.genero = genero;
        this.casa = casa;
        this.fechaPreparacion = fechaPreparacion;
        this.fechaDisponible = fechaDisponible;
        this.mililitrosTotal = mililitrosTotal;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(String fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public String getFechaDisponible() {
        return fechaDisponible;
    }

    public void setFechaDisponible(String fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
    }

    public long getMililitrosTotal() {
        return mililitrosTotal;
    }

    public void setMililitrosTotal(long mililitrosTotal) {
        this.mililitrosTotal = mililitrosTotal;
    }

    public PerfumePojo() {
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
