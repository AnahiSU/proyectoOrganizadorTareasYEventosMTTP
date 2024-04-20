import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba para la clase Tarea.
 */
public class TareaTest{
    private Tarea tarea1;
    private Tarea tarea2;
    
    /**
     * Constructor de la clase de prueba.
     */
    public TareaTest(){
    }

    /**
     * Método que se ejecuta antes de cada prueba.
     */
    @BeforeEach
    public void setUp(){
        tarea1 = new Tarea("Cocinar", new Hora(12,0), new Fecha(15,4,2024),2);
        tarea2 = new Tarea("Hacer papeleo",new Hora(15,15), new Fecha(15,4,2024),3);
    }

    /**
     * Método que se ejecuta después de cada prueba.
     */
    @AfterEach
    public void tearDown(){
    }
    
    /**
     * Prueba el método toString para la tarea 1.
     */
    @Test
    public void toStringTest1(){
        assertEquals(tarea1.toString(), "Cocinar|1200|15042024|2|true");
    }
    
    /**
     * Prueba el método toString para la tarea 2.
     */
    @Test
    public void toStringTest2(){
        assertEquals(tarea2.toString(), "Hacer papeleo|1515|15042024|3|true");
    }
    
    /**
     * Prueba el método mostrar para la tarea 1.
     */
    @Test
    public void mostrarTest(){
        assertEquals(tarea1.mostrar(), "Tarea con título: Cocinar\nA las: 12:00\nEn la fecha: 15/04/2024\nY con prioridad de: 2");
    }
}
