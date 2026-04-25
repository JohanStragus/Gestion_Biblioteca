// este main se ha creado y ha sido probado
// en eclipse, (tambien posible en vscode, opcion2)
// 1. si lo quereis probar en eclipse:
// si prueba en unico fitxero, quitar public en cada clase:
// public class Usuari -> class Usuari
// añadir esto arriba del todo abajo del package
// y copiar las classes sin sus imports
// package test;

// import java.util.Scanner;

// import java.util.ArrayList;
// import java.util.List;
// import java.text.Normalizer;

// import java.time.LocalDate;

// discomentar los imports que tengo aqui (list y Scanner)
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

// 2. si lo quereis probar en vscode:
// https://learn.microsoft.com/en-us/java/openjdk/download#openjdk-17
// descargar: Window x64 tipo msi (la 6ta opcion)

public class Main {

	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		/* para no enrollarme mucho con el main:
		 * como tengo infinitas ideas de como hacer el proyecto
		 * interactivo solamente me he profundidado en el case 1 y sobre todo el 5
		 * y aun asi me faltaba profundir mas con el 5 asi me siento, asi que
		 * por tiempo mejor dejarlo asi y no tocar mas sino nunca acabaria este proyecto
		 * cada vez estare anadiendo bucles y nuevas ideas.
		 * */

		// creamos la biblioteca y el gestor que manejara los prestamos
		// biblioteca guarda los libros, gestor controla prestamos y devoluciones
		Biblioteca biblioteca = new Biblioteca();
		GestorBiblioteca gestor = new GestorBiblioteca();

		// anadimos algunos libros de prueba a la biblioteca
		// esto es solo para que el programa tenga libros desde el inicio
		biblioteca.afegirLlibre(new Llibre("1984", "George Orwell", "Novel·la"));
		biblioteca.afegirLlibre(new Llibre("El petit princep", "Antoine de Saint-Exupery", "Infantil"));
		biblioteca.afegirLlibre(new Llibre("Cien anos de soledad", "Gabriel Garcia Marquez", "Novel·la"));

		// creamos dos usuarios de prueba
		// cada usuario tiene su lista de libros prestados y su historial
		Usuari usuari = new Usuari("Adil");
		Usuari usuari2 = new Usuari("Joan");

		// preguntamos con que usuario queremos interactuar
		// esto permite que cada usuario tenga su propio historial y prestamos
		System.out.println("Quin usuari vol entrar?");
		System.out.println("1. " + usuari.getNom());
		System.out.println("2. " + usuari2.getNom());
		System.out.print("Opcio: ");

		int usuariSeleccionado = input.nextInt();
		input.nextLine();

		// guardamos el usuario elegido en una variable
		// asi todo el programa trabaja con este usuario
		Usuari usuariEscollit;

		if (usuariSeleccionado == 1) {
			usuariEscollit = usuari;
		}
		else {
			usuariEscollit = usuari2;
		}

		// mensaje de bienvenida
		System.out.println("*********************************");
		System.out.println("\tBenvingut " + usuariEscollit.getNom());
		System.out.println("*********************************");


		int opcio = -1;

