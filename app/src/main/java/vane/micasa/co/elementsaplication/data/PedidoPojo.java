package vane.micasa.co.elementsaplication.data;

/**
 * Created by Michael Garcia on 10/05/2017.
 */

public class PedidoPojo {
    private String nombre;
    private String perfume;
    private long mililitros;
    private String fechaEntrega;
    private int pagado;
    private long abono;

    public PedidoPojo(String nombre, String perfume, long mililitros, String fechaEntrega) {
        this.nombre = nombre;
        this.perfume = perfume;
        this.mililitros = mililitros;
        this.fechaEntrega = fechaEntrega;
    }

    public PedidoPojo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfume() {
        return perfume;
    }

    public void setPerfume(String perfume) {
        this.perfume = perfume;
    }

    public long getMililitros() {
        return mililitros;
    }

    public void setMililitros(long mililitros) {
        this.mililitros = mililitros;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }


}
