/**
 * La clase Fecha representa una fecha con día, mes y año.
 */
public class Fecha {
    private int d; // Variable para almacenar el día
    private int m; // Variable para almacenar el mes
    private int a; // Variable para almacenar el año
    
    /**
     * Constructor de la clase Fecha.
     *
     * @param d Día.
     * @param m Mes.
     * @param a Año.
     */
    public Fecha(int d, int m, int a) {
        this.d = d;
        this.m = m;
        this.a = a;
    }
    
    /**
     * Obtiene el día.
     *
     * @return El día.
     */
    public int getD() {
        return d; // Devuelve el día
    }
    
    /**
     * Obtiene el mes.
     *
     * @return El mes.
     */
    public int getM() {
        return m; // Devuelve el mes
    }
    
    /**
     * Obtiene el año.
     *
     * @return El año.
     */
    public int getA() {
        return a; // Devuelve el año
    }
    
    /**
     * Devuelve una representación en cadena de la fecha en formato "d/m/a".
     *
     * @return La representación en cadena de la fecha.
     */
    @Override
    public String toString() {
        String strM = "0";
        String strD = "0";
        if(m < 10){
            strM = strM + m;
        }else{
            strM = "" + m;
        }
        if(d < 10){
            strD = strD + d;
        }else{
            strD = "" + d;
        }
        return strD + "/" + strM + "/" + a; // Devuelve la fecha formateada
    }
    
    /**
     * Devuelve una representación en cadena de la fecha en formato serializado "ddmmaa".
     * Se asume que los valores tienen siempre dos dígitos.
     *
     * @return La representación serializada de la fecha.
     */
    public String toStringSerializado() {
        // Se asegura de que el año tenga solo dos dígitos
        
        // Convierte el día y el mes a cadenas con dos dígitos
        String dS = (d < 10) ? "0" + d : String.valueOf(d);
        String mS = (m < 10) ? "0" + m : String.valueOf(m);
        
        // Devuelve la fecha serializada sin separadores
        return dS + mS + a;
    }
}
