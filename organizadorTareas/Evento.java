import java.time.LocalDate;

/**
 * La clase Evento representa un evento pendiente y extiende la clase Pendiente.
 * Además, implementa la interfaz Comparable para comparar eventos por prioridad.
 */
public class Evento extends Pendiente implements Comparable<Evento>{
    private LocalDate fechaAc; // Fecha actual del sistema
    private String lugar; // Lugar del evento

    /**
     * Constructor de la clase Evento.
     * @param titulo El título del evento.
     * @param descripcion La descripción del evento.
     * @param hora La hora del evento.
     * @param fecha La fecha del evento.
     * @param prioridad La prioridad del evento.
     * @param lugar El lugar del evento.
     */
    public Evento(String titulo, String descripcion, Hora hora, Fecha fecha, int prioridad, String lugar){
        super(titulo, descripcion, hora, fecha, prioridad);
        fechaAc = LocalDate.now(); // Asignación de la fecha actual
        this.lugar = lugar; // Asignación del lugar
    }
    
    //Implementacion de getHORA Y getFecha para la implementacion de no tener tareas con fechas repetidads
    public Hora getHora(){
        return hora;
    }
    
    //lo mismo para fecha
    public Fecha getFecha(){
        return fecha;
    }
    
    // Métodos de la clase Evento

    /**
     * Establece el título del evento.
     * @param str El nuevo título del evento.
     */
    public void setTitulo(String str){
        titulo = str;
    }

    /**
     * Obtiene el título del evento.
     * @return El título del evento.
     */
    public String getTitulo(){
        return titulo;
    }

    /**
     * Establece la descripción del evento.
     * @param str La nueva descripción del evento.
     */
    public void setDescripcion(String str){
        descripcion = str;
    }

    /**
     * Obtiene la descripción del evento.
     * @return La descripción del evento.
     */
    public String getDescripcion(){
        return descripcion;
    }

    /**
     * Establece la prioridad del evento.
     * @param n La nueva prioridad del evento.
     */
    public void setPrioridad(int n){
        prioridad = n;
    }

    /**
     * Obtiene la prioridad del evento.
     * @return La prioridad del evento.
     */
    public int getPrioridad(){
        return prioridad;
    }

    /**
     * Compara este evento con otro evento basado en su prioridad.
     * @param e El evento a comparar.
     * @return Un entero negativo si este evento tiene mayor prioridad que el evento dado,
     * cero si tienen la misma prioridad, o un entero positivo si este evento tiene
     * menor prioridad que el evento dado.
     */
    @Override
    public int compareTo(Evento e){
        int res = 1;
        if(this == e){
            res = 0;
        }else if(prioridad > e.prioridad){
            res = -1;
        }
        return res;
    }

    /**
     * Devuelve una representación en cadena del evento.
     * @return La representación en cadena del evento.
     */
    @Override
    public String toString(){
        return titulo+"|"+descripcion+"|"+hora.toStringSerializado()+"|"+fecha.toStringSerializado()+"|"+lugar+"|"+prioridad+"|"+seRepite();
    } //unir
    
    /**
     * Devuelve una cadena que representa la información del evento.
     * @return Una cadena con la información del evento.
     */
    public String mostrar(){
        return "Evento con título: "+titulo+"\nCon su descripción: "+descripcion+"\nA las: "+hora.toString()+"\nEn la fecha: "+fecha.toString()+"\nEn: "+lugar+"\nY con prioridad: "+prioridad;
    } 

    /**
     * Verifica si el evento se repite.
     * @return true si el evento se repite, false en caso contrario.
     */
    public boolean seRepite(){
        boolean bol = false;
        if(fecha.getA() >= fechaAc.getYear()){
            if(fecha.getM() > fechaAc.getMonthValue()){
                bol = true;
            }
            if(fecha.getM() == fechaAc.getMonthValue()){
                if(fecha.getD() >= fechaAc.getDayOfMonth()){
                    bol = true;
                }
            }
        }
        return bol;
    }
}