import java.util.*;
import estDatos.estDatNoLin.*;
import estDatos.estDatLin.*;

/**
 * La clase GestorGeneral es responsable de gestionar eventos y tareas utilizando árboles binarios de búsqueda de tal forma que
 * ambos son ordenados por el atributo prioridad.
 */
public class GestorGeneral {
    private ArbolBB<Evento> pqEvento; // Árbol binario de búsqueda para eventos
    private ArbolBB<Tarea> pqTarea;   // Árbol binario de búsqueda para tareas
    private Escritor escritorTarea;// Objeto para escribir en archivos
    private Escritor escritorEvento;// Objeto para escribir en archivos
    private boolean flag = true; // Bandera para controlar escritura en archivos
    private Usuario user;
    /**
     * Constructor de la clase GestorGeneral.
     * Inicializa los árboles binarios de búsqueda para eventos y tareas.
     */
    public GestorGeneral(Usuario u) {
        pqEvento = new ArbolBB<>(); // Inicializa el árbol para eventos
        pqTarea = new ArbolBB<>(); // Inicializa el árbol para tareas

        escritorTarea = new Escritor(u.toStringDirAs() +"\\BaseTareas.txt");
        escritorEvento = new Escritor(u.toStringDirAs() + "\\BaseEventos.txt"); // Inicializa el escritor de archivos

        // Carga datos de archivos
        ArrayList<String[]> datosTar = escritorTarea.leerTodo();
        ArrayList<String[]> datosEv = escritorEvento.leerTodo();

        // Registra eventos y tareas desde datos cargados
        registrarBaseEv(datosEv);
        registrarBaseTar(datosTar);
        user = u;
    }

    /**
     * Registra eventos desde datos proporcionados.
     * @param datosEv Lista de arrays de strings con datos de eventos.
     */
    private void registrarBaseEv(ArrayList<String[]> datosEv){
        if(!datosEv.isEmpty()){
            for(int i = 0; i<datosEv.size();i++){
                // Extrae datos de la lista
                String titulo = datosEv.get(i)[0];
                String desc = datosEv.get(i)[1];
                int h = Integer.parseInt(datosEv.get(i)[2]);
                int hor = h/100;
                int min = h%100;
                int dia = (Integer.parseInt(datosEv.get(i)[3]))/1000000;
                int mes = ((Integer.parseInt(datosEv.get(i)[3]))/10000)%100;
                int anho = (Integer.parseInt(datosEv.get(i)[3]))%10000;
                String lugar = datosEv.get(i)[4];
                int prio = Integer.parseInt(datosEv.get(i)[5]);

                // Crea un nuevo evento y lo registra
                Evento e = new Evento(titulo,desc,new Hora(hor,min), new Fecha(dia,mes,anho), prio,lugar);
                flag = false; // Desactiva la bandera para evitar escritura en archivos temporales
                registrarEvento(e);
                flag = true; // Reactiva la bandera para permitir escritura posterior
            }
        }
    }

    /**
     * Registra tareas desde datos proporcionados.
     * @param datosTar Lista de arrays de strings con datos de tareas.
     */
    private void registrarBaseTar(ArrayList<String[]> datosTar){
        if(!datosTar.isEmpty()){
            for(int i = 0; i<datosTar.size();i++){
                // Extrae datos de la lista
                String titulo = datosTar.get(i)[0];
                int h = Integer.parseInt(datosTar.get(i)[1]);
                int hor = h/100;
                int min = h%100;
                int dia = (Integer.parseInt(datosTar.get(i)[2]))/1000000;
                int mes = ((Integer.parseInt(datosTar.get(i)[2]))/10000)%100;
                int anho = (Integer.parseInt(datosTar.get(i)[2]))%10000;
                int prioridad = Integer.parseInt(datosTar.get(i)[3]);
                flag = false; // Desactiva la bandera para evitar escritura en archivos temporales
                // Crea una nueva tarea y la registra
                Tarea t = new Tarea(titulo,new Hora(hor,min), new Fecha(dia,mes,anho), prioridad);
                registrarTarea(t);
                flag = true; // Reactiva la bandera para permitir escritura posterior
            }
        }
    }

    /**
     * Obtiene el árbol de tareas.
     * @return El árbol de tareas.
     */
    public ArbolBB getTareas(){
        return pqTarea;
    }