		// bucle principal del menu
		// mientras opcio no sea 0, el menu se repite
		while (opcio != 0) {

			// mostramos el menu principal
			System.out.println("\n--- MENU BIBLIOTECA ---");
			System.out.println("1. Llistar llibres");
			System.out.println("2. Buscar llibre per titol");
			System.out.println("3. Buscar llibre sense accents");
			System.out.println("4. Prestar llibre");
			System.out.println("5. Retornar llibre");
			System.out.println("6. Veure historial de l'usuari");
			System.out.println("7. Veure prestecs actius");
			System.out.println("8. Veure estadistiques");
			System.out.println("0. Sortir");
			System.out.print("Opcio: ");

			opcio = input.nextInt();
			input.nextLine(); // limpiar el buffer

			switch (opcio) {

			// ---------------- CASE 1: LISTAR LIBROS ----------------
			case 1:
				// al principio este case 1 era principalmente para listar libros
				// solamente y el final he decidido hacer un submenu para listar
				// libros y permitir prestar desde aqui
				int opcioLlista = -1;

				while (opcioLlista != 2) {

					// mostramos todos los libros de la biblioteca
					biblioteca.llistarLlibres();

					// opciones despues de listar
					System.out.println("\n--- Opcions ---");
					System.out.println("1. Prestar un llibre");
					System.out.println("2. Tornar al menu principal");
					System.out.println("3. Tornar a llistar llibres");
					System.out.print("Opcio: ");

					opcioLlista = input.nextInt();
					input.nextLine();

					if (opcioLlista == 1) {
						// pedir titulo del libro a prestar
						System.out.print("Escriu el titol del llibre que vols agafar: ");
						String titolPrestar = input.nextLine();

						// buscamos el libro por titulo
						Llibre llibreTrobat = biblioteca.buscarLlibrePerTitol(titolPrestar);

						// si existe, lo prestamos
						if (llibreTrobat != null) {
							gestor.prestarLlibre(usuariEscollit, llibreTrobat);
							opcioLlista = 2; // volveremos al menu principal
						} else {
							System.out.println("Aquest llibre no existeix… potser lhas escrit malament.");
						}

					} else if (opcioLlista == 3) {
						// no hacemos nada aqui, si repite el
						// bucle (repetir listado)
					} else if (opcioLlista != 2) {
						System.out.println("Opcio incorrecta.");
					}
				}
				break;


				// ---------------- CASE 2: BUSCAR POR TITULO ----------------
			case 2:
				// pedir titulo
				System.out.print("Introdueix el titol: ");
				String t1 = input.nextLine();

				// buscar libro
				Llibre l1 = biblioteca.buscarLlibrePerTitol(t1);

				// mostrar resultado
				if (l1 != null) System.out.println(l1);
				else System.out.println("No s'ha trobat cap llibre.");
				break;

				// ---------------- CASE 3: BUSCAR SIN ACENTOS ----------------
			case 3:
				System.out.print("Introdueix el titol (sense accents): ");
				String t2 = input.nextLine();

				// buscar libro ignorando acentos
				Llibre l2 = biblioteca.buscarLlibreSenseAccents(t2);

				if (l2 != null) System.out.println(l2);
				else System.out.println("No s'ha trobat cap llibre.");
				break;

				// ---------------- CASE 4: PRESTAR LIBRO DIRECTO ----------------
			case 4:
				System.out.print("Titol del llibre a prestar: ");
				String t3 = input.nextLine();

				// buscar libro
				Llibre l3 = biblioteca.buscarLlibrePerTitol(t3);

				// si existe, prestarlo
				if (l3 != null) gestor.prestarLlibre(usuariEscollit, l3);
				else System.out.println("Aquest llibre no existeix.");
				break;

				// ---------------- CASE 5: DEVOLVER O PRESTAR SI NO TIENE ----------------
			case 5:

				// este case la tenia al principio pensado solamente
				// para devolver libros, asi de simple, y al final he decidido
				// si el usuari no tiene libros para prestar entonces le vamos 
				// a pregunatr si quiere prestar

				// comprobar si el usuario tiene libros prestados
				boolean tieneLibros = !usuariEscollit.getLlibresPrestats().isEmpty();

				if (!tieneLibros) {

					// si no tiene libros → ofrecer alquilar
					System.out.println("No tens cap llibre per retornar ara mateix.");
					System.out.println("Vols agafar un llibre?");
					System.out.println("1. Si, vull agafar un llibre");
					System.out.println("2. Tornar al menu principal");

					System.out.print("Opcio: ");
					int opcionAlquilar = input.nextInt();
					input.nextLine();

					if (opcionAlquilar == 1) {

						int opcionSubmenu = -1;

						while (opcionSubmenu != 2) {

							// submenu para elegir como alquilar
							System.out.println("\n--- Com vols agafar un llibre? ---");
							System.out.println("1. Escollir de la llista");
							System.out.println("2. Tornar al menu principal");
							System.out.print("Opcio: ");

							opcionSubmenu = input.nextInt();
							input.nextLine();

							// OPCION 1: elegir de la lista
							if (opcionSubmenu == 1) {

								// aqui obtenemos la lista de libros de la biblioteca
								// getLlibres() devuelve un List<Llibre>, pero yo quiero usarlo como ArrayList
								// porque necesito acceder por indice (libros.get(numero - 1))
								// por eso hago un casteo (conversion) a ArrayList
								ArrayList<Llibre> libros = (ArrayList<Llibre>) biblioteca.getLlibres();

								// mostramos libros numerados para que el usuario pueda elegir por numero
								System.out.println("\n--- Llibres disponibles ---");
								for (int i = 0; i < libros.size(); i++) {
									System.out.println((i + 1) + ". " + libros.get(i).getTitol());
								}

								// opcion para volver atras
								System.out.println((libros.size() + 1) + ". Tornar enrere");

								// pedimos numero del libro
								System.out.print("Escull un llibre: ");
								int numero = input.nextInt();
								input.nextLine();

								if (numero >= 1 && numero <= libros.size()) {

									// obtenemos el libro elegido usando el indice
									Llibre libroElegido = libros.get(numero - 1);

									// prestamos el libro
									gestor.prestarLlibre(usuariEscollit, libroElegido);

									// mostramos fecha estimada de retorno
									System.out.println("Data estimada de retorn: " + LocalDate.now().plusWeeks(2));

									opcionSubmenu = 2; // volver al menu principal

								} else if (numero == libros.size() + 1) {
									// volver atras
								} else {
									System.out.println("Opcio incorrecta.");
								}
							}

							else if (opcionSubmenu != 2) {
								System.out.println("Opcio incorrecta.");
							}
						}
					}

					// flujo termina aqui
				}

				else {

					// si tiene libros → devolver
					int opcionDevolver = -1;

					while (opcionDevolver != 2) {

						System.out.print("Titol del llibre a retornar: ");
						String tituloDevolver = input.nextLine();

						// buscamos el libro que quiere devolver
						Llibre libroDevolver = biblioteca.buscarLlibrePerTitol(tituloDevolver);

						if (libroDevolver != null) {

							// devolvemos el libro
							gestor.retornarLlibre(usuariEscollit, libroDevolver);
							opcionDevolver = 2;

						} else {

							// opciones si el titulo no existe
							System.out.println("Aquest llibre no existeix.");
							System.out.println("1. Veure llista de llibres");
							System.out.println("2. Tornar al menu principal");
							System.out.println("3. Tornar a escriure el titol");
							System.out.print("Opcio: ");

							opcionDevolver = input.nextInt();
							input.nextLine();

							if (opcionDevolver == 1) {
								biblioteca.llistarLlibres();
							} else if (opcionDevolver == 3) {
								// repetir
							} else if (opcionDevolver != 2) {
								System.out.println("Opcio incorrecta.");
							}
						}
					}
				}

				break;


				// ---------------- CASE 6: HISTORIAL ----------------
			case 6:
				// mostramos historial del usuario
				System.out.println("Historial de " + usuari.getNom() + ":");

				if (usuari.getHistorialPrestecs().isEmpty()) {
					System.out.println("No tens cap llibre al historial.");
				} else {
					for (Llibre ll : usuari.getHistorialPrestecs()) {
						System.out.println(ll);
					}
				}
				break;

				// ---------------- CASE 7: PRESTAMOS ACTIVOS ----------------
			case 7:
				// mostramos prestamos activos del gestor
				gestor.mostrarPrestecs();
				break;

				// ---------------- CASE 8: ESTADISTICAS ----------------
			case 8:
				// mostramos estadisticas de la biblioteca
				gestor.mostrarEstadistiques(biblioteca);
				break;

				// ---------------- CASE 0: SALIR ----------------
			case 0:
				System.out.println("Adios...");
				break;

			default:
				System.out.println("Opcio incorrecta.");
			}
		}

		input.close();
	}

}