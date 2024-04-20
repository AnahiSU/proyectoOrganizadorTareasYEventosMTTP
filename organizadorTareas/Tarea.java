/**
 * La clase Tarea representa una tarea pendiente y extiende la clase Pendiente.
 * Además, implementa la interfaz Comparable para comparar tareas por prioridad.
 */
public class Tarea extends Pendiente implements Comparable<Tarea> {

    // Constructores de la clase Tarea

    /**
     * Constructor de Tarea con todos los parámetros.
     * @param titulo El título de la tarea.
     * @param descripcion La descripción de la tarea.
     * @param hora La hora de la tarea.
     * @param fecha La fecha de la tarea.
     * @param prioridad La prioridad de la tarea.
     */

    /**
     * Constructor de Tarea con título, hora, fecha y prioridad.
     * @param titulo El título de la tarea.
     * @param hora La hora de la tarea.
     * @param fecha La fecha de la tarea.
     * @param prioridad La prioridad de la tarea.
     */
    public Tarea(String titulo, Hora hora, Fecha fecha, int prioridad) {
        super(titulo, "", hora, fecha, prioridad);
    }

    // Métodos de la clase Tarea

    /**
     * Establece el título de la tarea.
     * @param str El nuevo título de la tarea.
     */
    public void setTitulo(String str) {
        titulo = str;
    }

    /**
     * Obtiene el título de la tarea.
     * @return El título de la tarea.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece la prioridad de la tarea.
     * @param n La nueva prioridad de la tarea.
     */
    public void setPrioridad(int n) {
        prioridad = n;
    }

    /**
     * Obtiene la prioridad de la tarea.
     * @return La prioridad de la tarea.
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Compara esta tarea con otra tarea basada en su prioridad.
     * @param e La tarea a comparar.
     * @return Un entero negativo si esta tarea tiene mayor prioridad que la tarea dada,
     * cero si tienen la misma prioridad, o un entero positivo si esta tarea tiene
     * menor prioridad que la tarea dada.
     */
    @Override
    public int compareTo(Tarea e) {
        int res = 1;
        if (this == e) {
            res = 0;
        } else if (prioridad > e.prioridad) {
            res = -1;
        }
        return res;
    }

    /**
     * Devuelve una representación en cadena de la tarea.
     * @return La representación en cadena de la tarea.
     */
    @Override
    public String toString(){
        return titulo + "|" + hora.toStringSerializado() + "|" + fecha.toStringSerializado() + "|"+ prioridad +"|"+ seRepite();
    }

    /**
     * Muestra la información de la tarea.
     * @return Una cadena con la información de la tarea.
     */
    public String mostrar(){
        return "Tarea con título: "+titulo + "\nA las: " + hora.toString()+ "\nEn la fecha: " + fecha.toString() + "\nY con prioridad de: "+ prioridad;
    }

    /**
     * Verifica si la tarea se repite.
     * @return true si la tarea se repite, false en caso contrario.
     */
    public boolean seRepite() {
        return true; 
    }
}
