package negocio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import dominio.ResumenGastos;
import java.util.Map;


public class ResumenService {

    private GastosService gastosService;
    private CategoriaService categoriaService;
    
    public ResumenService(GastosService gastosService, CategoriaService categoriaService) {
        this.gastosService = gastosService;
        this.categoriaService = categoriaService;
    }
    
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
    
    public ResumenGastos obtenerResumen() {
        return gastosService.obtenerResumen();
    }
    
}
