package dominio;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de acumular y procesar los datos para el reporte de gastos.
 * Mantiene el total general y el desglose por categorías.
 * * 
 * @author Saul Isaac Apodaca Baldenegro
 * @author Ari Raul Montoya Navarro
 * @author Jorge Cuevas Gastelum
 * @author Camila Zubía Higuera
 * @author Isaias Coronado
 */
public class ResumenGastos {

    private double totalGastos;
    private Map<String, Double> gastosPorCategoria;

    /**
     * Inicializa un nuevo resumen con totales en cero y un mapa vacío.
     */
    public ResumenGastos() {
        this.totalGastos = 0.0;
        this.gastosPorCategoria = new HashMap<>();
    }

    /**
     * Agrega un gasto al resumen, actualizando el total general y el subtotal
     * de la categoría correspondiente. Normaliza el nombre de la categoría.
     * * @param gasto El objeto Gasto a procesar.
     */
    public void agregarGasto(Gasto gasto) {
        String catNormalizada = normalizarTexto(gasto.getCategoria());
        totalGastos += gasto.getMonto();
        gastosPorCategoria.put(catNormalizada,
                gastosPorCategoria.getOrDefault(catNormalizada, 0.0) + gasto.getMonto());
    }

    /**
     * Normaliza el texto de la categoría para evitar duplicados por mayúsculas/minúsculas.
     * Convierte el texto a formato "Capitalizado" (Primera mayúscula, resto minúsculas).
     * * @param texto El texto de entrada.
     * @return El texto normalizado o "Otros" si la entrada es nula/vacía.
     */
    private String normalizarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "Otros";
        }
        String t = texto.trim().toLowerCase();
        return t.substring(0, 1).toUpperCase() + t.substring(1);
    }

    /**
     * Obtiene el total acumulado de todos los gastos.
     * * @return El monto total.
     */
    public double getTotalGastos() {
        return totalGastos;
    }

    /**
     * Obtiene el mapa con el desglose de gastos por categoría.
     * * @return Un Map donde la clave es el nombre de la categoría y el valor es el subtotal.
     */
    public Map<String, Double> getGastosPorCategoria() {
        return gastosPorCategoria;
    }
}