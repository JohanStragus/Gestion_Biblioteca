import java.time.LocalDate;
import java.util.ArrayList;

public class GestorBiblioteca{
    private ArrayList<Prestec> prestecs;
    
    public GestorBiblioteca() {
        this.prestecs = new ArrayList<>();
    }

    // metode para prestar un libro a un usuario
    public boolean prestarLlibre(Usuari usuari, Llibre llibre){

        // comprobamos si el libro esta prestado o disponible
        if (!llibre.estaDisponible()) {
            System.out.println("El llibre ja esta prestat, no es pot agafar.");
            return false;
        }

        // comprobamos si el usuario ya tiene demasiados libros prestados ya
        if(usuari.getLlibresPrestats().size()>= 5){
            System.out.println("L'usuari " + usuari.getNom() + " ja te el maxim de llibres prestats.");
            return false;
        }

        //hacemos el prestamo y marcar el libro como prestado
        llibre.prestar();

        // creamos el objeto Prestec con la fecha de hoy
        Prestec p = new Prestec(usuari, llibre, LocalDate.now());
        // añadimos el prestamo a la lista
        prestecs.add(p);
        
        // añadimos el libro al usuario
        usuari.afegirLlibre(llibre);

        System.out.println(usuari.getNom() + " ha agafat el llibre: " + llibre.getTitol());
        return true;
    }

    // metode para devolver un libro
    public boolean retornarLlibre(Usuari usuari, Llibre llibre){
        if(!usuari.teLlibrePrestat(llibre)){
            System.out.println("Aquest usuari no te aquest llibre.");
            return false;
        }

        // marcamos el libro como devuelto
        llibre.retornar();
        
        Prestec prestecTrobat = null;

        for (int i = 0; i < prestecs.size(); i++) {
            Prestec p = prestecs.get(i);

            // comprobamos si coincide el usuario y el libro
            if (p.getUsuari() == usuari && p.getLlibre() == llibre) {
                prestecTrobat = p;
                i = prestecs.size(); // esto fuerza salir del bucle sin usar break
            }
        }

        // Eliminamos el prestamo de la lista
        // comprobamos que no sigui null el prestec
        // sino estare haciendo remove a un null y no tendra sentido
        if (prestecTrobat != null) {
            prestecs.remove(prestecTrobat);
        }


        // lo quitamos del usuario
        usuari.retornarLlibre(llibre);
        System.out.println(usuari.getNom() + " ha retornat el llibre: " + llibre.getTitol());
        return true;
       }

    // metodo que muestra todos los prestecs fets
    public void mostrarPrestecs() {
         if (prestecs.isEmpty()) {
            System.out.println("No hi ha cap prestec actiu.");
            return;
        }
        for (Prestec p : prestecs) {
            System.out.println(p);
        }
    }
      // Metodo para comprovar si un libro esta disponible
    public boolean comprovarDisponibilitat(Llibre llibre) {
        return llibre.estaDisponible();
    }
 
    // Metodo para mostrar estadisticas, cuantas veces ha sido prestado cada libro
    public void mostrarEstadistiques(Biblioteca biblioteca) {
        System.out.println("--- Estadistiques ---");
        System.out.println("Total prestecs actius: " + prestecs.size());
 
        // Mostramos las veces que ha sido prestado cada libro
        ArrayList<Llibre> llibres = biblioteca.getLlibres();
        for (int i = 0; i < llibres.size(); i++) {
            System.out.println(llibres.get(i).getTitol() + " -> " + llibres.get(i).getVegadesPrestat() + " vegades prestat");
        }
    }

}