import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import estDatos.estDatNoLin.*;
import estDatos.estDatLin.*;

public class GestorTest{
    private Tarea tar1, tar2;
    private Evento ev1, ev2;
    private GestorGeneral gest;
    
    public GestorTest(){    
    }

    @BeforeEach
    public void setup(){
        tar1 = new Tarea("Estudiar", new Hora(12,30),new Fecha(12,10,2024),3);
        tar2 = new Tarea("Avanzar proyecto", new Hora(17,0),new Fecha(12,10,2024),1);
        ev1 = new Evento("Examen de grafos","", new Hora(9,45), new Fecha(1,4,2024),3,"");
        ev2 = new Evento("Examen de base de datos","", new Hora(8,15), new Fecha(5,4,2024),2,"");
        gest = new GestorGeneral(null);
        gest.registrarTarea(tar1);
        gest.registrarTarea(tar2);
        gest.registrarEvento(ev1);
        gest.registrarEvento(ev2);

    }

    @AfterEach
    public void tearDown(){
        gest.eliminarTodo();
    }

    @Test
    public void registrarTareaTest(){
        ListaSE<Tarea> liTar = gest.getTareas().inOrder();

        int siz = liTar.length();

        assertEquals(siz,2);

    }

    @Test
    public void registrarEventoTest(){
        ListaSE<Evento> liEv = gest.getEventos().inOrder();

        int siz = liEv.length();

        assertEquals(siz,2);

    }

    
      @Test
    public void eliminarTareaTest(){
        boolean res = true;;;;
        String titulo = tar1.getTitulo();
        gest.eliminarTarea(titulo);
        ListaSE<Tarea> listTar = gest.getTareas().inOrder();
        
        for(int i = 0; i<gest.getTareas().inOrder().length();i++){
            if(listTar.get(i)== tar1){
                res = false;
            }
        }
        
        assertTrue(res);
    }

    @Test
    public void eliminarEventoTest(){
        boolean res = true;;
        String titulo = ev2.getTitulo();
        gest.eliminarEvento(titulo);
        ListaSE<Evento> listEv = gest.getEventos().inOrder();
        
        for(int i = 0; i<gest.getTareas().inOrder().length();i++){
            if(listEv.get(i)== ev2){
                res = false;
            }
        }
        
        assertTrue(res);
    }

    @Test
    public void tareasEnOrdenTest(){
        boolean flag= true;
        ListaSE<Tarea> listTar = gest.getTareas().inOrder();

        for(int i = 1; i<gest.getTareas().inOrder().length();i++){
            if(listTar.get(i).getPrioridad()>listTar.get(i-1).getPrioridad()){
                flag = false;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void eventosEnOrdenTest(){
        boolean flag= true;
        ListaSE<Evento> listEv = gest.getEventos().inOrder();

        for(int i = 1; i<gest.getEventos().inOrder().length();i++){
            if(listEv.get(i).getPrioridad()>listEv.get(i-1).getPrioridad()){
                flag = false;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void eliminarTodoTest(){
        boolean empty;

        gest.eliminarTodo();
        empty = gest.getTareas().isEmpty() && gest.getEventos().isEmpty();

        assertTrue(empty);
    }
    

}
