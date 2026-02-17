import java.util.List;

// Esta clase controla el juego: puntuación, preguntas, respuestas
public class JuegoPreguntas {

    private List<Pregunta> preguntas;
    private int puntuacion;
    private int preguntaActual;

    public JuegoPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
        this.puntuacion = 0;
        this.preguntaActual = 0;
    }

    // Verifica si quedan preguntas
    public boolean quedanPreguntas() {
        return preguntaActual < preguntas.size();
    }

    // Devuelve la pregunta actual
    public Pregunta getPreguntaActual() {
        if (quedanPreguntas()) {
            return preguntas.get(preguntaActual);
        }
        return null;
    }

    // Procesa la respuesta del usuario
    public boolean responder(String respuestaUsuario) {
        Pregunta p = getPreguntaActual();

        if (p == null) {
            return false;
        }

        boolean correcta = p.esCorrecta(respuestaUsuario);

        if (correcta) {
            puntuacion++;
        }

        preguntaActual++;
        return correcta;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getTotalPreguntas() {
        return preguntas.size();
    }

    public void reiniciar() {
        puntuacion = 0;
        preguntaActual = 0;
    }
}