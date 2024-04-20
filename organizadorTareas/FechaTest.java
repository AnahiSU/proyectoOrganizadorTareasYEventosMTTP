import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba para la clase Fecha.
 */
public class FechaTest{
    private Fecha fecha;
    
    /**
     * Constructor de la clase de prueba.
     */
    public FechaTest(){
    }

    /**
     * Método que se ejecuta antes de cada prueba.
     */
    @BeforeEach
    public void setUp(){
        fecha = new Fecha(22,06,2024);
    }

    /**
     * Método que se ejecuta después de cada prueba.
     */
    @AfterEach
    public void tearDown(){
    }
    
    /**
     * Prueba el método toString para la fecha.
     */
    @Test
    public void toStringTest(){
        assertEquals(fecha.toString(), "22/06/2024");
    }
    
    /**
     * Prueba el método toStringSerializado para la fecha.
     */
    @Test
    public void toStringSerializadoTest(){
        assertEquals(fecha.toStringSerializado(), "22062024");
    }
}
