package dominio;

/**
 * Representa una categoría a la que puede pertenecer un gasto.
 * Permite agrupar los gastos bajo un nombre común.
 * * 
 * @author Saul Isaac Apodaca Baldenegro
 * @author Ari Raul Montoya Navarro
 * @author Jorge Cuevas Gastelum
 * @author Camila Zubía Higuera
 * @author Isaias Coronado
 */
public class Categoria {
    
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public Categoria() {
    }

    /**
     * Constructor que inicializa la categoría con un nombre específico.
     * * @param nombre El nombre de la categoría (ej. "Alimentos", "Transporte").
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de la categoría.
     * * @return El nombre de la categoría.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     * * @param nombre El nuevo nombre para la categoría.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}