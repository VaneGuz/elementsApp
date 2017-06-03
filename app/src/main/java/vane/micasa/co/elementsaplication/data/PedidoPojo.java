package vane.micasa.co.elementsaplication.data;

import java.util.Date;

/**
 * Created by Michael Garcia on 10/05/2017.
 */

public class PedidoPojo {
    private String nombre;
    private String perfume;
    private long mililitros;
    private String fechaEntrega;
    private int estado;
    private long valor;
 private String key;

    public PedidoPojo(String nombre, String perfume, long mililitros, String fechaEntrega, int estado, long valor, String key) {
        this.nombre = nombre;
        this.perfume = perfume;
        this.mililitros = mililitros;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.valor = valor;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
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
