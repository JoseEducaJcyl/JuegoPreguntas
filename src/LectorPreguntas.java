import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Esta clase SOLO se encarga de leer preguntas usando BufferedReader
public class LectorPreguntas {

    private String nombreArchivo;

    public LectorPreguntas(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    // Lee las preguntas del archivo usando BufferedReader
    public List<Pregunta> leer() {
        List<Pregunta> preguntas = new ArrayList<>();

        // Usamos BufferedReader (más eficiente para archivos grandes)
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int numeroLinea = 0;

            // ReadLine lee una línea completa del archivo
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;

                // Saltar líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                // Cada línea debe tener: pregunta|respuesta
                String[] partes = linea.split("\\|");

                if (partes.length == 2) {
                    String texto = partes[0].trim();
                    String respuesta = partes[1].trim();

                    // Validar que la respuesta sea True o False
                    if (respuesta.equalsIgnoreCase("True") ||
                            respuesta.equalsIgnoreCase("False")) {

                        // Creamos pregunta con información del archivo
                        Pregunta p = new PreguntaConArchivo(texto, respuesta,
                                nombreArchivo, numeroLinea);
                        preguntas.add(p);
                    } else {
                        System.out.println("Línea " + numeroLinea +
                                ": Respuesta no válida (debe ser True/False)");
                    }
                } else {
                    System.out.println("Línea " + numeroLinea +
                            ": Formato incorrecto (debe ser: pregunta|respuesta)");
                }
            }

            System.out.println("Cargadas " + preguntas.size() + " preguntas desde " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo " + nombreArchivo);
            System.out.println("Usando preguntas por defecto...");
            return preguntasPorDefecto();
        }

        // Si no hay preguntas en el archivo, usamos las por defecto
        if (preguntas.isEmpty()) {
            System.out.println("El archivo está vacío, usando preguntas por defecto");
            return preguntasPorDefecto();
        }

        return preguntas;
    }

    // Preguntas en español por si no hay archivo
    private List<Pregunta> preguntasPorDefecto() {
        List<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta("¿La sangre de una babosa es verde?", "True"));
        preguntas.add(new Pregunta("¿El animal más ruidoso es el elefante africano?", "False"));
        preguntas.add(new Pregunta("¿Un cuarto de los huesos humanos están en los pies?", "True"));
        preguntas.add(new Pregunta("¿Google se llamaba originalmente 'Backrub'?", "True"));
        preguntas.add(new Pregunta("¿Se puede doblar un papel más de 7 veces?", "False"));
        preguntas.add(new Pregunta("¿El chocolate puede matar a un perro?", "True"));
        preguntas.add(new Pregunta("¿La sangre humana es azul antes de oxigenarse?", "False"));
        preguntas.add(new Pregunta("¿Los osos polares son zurdos?", "False"));
        preguntas.add(new Pregunta("¿Las bananas son radioactivas?", "True"));
        preguntas.add(new Pregunta("¿Los canguros pueden saltar hacia atrás?", "False"));

        System.out.println("Usando " + preguntas.size() + " preguntas por defecto");
        return preguntas;
    }
}