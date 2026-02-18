package negocio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import dominio.Gasto;
import dominio.ResumenGastos;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GastosService {

    private List<Gasto> listaGastos;
    private ResumenGastos resumen;

    public GastosService() {
        this.listaGastos = new ArrayList<>();
        this.resumen = new ResumenGastos();
    }

    public void agregarGasto(Gasto gasto) {
        listaGastos.add(gasto);
        resumen.agregarGasto(gasto);
    }

    public double obtenerTotalGastos() {
        return resumen.getTotalGastos();
    }
    
    public Map<String, Double> obtenerGastosPorCategoria() {
        return resumen.getGastosPorCategoria();
    }
    
    public ResumenGastos obtenerResumen() {
        return resumen;
    }
    
}
