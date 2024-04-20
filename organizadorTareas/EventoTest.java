import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba para la clase Evento.
 */
public class EventoTest{
    private Evento evento;
    
    /**
     * Constructor de la clase de prueba.
     */
    public EventoTest(){
    }

    /**
     * Método que se ejecuta antes de cada prueba.
     */
    @BeforeEach
    public void setUp(){
        evento = new Evento("Examen", "En la 624, Calculo 2", new Hora(11,15), new Fecha(15,4,2024), 3, "Umss");
    }

    /**
     * Método que se ejecuta después de cada prueba.
     */
    @AfterEach
    public void tearDown(){
    }
    
    /**
     * Prueba el método toString para el evento.
     */
    @Test
    public void toStringTest(){
        assertEquals(evento.toString(), "Examen|En la 624, Calculo 2|1115|15042024|Umss|3|true");
    }
    
    /**
     * Prueba el método seRepite para el evento.
     */
    @Test
    public void seRepiteTest(){
        assertTrue(evento.seRepite());
    }
    
    /**
     * Prueba el método mostrar para el evento.
     */
    @Test
    public void mostrarTest(){
        assertEquals(evento.mostrar(), "Evento con título: Examen\nCon su descripción: En la 624, Calculo 2\nA las: 11:15\nEn la fecha: 15/04/2024\nEn: Umss\nY con prioridad: 3");
    }
}
