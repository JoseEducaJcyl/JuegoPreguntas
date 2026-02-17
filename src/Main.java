import java.util.List;
import java.util.Scanner;

// Clase principal - aquí empieza todo
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static JuegoPreguntas juego;

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("      JUEGO DE PREGUNTAS");
        System.out.println("=================================");

        // 1. Cargar preguntas
        LectorPreguntas lector = new LectorPreguntas("preguntas.txt");
        List<Pregunta> preguntas = lector.leer();

        // 2. Crear juego
        juego = new JuegoPreguntas(preguntas);

        // 3. Mostrar menú
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Jugar");
            System.out.println("2. Reiniciar juego");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            opcion = leerNumero();

            switch(opcion) {
                case 1:
                    jugar();
                    break;
                case 2:
                    juego.reiniciar();
                    System.out.println("¡Juego reiniciado!");
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while(opcion != 3);

        scanner.close();
    }

    // Lee un número del teclado
    private static int leerNumero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Juego principal
    private static void jugar() {
        System.out.println("\n--- COMENZAMOS A JUGAR ---");
        System.out.println("Responde True o False a cada pregunta");
        System.out.println("");

        while (juego.quedanPreguntas()) {
            Pregunta p = juego.getPreguntaActual();

            System.out.println("Pregunta " + (juego.getPuntuacion() + 1) +
                    " de " + juego.getTotalPreguntas());
            System.out.println("Puntuación: " + juego.getPuntuacion());
            System.out.println("Pregunta: " + p.getTexto());
            System.out.print("Tu respuesta (True/False): ");

            String respuesta = scanner.nextLine();

            // Validar respuesta
            if (!respuesta.equalsIgnoreCase("True") &&
                    !respuesta.equalsIgnoreCase("False")) {
                System.out.println("ERROR: Escribe True o False");
                continue;
            }

            boolean correcta = juego.responder(respuesta);

            if (correcta) {
                System.out.println("¡CORRECTO! +1 punto");
            } else {
                System.out.println("INCORRECTO");
                System.out.println("La respuesta era: " + p.getRespuesta());
                System.out.println("\n--- FIN DEL JUEGO ---");
                System.out.println("Puntuación final: " + juego.getPuntuacion() +
                        "/" + juego.getTotalPreguntas());
                return;
            }
        }

        // Si terminó todas las preguntas
        System.out.println("\n¡FELICIDADES! Completaste todas las preguntas");
        System.out.println("Puntuación final: " + juego.getPuntuacion() +
                "/" + juego.getTotalPreguntas());
    }
}