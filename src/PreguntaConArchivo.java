// Esta clase hereda de Pregunta (HERENCIA)
// AÑADE: información de dónde viene la pregunta (archivo)
public class PreguntaConArchivo extends Pregunta {

    private String nombreArchivo;  // De qué archivo viene
    private int linea;              // En qué línea del archivo

    public PreguntaConArchivo(String texto, String respuesta,
                              String nombreArchivo, int linea) {
        super(texto, respuesta);  // Llamamos al constructor de la clase padre
        this.nombreArchivo = nombreArchivo;
        this.linea = linea;
    }

    // Getters
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public int getLinea() {
        return linea;
    }
}