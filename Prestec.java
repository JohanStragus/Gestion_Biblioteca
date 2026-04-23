import java.time.LocalDate;

// en esta clase solo he implementado el toString
// de la classe, casi toda la logica la voy a usar en 
// la clase GestorBiblioteca

// clase que guarda la info de un prestec
public class Prestec {

    private Usuari usuari;
    private Llibre llibre;
    private LocalDate dataPrestec;
    private LocalDate dataRetorn;

    // constructor
    public Prestec(Usuari usuari, Llibre llibre, LocalDate dataPrestec) {
        this.usuari = usuari;
        this.llibre = llibre;
        this.dataPrestec = dataPrestec;

        // el llibre es retorna automaticament en 2 semanas
        this.dataRetorn = dataPrestec.plusWeeks(2);
    }

    // getters
    public Usuari getUsuari() {
        return usuari;
    }

    public Llibre getLlibre() {
        return llibre;
    }

    public LocalDate getDataPrestec() {
        return dataPrestec;
    }

    public LocalDate getDataRetorn() {
        return dataRetorn;
    }

    @Override
    public String toString() {
        return "Prestec de " + usuari.getNom() + " -> " + llibre.getTitol() +
                " | Data prestec: " + dataPrestec +
                " | Data retorn: " + dataRetorn;
    }
}
