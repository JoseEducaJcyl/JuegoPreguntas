// Clase que representa una pregunta
// RESPONSABILIDAD: Guardar la información de una pregunta
public class Pregunta {
    private String texto;      // La pregunta
    private String respuesta;   // La respuesta correcta

    // Constructor
    public Pregunta(String texto, String respuesta) {
        this.texto = texto;
        this.respuesta = respuesta;
    }

    // Getters (encapsulación)
    public String getTexto() {
        return texto;
    }

    public String getRespuesta() {
        return respuesta;
    }

    // Verifica si la respuesta es correcta
    public boolean esCorrecta(String respuestaUsuario) {
        return respuesta.equalsIgnoreCase(respuestaUsuario);
    }
}