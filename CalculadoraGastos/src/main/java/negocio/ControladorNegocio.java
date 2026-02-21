package negocio;

import dominio.Categoria;
import dominio.Gasto;
import dominio.ResumenGastos;

/**
 * Controlador principal (Patrón Facade) que centraliza las operaciones de negocio.
 * La interfaz gráfica (Presentación) se comunica únicamente con esta clase
 * para realizar operaciones.
 * * 
 * @author Saul Isaac Apodaca Baldenegro
 * @author Ari Raul Montoya Navarro
 * @author Jorge Cuevas Gastelum
 * @author Camila Zubía Higuera
 * @author Isaias Coronado
 */
public class ControladorNegocio {

    private GastosService gastosService;
    private CategoriaService categoriaService;
    private ResumenService resumenService;

    /**
     * Inicializa el controlador instanciando todos los servicios necesarios
     * y estableciendo sus dependencias.
     */
    public ControladorNegocio() {
        this.gastosService = new GastosService();
        this.categoriaService = new CategoriaService();
        this.resumenService = new ResumenService(gastosService, categoriaService);
    }

    /**
     * Registra un nuevo gasto en el sistema.
     * * @param gasto El objeto Gasto a agregar.
     */
    public void agregarGasto(Gasto gasto) {
        gastosService.agregarGasto(gasto);
    }

    /**
     * Ejecuta la lógica para generar (imprimir) el resumen por consola.
     */
    public void generarResumen() {
        resumenService.generarResumen();
    }

    /**
     * Crea y registra una nueva categoría a partir de un nombre.
     * * @param categoria Nombre de la nueva categoría.
     */
    public void agregarCategoria(String categoria) {
        Categoria cat = new Categoria(categoria);
        categoriaService.agregarCategoria(cat);
    }

    /**
     * Verifica si una categoría ya existe en el sistema.
     * * @param categoria Nombre de la categoría a buscar.
     * @return true si existe, false en caso contrario.
     */
    public boolean verificarCategoria(String categoria) {
        return categoriaService.categoriaExiste(categoria);
    }

    /**
     * Obtiene los datos estructurados del resumen de gastos.
     * * @return Objeto ResumenGastos con totales y desglose.
     */
    public ResumenGastos obtenerResumen() {
        return resumenService.obtenerResumen();
    }

    /**
     * Obtiene una colección iterable con los nombres de todas las categorías.
     * * @return Iterable de Strings con los nombres.
     */
    public Iterable<String> obtenerNombresCategorias() {
        return categoriaService.obtenerNombresCategorias();
    }
}