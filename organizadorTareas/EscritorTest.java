import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

/**
 * Esta clase contiene pruebas unitarias para la clase Escritor.
 */
public class EscritorTest {
    private Escritor escritor;
    private final String nombreArchivo = "test.txt";

    /**
     * Preparación antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        escritor = new Escritor(nombreArchivo);
        // Limpia el archivo antes de cada prueba
        escritor.limpiar();
    }

    /**
     * Limpia después de cada prueba.
     */
    @AfterEach
    public void tearDown() {
        // Elimina el archivo después de cada prueba
        File file = new File(nombreArchivo);
        file.delete();
    }

    /**
     * Prueba para verificar el método escribir().
     */
    @Test
    public void testEscribir() {
        escritor.escribir("Primera línea");
        escritor.escribir("Segunda línea");
        assertEquals(2, escritor.contarElementos());
    }

    /**
     * Prueba para verificar el método leerTodo().
     */
    @Test
    public void testLeerTodo() {
        escritor.escribir("Primera línea");
        escritor.escribir("Segunda línea");
        ArrayList<String[]> contenido = escritor.leerTodo();
        assertEquals(2, contenido.size());
        assertEquals("Primera línea", contenido.get(0)[0]);
        assertEquals("Segunda línea", contenido.get(1)[0]);
    }

    /**
     * Prueba para verificar el método limpiar().
     */
    @Test
    public void testLimpiar() {
        escritor.escribir("Primera línea");
        escritor.limpiar();
        assertEquals(0, escritor.contarElementos());
    }

    /**
     * Prueba para verificar el método leerUltimaLinea().
     */
    @Test
    public void testLeerUltimaLinea() {
        escritor.escribir("Primera línea");
        escritor.escribir("Segunda línea");
        assertEquals("Segunda línea", escritor.leerUltimaLinea());
    }

    /**
     * Prueba para verificar el método buscarPorValor().
     */
    @Test
    public void testBuscarPorValor() {
        escritor.escribir("Primera");
        escritor.escribir("Segunda");
        assertEquals("Primera", escritor.buscarPorValor("Primera"));
    }

    /**
     * Prueba para verificar el método eliminar().
     */
    @Test
    public void testEliminar() {
        escritor.escribir("Primera línea");
        escritor.escribir("Segunda línea");
        escritor.eliminar("Primera");
        assertEquals(1, escritor.contarElementos());
        assertEquals("Segunda línea", escritor.leerUltimaLinea());
    }

    /**
     * Prueba para verificar el método contarElementos().
     */
    @Test
    public void testContarElementos() {
        escritor.escribir("Primera línea");
        escritor.escribir("Segunda línea");
        assertEquals(2, escritor.contarElementos());
    }
}


