package negocio;

import dominio.Gasto;
import dominio.ResumenGastos;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Servicio encargado de gestionar el almacenamiento y recuperación de gastos.
 * También mantiene una instancia de ResumenGastos actualizada.
 * * 
 * @author Saul Isaac Apodaca Baldenegro
 * @author Ari Raul Montoya Navarro
 * @author Jorge Cuevas Gastelum
 * @author Camila Zubía Higuera
 * @author Isaias Coronado
 */
public class GastosService {

    private List<Gasto> listaGastos;
    private ResumenGastos resumen;

    /**
     * Inicializa el servicio creando una lista de gastos vacía y un resumen nuevo.
     */
    public GastosService() {
        this.listaGastos = new ArrayList<>();
        this.resumen = new ResumenGastos();
    }

    /**
     * Agrega un nuevo gasto a la lista y actualiza el resumen acumulado.
     * * @param gasto El objeto Gasto a registrar.
     */
    public void agregarGasto(Gasto gasto) {
        listaGastos.add(gasto);
        resumen.agregarGasto(gasto);
    }

    /**
     * Obtiene el total global de gastos registrados hasta el momento.
     * * @return El monto total de gastos.
     */
    public double obtenerTotalGastos() {
        return resumen.getTotalGastos();
    }
    
    /**
     * Obtiene el desglose de gastos agrupados por categoría.
     * * @return Mapa con categorías y sus respectivos subtotales.
     */
    public Map<String, Double> obtenerGastosPorCategoria() {
        return resumen.getGastosPorCategoria();
    }
    
    /**
     * Obtiene el objeto ResumenGastos completo.
     * * @return Instancia actual de ResumenGastos.
     */
    public ResumenGastos obtenerResumen() {
        return resumen;
    }
}