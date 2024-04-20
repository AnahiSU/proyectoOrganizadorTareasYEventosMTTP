import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * La clase HoraTest contiene pruebas unitarias para la clase Hora.
 */
public class HoraTest {
    private Hora hora;
    private Hora hora1;
    private Hora hora2;
    
    /**
     * Constructor de la clase HoraTest.
     */
    public HoraTest(){
    }

    /**
     * Configuración inicial para cada prueba.
     */
    @BeforeEach
    public void setUp(){
        hora = new Hora(0,0);
    }

    /**
     * Realiza la limpieza posterior a cada prueba.
     */
    @AfterEach
    public void tearDown(){
    }
    
    /**
     * Prueba unitaria del método toString() de la clase Hora.
     * Verifica si la representación en cadena de la hora es correcta.
     */
    @Test
    public void toStringTest(){
        hora1 = new Hora(11,20);
        assertEquals("11:20", hora1.toString());
    }
    
    /**
     * Prueba unitaria del método toStringSerializado() de la clase Hora.
     * Verifica si la representación serializada de la hora es correcta.
     */
    @Test
    public void toStringSerializadoTest(){
        hora2 = new Hora(15,0);
        assertEquals("1500", hora2.toStringSerializado());
    }
}
