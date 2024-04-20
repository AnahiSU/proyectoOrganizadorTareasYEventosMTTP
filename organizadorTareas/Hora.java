import java.util.Scanner;

/**
 * La clase Hora representa una hora con horas y minutos.
 */
public class Hora {
    private int hora; // Variable para almacenar las horas
    private int min;  // Variable para almacenar los minutos
    
    /**
     * Constructor de la clase Hora.
     *
     * @param h Horas.
     * @param m Minutos.
     */
    public Hora(int h, int m) {
        hora = h; 
        min = m;
    }
    
    /**
     * Establece las horas y los minutos mediante la entrada del usuario.
     */
    public void cambiarHora() {
        Scanner sc = new Scanner(System.in);
        boolean validador = false;

        while (!validador) {
            System.out.print("Ingrese las horas a la que quiere cambiar la actividad: ");
            int h = sc.nextInt();

            if (h >= 0 && h < 24) {
                hora = h;
                validador = true;
            } else {
                System.out.println("Hora inválida. Ingrese una hora valida 0 - 23.");
            }
        }
        validador = false;
        while(!validador) {
            System.out.println("Ingrese los minutos a los que quiere cambiar la actividad: ");
            int m = sc.nextInt();            
            if(m >= 0 && m < 60) {
                min = m;
                validador = true;
            }else {
                System.out.println("Minutos inválidos. Ingrese unos minutos validos 0 - 60.");
            }
        }
        sc.close();
        // Asignación de las nuevas horas
        // Asignación de los nuevos minutos
    }
    
    /**
     * Obtiene las horas.
     *
     * @return Las horas.
     */
    public int getHora() {
        return hora; // Devuelve las horas
    }
    
    /**
     * Obtiene los minutos.
     *
     * @return Los minutos.
     */
    public int getMin() {
        return min; // Devuelve los minutos
    }
    
    /**
     * Devuelve una representación en cadena de la hora en formato "hh:mm".
     *
     * @return La representación en cadena de la hora.
     */
    @Override
    public String toString() {
        String str = "0";
        if(min < 10){
            str = str + min;
        }else{
            str = ""+ min;
        }
        return hora + ":" + str; // Devuelve la hora y los minutos formateados
    }
    
    /**
     * Devuelve una representación en cadena de la hora en formato serializado "hhmm".
     *
     * @return La representación serializada de la hora.
     */
    public String toStringSerializado() {
        String str = "0";
        if(min < 10){
            str = str + min;
        }else{
            str = ""+ min;
        }
        return "" + hora + str; // Devuelve la hora y los minutos formateados sin separador
    }
}