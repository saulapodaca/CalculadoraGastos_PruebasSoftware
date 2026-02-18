package negocio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import dominio.Categoria;
import dominio.Gasto;
import dominio.ResumenGastos;


public class ControladorNegocio {

    private GastosService gastosService;
    private CategoriaService categoriaService;
    private ResumenService resumenService;
    
    public ControladorNegocio() {
        this.gastosService = new GastosService();
        this.categoriaService = new CategoriaService();
        this.resumenService = new ResumenService(gastosService, categoriaService);
    }
    
    public void agregarGasto(Gasto gasto) {
        gastosService.agregarGasto(gasto);
    }
    
    public void generarResumen() {
        resumenService.generarResumen();
    }
    
    public void agregarCategoria(String categoria) {
        Categoria cat = new Categoria(categoria);
        categoriaService.agregarCategoria(cat);
    }
    
    public boolean verificarCategoria(String categoria) {
        return categoriaService.categoriaExiste(categoria);
    }
    
    public ResumenGastos obtenerResumen() {
        return resumenService.obtenerResumen(); 
    }
    
}
