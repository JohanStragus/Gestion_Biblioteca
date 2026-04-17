import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;

public class Biblioteca{
    // ATRIBUTOS
    private List<Llibre> llibres;

    // CONSTRUCTOR
    public Biblioteca() {
        this.llibres = new ArrayList<>();
    }

    // GETTERS
    public List<Llibre> getLlibres() {
        return llibres;
    }


    // METODES

    // 1. Método para añadir un libro a la biblioteca
    public void afegirLlibre(Llibre llibre) {
        // Añadimos el libro a la lista.
        llibres.add(llibre);
    }

    // 2. Método para eliminar un libro de la biblioteca
    public boolean eliminarLlibre(Llibre llibre) {
        // Eliminamos el libro y devolvemos true o false dependiendo de si se ha eliminado o no
        return llibres.remove(llibre);
    }

    /**
     * MÉTODO 3
     * Método para buscar un libro por título.
     * Compara el título ignorando mayúsculas y minúsculas.
     */
    public Llibre buscarLlibrePerTitol(String titol) {
    // Recorremos la lista de libros
    for (int i = 0; i < llibres.size(); i++) {
        // Si el título del libro coincide con el título buscado, lo devolvemos
        if (llibres.get(i).getTitol().equalsIgnoreCase(titol)) {
            // Devolvemos el libro encontrado
            return llibres.get(i);
        }
    }
    // Si no encontramos el libro, devolvemos null
    return null;
}

    // 4. Listar todos los libros de la biblioteca
    public void llistarLlibres() {
        // Recorremos la lista de libros
        for (int i = 0; i < llibres.size(); i++) {
            // Imprimimos el título, autor y categoría de cada libro
            System.out.println("Títol: " + llibres.get(i).getTitol() + ", Autor: " + llibres.get(i).getAutor() + ", Categoria: " + llibres.get(i).getCategoria());
        }
    }
    
    /**
     * MÉTODO 5
     * Método auxiliar para eliminar los acentos de un texto.
     * Convierte caracteres como "á", "é", "í" en "a", "e", "i"
     * para poder comparar textos sin problemas de acentuación.
     */
    private String eliminarAccents(String text) {
        // Normaliza el texto separando las letras de sus acentos
        String normalizado = Normalizer.normalize(text, Normalizer.Form.NFD);

        // Elimina los caracteres de acento
        String sinAcentos = normalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // Convierte todo a minúsculas para facilitar la comparación
        return sinAcentos.toLowerCase();
    }

    /**
     * MÉTODO 6
     * Método para buscar un libro por título ignorando los acentos.
     * Convierte tanto el texto introducido como los títulos de los libros
     * eliminando los acentos antes de compararlos.
     */
    public Llibre buscarLlibreSenseAccents(String titol) {

        // Quitamos los acentos al título que busca el usuario
        String titolBuscat = eliminarAccents(titol);

        // Recorremos todos los libros de la biblioteca
        for (int i = 0; i < llibres.size(); i++) {

            // Quitamos los acentos al título de cada libro
            String titolLlibre = eliminarAccents(llibres.get(i).getTitol());

            // Comparamos los títulos sin acentos
            if (titolLlibre.equals(titolBuscat)) {
                // Si coinciden, devolvemos el libro encontrado
                return llibres.get(i);
            }
        }

        // Si no encontramos ningún libro, devolvemos null
        return null;
    }
}