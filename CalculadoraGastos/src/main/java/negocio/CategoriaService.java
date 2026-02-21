package negocio;

import dominio.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la gestión de categorías disponibles en el sistema.
 * Permite agregar nuevas categorías y consultar las existentes.
 * * 
 * @author Saul Isaac Apodaca Baldenegro
 * @author Ari Raul Montoya Navarro
 * @author Jorge Cuevas Gastelum
 * @author Camila Zubía Higuera
 * @author Isaias Coronado
 * 
 */
public class CategoriaService {

    private List<Categoria> categorias;

    /**
     * Constructor que inicializa la lista de categorías.
     */
    public CategoriaService() {
        this.categorias = new ArrayList<>();
    }

    /**
     * Agrega una nueva categoría a la lista si no existe previamente.
     * La verificación es insensible a mayúsculas/minúsculas.
     * * @param categoria El objeto Categoria a agregar.
     */
    public void agregarCategoria(Categoria categoria) {
        // Solo agregar si no existe (ignora mayúsculas/minúsculas)
        if (!categoriaExiste(categoria.getNombre())) {
            categorias.add(categoria);
        }
    }

    /**
     * Retorna la lista completa de objetos Categoria.
     * * @return Lista de categorías.
     */
    public List<Categoria> obtenerCategorias() {
        return categorias;
    }

    /**
     * Verifica si una categoría ya existe en la lista comparando nombres.
     * * @param categoria El nombre de la categoría a verificar.
     * @return true si la categoría existe, false en caso contrario.
     */
    public boolean categoriaExiste(String categoria) {
        if (categoria == null) {
            return false;
        }
        
        return categorias.stream()
                .anyMatch(c -> c.getNombre().equalsIgnoreCase(categoria.trim()));
    }

    /**
     * Obtiene una lista con solo los nombres de las categorías almacenadas.
     * Útil para llenar componentes de interfaz gráfica como ComboBoxes.
     * * @return Lista de Strings con los nombres de las categorías.
     */
    public List<String> obtenerNombresCategorias() {
        return categorias.stream().map(Categoria::getNombre).toList();
    }
}