public class Llibre{
    // ATRIBUTOS
    private String titol; 
    private String autor;
    private boolean prestat;

    // Atributos adicionales, como pide el ejercicio
    private String categoria;
    private int vegadesPrestat;

    // CONSTRUCTOR
    public Llibre(String titol, String autor, String categoria){
        this.titol = titol;
        this.autor = autor;
        this.categoria = categoria;
        this.prestat = false; // Por defecto, el libro no está prestado
        this.vegadesPrestat = 0; // Por defecto, el libro no ha sido prestado ninguna vez
    }

    // GETTERS
    public String getTitol() {
        return titol;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isPrestat() {
        return prestat;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getVegadesPrestat() {
        return vegadesPrestat;
    }

    // SETTERS
    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // METODES

    // 1. Método para prestar el libro
    public boolean prestar(){
        // Si el libro no está prestado
        if(!prestat){
            // pasamos prestado a true
            prestat = true;

            // Incrementamos el contador de veces prestado
            vegadesPrestat++;

            // Devolvemos true para indicar que ha sido prestado correctamente
            return true;

        }
        // Si el libro ya está prestado, devolvemos false
        return false;
    }

    // 2. Método para devolver el libro
    public boolean retornar(){
        // Si el libro está prestado
        if(prestat){
            // pasamos prestado a false
            prestat = false;

            // Devolvemos true para indicar que ha sido devuelto correctamente
            return true;
        }
        // Si el libro no estaba prestado, devolvemos false
        return false;
    }

    // 3. Método para verificar si el libro está disponible
    public boolean estaDisponible(){
        return !prestat; // Está disponible si no está prestado
    }

    // 4. Método toString para mostrar la información del libro
    @Override
    public String toString() {
    return "Títol: " + titol +
            ", Autor: " + autor +
            ", Categoria: " + categoria +
            ", Disponible: " + (prestat ? "No" : "Sí") +
            ", Vegades prestat: " + vegadesPrestat;
}
}