import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
	
	//ATRIBUTOS
	private static ArrayList<String> palabras = new ArrayList<String>(); // Contiene todas las palabras que contiene el juego
	private static ArrayList<Character> palabraDividida = new ArrayList<Character>(); // Contiene los caracteres de cada palabra
	private static Scanner sc = new Scanner (System.in); // Recoger textos por teclado
	private static int intentos = 5; // Nº de intentos 
	private static String respuesta1; // Respuestas dadas por el usuario
	private static Character letra1; // Conversion de letras de palabra en char
	private static String letraPropuesta = ""; // declaracion de letrapropuesta del scanner
	private static String seleccionAuto1 = ""; //
	private static ArrayList<Character> palabraOculta = new ArrayList<Character>();
	
	
	// Constructor
	public Ahorcado() 
	{
		asignarPalabras();
		dividirPalabra();
		iniciarJuego();
	}
	
	// Método para mostrar mensajes
	private static void mostrarTexto(String texto1) 
	{
		System.out.println(texto1);
		System.out.println();
	}
	
	// Método que permite recoger textos por teclado
	private static String recogerTexto() 
	{
		return sc.nextLine();
	}
	
	// Método que asigna palabras al juego
	private static void asignarPalabras() 
	{
		palabras.add("RODILLA");
		palabras.add("MUÑECA");
		palabras.add("BRAZO");
		palabras.add("PIERNA");
		palabras.add("CABEZA");
		palabras.add("HOMBRO");
	}
	
	// Método que inicia un menú de juego
	private static void iniciarJuego() 
	{
		mostrarTexto("AHORCADO");
		respuesta1= menu("1.-Comenzar juego\n2.-Terminar juego");
		procesarRespuesta(respuesta1);
	}
	
	// Mostrar menú del juego y solicitar elegir entre jugar o terminar 
	private static String menu(String texto1) 
	{
		do 
		{
			mostrarTexto(texto1);
			respuesta1 = recogerTexto();
		}
		while (!respuesta1.equals("1") && !respuesta1.equals("2") );
			return respuesta1;
	}
	
	// Método que evalúa las respuestas y llama al siguiente método (JUGAR O TERMINAR)
	private static void procesarRespuesta(String texto1) 
	{
		if(texto1.equals("1")) {
			// Llama a la función credito para asignar crédito
			jugar();
		}
		if(texto1.equals("2"))
		{
			// Llama a la funcion Terminar el juego
			victoria();
		}
	}
	
	// Método que muestra el juego en consola
	private static void jugar() 
	{
		intentos = 5;
		mostrarTexto("");
		mostrarTexto("Bienvenido, debes adivinar la palabra censurada");
		mostrarTexto("Todas las palabras pertenecen a una parte del cuerpo");
		mostrarTexto("Dispones de " + intentos + " intentos");
		mostrarTexto("Cada fallo restará 1 intento, buena suerte!");
		dividirPalabra();
		mostrarTexto("" + palabraOculta);
		comprobarLetras();
	}
	
	// Método que permite elegir una palabra aleatoriamente en el ArrayList
		private static int generarAleatorio(int valorMaximo) 
		{
			Random random1 = new Random();
			int aleatorio1 = random1.nextInt(valorMaximo);
			return aleatorio1;
		}
		
	// Método que divide las palabras
		private static void dividirPalabra () 
		{
			 // Selecciona una palabra aleatoriamente
			 seleccionAuto1 = palabras.get(generarAleatorio(palabras.size()));
			 palabraDividida.clear();
			 palabraOculta.clear();
			 // Divide la palabra aleatoria e introduce los caracteres en un Array char
			 for (char c : seleccionAuto1.toCharArray()) 
			 {
				 palabraDividida.add(c);
			 }
			 // Cambia cada char por un _
			 for (int i = 0; i <palabraDividida.size(); i++) 
			 {
				 palabraOculta.add('_');
			 }  
		}
	// Método para comprobar si coinciden las letras de la palabra cifrada con las que escribe el jugador
		private static void comprobarLetras() 
		{
			while (intentos>0)
			{
				mostrarTexto("Introduce una letra: ");
				letraPropuesta=sc.nextLine().toUpperCase();
				letra1 = letraPropuesta.charAt(0);
				// Recoge la posicion del primer char
				char letraPropuesta1=letraPropuesta.charAt(0);
				// Cambia la letra escondida por la real
				for(int i=0; i<palabraDividida.size(); i++) 
				{
					if (letra1.equals(palabraDividida.get(i))) 
					{
						palabraOculta.set(i, letraPropuesta1);
					}	
				}
						System.out.println(palabraOculta);
				
				Boolean resultado = palabraDividida.contains(letraPropuesta1);
				if (resultado) 
				{
						mostrarTexto ("Bien, continúa");
				} else 
				{
					mostrarTexto("Respuesta Incorrecta");
					intentos--;
					System.out.println("Vidas actuales: " + intentos);
				}
				
				if (palabraOculta.equals(palabraDividida)) {
						mostrarTexto("ENHORABUENA HAS GANADO");
						respuesta1 = menu("1.-Comenzar Juego \n 2.-Terminar Juego");
						procesarRespuesta(respuesta1);			
				}
		
			}
			// Si los intentos no se han gastado
			derrota(); // Se termina el juego perdiendo
			mostrarTexto("¿Quieres Volver a Jugar?"); 
			respuesta1 = menu("1.-Comenzar Juego \n 2.-Finalizar Juego"); 
			procesarRespuesta(respuesta1);
		}
		
		// Terminar el juego Ganando
		private static void victoria() {
			intentos = 0;
			mostrarTexto("Gracias por jugar");	
		}
		// Terminar el juego Perdiendo
		private static void derrota() {
			intentos = 0;
			mostrarTexto("Derrota, vuelve a intentarlo");
		}	
}
