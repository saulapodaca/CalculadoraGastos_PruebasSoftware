package dominio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import java.time.LocalDate;


public class Gasto {

    private double monto;
    private String categoria;
    private LocalDate fecha;
    private String descripcion;

    public Gasto() {
    }

    public Gasto(double monto, String categoria, LocalDate fecha, String descripcion) {
        this.monto = monto;
        this.categoria = categoria;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
