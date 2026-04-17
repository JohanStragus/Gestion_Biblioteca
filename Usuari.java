import java.util.ArrayList;
import java.util.List;

public class Usuari {
    // ATRIBUTOS
    private String nom;
    private List<Llibre> llibresPrestats;
    private List<Llibre> historialPrestecs;

    // CONSTRUCTOR
    public Usuari(String nom) {
        this.nom = nom;
        this.llibresPrestats = new ArrayList<>();
        this.historialPrestecs = new ArrayList<>();
    }

    // GETTERS
    public String getNom() {
        return nom;
    }

    public List<Llibre> getLlibresPrestats() {
        return llibresPrestats;
    }

    public List<Llibre> getHistorialPrestecs() {
        return historialPrestecs;
    }

    // SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }

    // METODES

    // 1. Añadir un libro a la lista de libros prestados y al historial de préstamos
    public void afegirLlibre(Llibre llibre) {
        // Añadimos el libro a la lista de libros prestados
        llibresPrestats.add(llibre);

        // Añadimos el libro al historial de préstamos
        historialPrestecs.add(llibre);
    }

    // 2. Método para devolver un libro (EL HISTORIAL DE PRÉSTAMOS NO SE MODIFICA)
    public void retornarLlibre(Llibre llibre) {
        // Si el libro está en la lista de libros prestados
        if(llibresPrestats.contains(llibre)){
            // Lo eliminamos de la lista de libros prestados
            llibresPrestats.remove(llibre);
        }
    }

    // 3. Método para comprobar si el usuario tiene un libro prestado
    public boolean teLlibrePrestat(Llibre llibre) {
        return llibresPrestats.contains(llibre);
    }

    // 4. toString para mostrar la información del usuario
    @Override
    public String toString() {
        return "Nom: " + nom +
            ", llibres prestats: " + llibresPrestats.size() +
            ", historial de préstecs: " + historialPrestecs.size();
    }
}