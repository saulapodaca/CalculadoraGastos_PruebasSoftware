package negocio;

import dominio.ResumenGastos;
import java.util.Map;

/**
 * Servicio coordinador para la generación de resúmenes.
 * Actúa como intermediario para obtener datos procesados de los gastos.
 * * 
 * @author Saul Isaac Apodaca Baldenegro
 * @author Ari Raul Montoya Navarro
 * @author Jorge Cuevas Gastelum
 * @author Camila Zubía Higuera
 * @author Isaias Coronado
 */
public class ResumenService {

    private GastosService gastosService;
    private CategoriaService categoriaService;
    
    /**
     * Constructor que inyecta las dependencias necesarias.
     * * @param gastosService Servicio de gestión de gastos.
     * @param categoriaService Servicio de gestión de categorías.
     */
    public ResumenService(GastosService gastosService, CategoriaService categoriaService) {
        this.gastosService = gastosService;
        this.categoriaService = categoriaService;
    }
    
    /**
     * Imprime en la consola un reporte básico de los gastos.
     * Útil para depuración o interfaces de línea de comandos.
     */
    public void generarResumen() {
        double totalGastos = gastosService.obtenerTotalGastos();
        Map<String, Double> gastosPorCategoria = gastosService.obtenerGastosPorCategoria();

        System.out.println("Resumen de Gastos:");
        System.out.println("Total de Gastos: $" + totalGastos);
        System.out.println("Gastos por Categoría:");
        for (Map.Entry<String, Double> entry : gastosPorCategoria.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
    
    /**
     * Obtiene el objeto de resumen de gastos actual.
     * * @return El objeto ResumenGastos calculado en GastosService.
     */
    public ResumenGastos obtenerResumen() {
        return gastosService.obtenerResumen();
    } 
}