    /**
     * Obtiene el árbol de eventos.
     * @return El árbol de eventos.
     */
    public ArbolBB getEventos(){
        return pqEvento;
    }

    /**
     * Método para registrar una nueva tarea en el gestor.
     * @param tarea La tarea a registrar.
     */
    public void registrarTarea(Tarea tarea) {
        pqTarea.insertData(tarea); // Inserta la tarea en el árbol de tareas
        if(flag){
            escritorTarea.escribir(tarea.toString()); // Escribe la tarea en archivo si la bandera lo permite
        }
    }

    /**
     * Método para registrar un nuevo evento en el gestor.
     * @param evento El evento a registrar.
     */
    public void registrarEvento(Evento evento) {
        pqEvento.insertData(evento); // Inserta el evento en el árbol de eventos
        if(flag){
            escritorEvento.escribir(evento.toString()); // Escribe el evento en archivo si la bandera lo permite
        }
    }

    /**
     * Método para eliminar una tarea del gestor.
     * @param tarea La tarea a eliminar.
     */
    private void eliminarTarea(Tarea tarea) {
        escritorTarea.eliminar(tarea.toString());
        pqTarea.eliminar(tarea); // Elimina la tarea del árbol de tareas si la bandera lo permite
    }

    /**
     * Método para eliminar un evento del gestor.
     * @param evento El evento a eliminar.
     */
    private void eliminarEvento(Evento evento) {
        escritorEvento.eliminar(evento.toString());
        pqEvento.eliminar(evento); // Elimina el evento del árbol de eventos
    }

    /**
     * Elimina todas las tareas y eventos del gestor y limpia los archivos de datos.
     */
    public void eliminarTodo(){
        escritorTarea.limpiar(); // Limpia el archivo de eventos
        escritorEvento.limpiar(); // Limpia el archivo de tareas
        pqEvento.deleteAll(); // Elimina todos los eventos del árbol de eventos
        pqTarea.deleteAll(); // Elimina todas las tareas del árbol de tareas
    }

    /**
     * Método de prueba para imprimir los eventos y tareas ordenados.
     * Utiliza el recorrido inOrder de los árboles binarios de búsqueda.
     */
    public void imprimirOrdenado(){
        System.out.println("---------------------------------------------");
        System.out.println("Eventos Pendientes: ");
        ListaSE<Evento> lisEvento = pqEvento.inOrder();
        for(int i = 0; i < lisEvento.length(); i++){
            System.out.println(lisEvento.get(i).mostrar());
            System.out.println("---------------------------------------------");
        }
        // Imprime los eventos ordenados
        ListaSE<Tarea> lisTarea = pqTarea.inOrder();
        System.out.println("Tareas Pendientes: ");
        for(int i = 0; i < lisTarea.length(); i++){ 
            System.out.println(lisTarea.get(i).mostrar());
            System.out.println("---------------------------------------------");
        }
        // Imprime las tareas ordenadas
    }

    /**
     * Metodo para buscar un objeto Tarea
     * @param titulo, listaTareas.
     */
    private Tarea buscarTarea(String s, ListaSE<Tarea> listaT){
        Tarea tarea = null;
        ArrayList<Tarea> listaCoin = new ArrayList<>();
        for(int i = 0; i < listaT.length(); i++){
            // Obtiene el título de la tarea y lo convierte a minúsculas
            String titulo = listaT.get(i).getTitulo();
            titulo = titulo.toLowerCase();
            int contador = 0;
            // Compara caracteres de los títulos con la cadena de búsqueda
            for(int j = 0, h = 0; j < titulo.length() && h < s.length(); j++,h++){
                if(titulo.charAt(j) == s.charAt(h)){
                    contador++;
                }
            }
            // Si el título coincide parcial o completamente con la cadena de búsqueda, se agrega a la lista de coincidencias
            if(contador >= 3 || contador == titulo.length() || contador == s.length()){
                tarea = listaT.get(i); 
                listaCoin.add(listaT.get(i));
            }
        }

        // Si hay más de una coincidencia y una de ellas es exactamente igual a la cadena de búsqueda, se selecciona esa tarea
        if(listaCoin.size() > 1){
            for(int i = 0; i < listaCoin.size(); i++){
                String candidato = listaCoin.get(i).getTitulo();
                if(candidato.equals(s)){
                    tarea = listaCoin.get(i);
                    break;
                }
            }
        }
        return tarea; // Retorna la tarea encontrada
    }

