// Importación de clases necesarias
import java.time.LocalDate;

/**
 * Clase abstracta Pendiente que representa una tarea o evento pendiente.
 */
public abstract class Pendiente {
    // Atributos protegidos para el título, descripción, hora, fecha y prioridad del pendiente
    protected String titulo;
    protected String descripcion;
    protected Hora hora;
    protected Fecha fecha;
    protected int prioridad;
    
    /**
     * Constructor de la clase Pendiente.
     * @param titulo El título del pendiente.
     * @param descripcion La descripción del pendiente.
     * @param hora La hora del pendiente.
     * @param fecha La fecha del pendiente.
     * @param prioridad La prioridad del pendiente.
     */
    protected Pendiente(String titulo, String descripcion, Hora hora, Fecha fecha, int prioridad){
        // Inicialización de los atributos con los valores proporcionados
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hora = hora;
        this.fecha = fecha;
        this.prioridad = prioridad;
    }
    
    /**
     * Método abstracto para obtener una representación en cadena del pendiente.
     * @return Representación en cadena del pendiente.
     */
    public abstract String toString();
    
    /**
     * Método abstracto para verificar si el pendiente se repite.
     * @return true si el pendiente se repite, false en caso contrario.
     */
    public abstract boolean seRepite();
}
