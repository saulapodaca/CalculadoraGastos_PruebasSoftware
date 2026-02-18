package negocio;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import dominio.Categoria;
import java.util.ArrayList;
import java.util.List;


public class CategoriaService {

    private List<Categoria> categorias;
    
    public CategoriaService() {
        this.categorias = new ArrayList<>();
    }
    
    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
    
    public List<Categoria> obtenerCategorias() {
        return categorias;
    }
    
    public boolean categoriaExiste(String categoria) {
        return categorias.stream().anyMatch(c -> c.getNombre().equalsIgnoreCase(categoria));
    }
    
}