    /**
     *Método público para buscar una tarea por título
     * @param titulo, tarea con titulo a buscar.
     */
    public String buscarTarea(String s){
        ListaSE<Tarea> listaTarea = pqTarea.inOrder(); // Obtiene la lista de tareas ordenadas
        Tarea t = buscarTarea(s, listaTarea); // Busca la tarea por título
        String res = "No se encontró su Tarea"; // Mensaje predeterminado si no se encuentra la tarea
        if(t != null){
            res = t.mostrar(); // Si se encuentra la tarea, se devuelve su representación en cadena
        }
        return res; // Retorna el resultado de la búsqueda
    }

    // Método privado para buscar un evento por título
    /**
     * Metodo para buscar un objeto Evento
     * @param titulo, listaTareas.
     */
    private Evento buscarEvento(String s, ListaSE<Evento> listaE){
        Evento evento = null;
        ArrayList<Evento> listaCoin = new ArrayList<>();
        for(int i = 0; i < listaE.length(); i++){
            // Obtiene el título del evento y lo convierte a minúsculas
            String titulo = listaE.get(i).getTitulo();
            titulo = titulo.toLowerCase();
            int contador = 0;
            // Compara caracteres de los títulos con la cadena de búsqueda
            for(int j = 0, h = 0; j < titulo.length() && h < s.length(); j++,h++){
                if(titulo.charAt(j) == s.charAt(h)){
                    contador++;
                }
            }
            // Si el título coincide parcial o completamente con la cadena de búsqueda, se agrega a la lista de coincidencias
            if(contador >= 3 || contador == titulo.length() || contador == s.length()){
                evento = listaE.get(i); 
                listaCoin.add(listaE.get(i));
            }
        }

        // Si hay más de una coincidencia y una de ellas es exactamente igual a la cadena de búsqueda, se selecciona ese evento
        if(listaCoin.size() > 1){
            for(int i = 0; i < listaCoin.size(); i++){
                String candidato = listaCoin.get(i).getTitulo();
                if(candidato.equals(s)){
                    evento = listaCoin.get(i);
                    break;
                }
            }
        }
        return evento; // Retorna el evento encontrado
    }

    /**
     *  Método público para buscar un Evento por título
     * @param titulo, evento con titulo a buscar.
     */
    public String buscarEvento(String s){
        ListaSE<Evento> listaEvento = pqEvento.inOrder(); // Obtiene la lista de eventos ordenados
        Evento v = buscarEvento(s, listaEvento); // Busca el evento por título
        String res = "No se encontró su Evento"; // Mensaje predeterminado si no se encuentra el evento
        if(v != null){
            res = v.mostrar(); // Si se encuentra el evento, se devuelve su representación en cadena
        }
        return res; // Retorna el resultado de la búsqueda
    }

    // Método público para eliminar un evento por título
    /**
     *  Método público para eliminar un Evento
     * @param titulo, Evento con titulo a eliminar.
     */
    public boolean eliminarEvento(String titulo){
        boolean res = false;
        ListaSE<Evento> listaEvento = pqEvento.inOrder(); // Obtiene la lista de eventos ordenados
        Evento v = buscarEvento(titulo, listaEvento); // Busca el evento por título
        if(v != null){
            eliminarEvento(v); // Si se encuentra el evento, se elimina
            res = true;
        }
        return res;
    }

    // Método público para eliminar una tarea por título
    /**
     *  Método público para eliminar una Tarea
     * @param titulo, Tarea con titulo a eliminar.
     */
    public boolean eliminarTarea(String titulo){
        boolean res = false;
        ListaSE<Tarea> listaTarea = pqTarea.inOrder(); // Obtiene la lista de tareas ordenadas
        Tarea t = buscarTarea(titulo, listaTarea); // Busca la tarea por título
        if(t != null){
            eliminarTarea(t); // Si se encuentra la tarea, se elimina
            res = true;
        }
        return res;
    }
    
    public String getNomUs(){
        return user.getNombre();
    }
}

