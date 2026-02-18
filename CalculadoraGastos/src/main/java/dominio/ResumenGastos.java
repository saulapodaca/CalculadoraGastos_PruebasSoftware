package dominio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import java.util.HashMap;
import java.util.Map;


public class ResumenGastos {

    private double totalGastos;
    private Map<String, Double> gastosPorCategoria;

    public ResumenGastos() {
        this.totalGastos = 0.0;
        this.gastosPorCategoria = new HashMap<>();
    }

     public void agregarGasto(Gasto gasto) {
        totalGastos += gasto.getMonto();
        gastosPorCategoria.put(gasto.getCategoria(), 
                               gastosPorCategoria.getOrDefault(gasto.getCategoria(), 0.0) + gasto.getMonto());
    }

    public double getTotalGastos() {
        return totalGastos;
    }

    public Map<String, Double> getGastosPorCategoria() {
        return gastosPorCategoria;
    }
    
}
