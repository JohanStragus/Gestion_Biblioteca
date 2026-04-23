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
    }

    // metode para devolver un libro
    public boolean retornarLlibre(Usuari usuari, Llibre llibre){
        if(!usuari.teLlibrePrestat(llibre)){
            System.out.println("Aquest usuari no te aquest llibre.");
            return false;
        }

        // marcamos el libro como devuelto
        llibre.retornar();

        // lo quitamos del usuario
        usuari.retornarLlibre(llibre);
        System.out.println("Llibre retornat correctament.");
        return true;

        // metodo que muestra todos los prestecs fets
        publc void mostrarPrestecs(){
            for(Prestec p : prestecs){
                System.out.println(p);
            }
        }
    }
}