package dominio;

import java.time.LocalDate;

/**
 * Representa un gasto individual realizado por el usuario. Contiene información
 * sobre el monto, la categoría y la fecha.
 *
 * * 
 * @author Saul Isaac Apodaca Baldenegro
 * @author Ari Raul Montoya Navarro
 * @author Jorge Cuevas Gastelum
 * @author Camila Zubía Higuera
 * @author Isaias Coronado
 *
 */
public class Gasto {

    private double monto;
    private String categoria;
    private LocalDate fecha;

    /**
     * Constructor por defecto.
     */
    public Gasto() {
    }

    /**
     * Constructor con todos los parámetros necesarios para registrar un gasto.
     *
     * * @param monto Cantidad de dinero gastada.
     * @param categoria Nombre de la categoría a la que pertenece el gasto.
     * @param fecha Fecha en la que se realizó el gasto.
     */
    public Gasto(double monto, String categoria, LocalDate fecha) {
        this.monto = monto;
        this.categoria = categoria;
        this.fecha = fecha;
    }

    /**
     * @return El monto del gasto.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto El monto a asignar.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * @return La categoría del gasto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria La categoría a asignar.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return La fecha del gasto.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha La fecha a asignar.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
