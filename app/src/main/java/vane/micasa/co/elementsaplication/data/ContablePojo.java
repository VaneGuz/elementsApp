package vane.micasa.co.elementsaplication.data;

/**
 * Created by Michael Garcia on 29/05/2017.
 */

public class ContablePojo {
    private String concepto;
    private String estado;
    private String detalle;
    private double valor;

    public ContablePojo(String concepto, String estado, String detalle, double valor) {
        this.concepto = concepto;
        this.estado = estado;
        this.detalle = detalle;
        this.valor = valor;
    }

    public ContablePojo() {
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
