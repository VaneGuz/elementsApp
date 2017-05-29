package vane.micasa.co.elementsaplication.data;

/**
 * Created by Michael Garcia on 10/05/2017.
 */

public class PedidoPojo {
    private String nombre;
    private String perfume;
    private Long mililitros;
    private String fechaEntrega;


    public PedidoPojo(String nombre, String perfume, Long mililitros, String fechaEntrega) {
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

    public Long getMililitros() {
        return mililitros;
    }

    public void setMililitros(Long mililitros) {
        this.mililitros = mililitros;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }


}
