package Controlador;
import Modelo.Escritor;
import java.util.*;
import Controlador.estDatos.estDatNoLin.*;
import Controlador.estDatos.estDatLin.*;

/**
 * La clase GestorGeneral es responsable de gestionar eventos y tareas utilizando árboles binarios de búsqueda de tal forma que
 * ambos son ordenados por el atributo prioridad.
 */
public class GestorGeneral {
    private ArbolBB<Evento> pqEvento; // �?rbol binario de búsqueda para eventos
    private ArbolBB<Tarea> pqTarea;   // �?rbol binario de búsqueda para tareas
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

    public boolean modificarDescripcion(String d, String titulo){
        //solo para eventos
        boolean res = false;
        Evento e = buscarEventoObjeto(titulo);
        if(e!=null){
            String eAntiguo = e.toString();
            e.setDescripcion(d);
            String eNuevo = e.toString();
            escritorEvento.eliminar(eAntiguo);
            escritorEvento.escribir(eNuevo);
            ArrayList<String[]> datosEv = escritorEvento.leerTodo();
            reiniciarArbolEv();
            registrarBaseEv(datosEv);
            res = true;
        }
        return res;
    }

    /*
    Estados:
    1. Encontré y no elimine (fecha y hora invalidas(repetido))
    2. No encontré el pendiente
    3. Encontrado y modificado
     */
    public byte modificarFecha(Fecha f, String titulo, byte tipo){
        byte res = 3;

        if(tipo == 1){
            Tarea tar = buscarTareaObjeto(titulo);
            if(tar == null){
                res = 2;
            }else{
                if(validarTiempo(tar.getHora(), f, "tarea")){
                    String tAntiguo = tar.toString();
                    tar.setFecha(f);
                    String tNuevo = tar.toString();
                    escritorTarea.eliminar(tAntiguo);
                    escritorTarea.escribir(tNuevo);
                    ArrayList<String[]> datosTar = escritorTarea.leerTodo();
                    reiniciarArbolTar();
                    registrarBaseTar(datosTar);
                }else{
                    res = 1;
                }
            }
        }else{
            Evento ev = buscarEventoObjeto(titulo);
            if(ev == null){
                res = 2;
            }else{
                if(validarTiempo(ev.getHora(), f, "evento")){
                    String eAntiguo = ev.toString();
                    ev.setFecha(f);
                    String eNuevo = ev.toString();
                    escritorEvento.eliminar(eAntiguo);
                    escritorEvento.escribir(eNuevo);
                    ArrayList<String[]> datosEv = escritorEvento.leerTodo();
                    reiniciarArbolEv();
                    registrarBaseEv(datosEv);
                }else{
                    res = 1;
                }
            }            
        }
        return res;
    }

    /*
    Estados:
    1. Encontré y no elimine (fecha y hora invalidas(repetido))
    2. No encontré el pendiente
    3. Encontrado y modificado
     */
    public byte modificarHora(Hora h, String titulo, byte tipo){
        byte res = 3;
        if(tipo == 1){
            Tarea tar = buscarTareaObjeto(titulo);
            if(tar == null){
                res = 2;
            }else{
                if(validarTiempo(h, tar.getFecha(), "tarea")){
                    String tAntiguo = tar.toString();
                    tar.setHora(h);
                    String tNuevo = tar.toString();
                    escritorTarea.eliminar(tAntiguo);
                    escritorTarea.escribir(tNuevo);
                    ArrayList<String[]> datosTar = escritorTarea.leerTodo();
                    reiniciarArbolTar();
                    registrarBaseTar(datosTar);
                }else{
                    res = 1;
                }
            }
        }else{
            Evento ev = buscarEventoObjeto(titulo);
            if(ev == null){
                res = 2;
            }else{
                if(validarTiempo(h, ev.getFecha(), "evento")){
                    String eAntiguo = ev.toString();
                    ev.setHora(h);
                    String eNuevo = ev.toString();
                    escritorEvento.eliminar(eAntiguo);
                    escritorEvento.escribir(eNuevo);
                    ArrayList<String[]> datosEv = escritorEvento.leerTodo();
                    reiniciarArbolEv();
                    registrarBaseEv(datosEv);
                }else{
                    res = 1;
                }
            }            
        }
        return res;
    }

    public boolean modificarTitulo(String t, String titulo, byte tipo){
        boolean res = false;
        if(tipo == 1){
            Tarea tar =  buscarTareaObjeto(titulo);
            if(tar != null){
                String tAntiguo = tar.toString();
                tar.setTitulo(t);
                String tNuevo = tar.toString();
                escritorTarea.eliminar(tAntiguo);
                escritorTarea.escribir(tNuevo);
                ArrayList<String[]> datosTar = escritorTarea.leerTodo();
                reiniciarArbolTar();
                registrarBaseTar(datosTar);
                res = true;
            }
        }else{
            Evento ev =  buscarEventoObjeto(titulo);
            if(ev != null){
                String eAntiguo = ev.toString();
                ev.setTitulo(t);
                String eNuevo = ev.toString();
                escritorEvento.eliminar(eAntiguo);
                escritorEvento.escribir(eNuevo);
                ArrayList<String[]> datosEv = escritorEvento.leerTodo();
                reiniciarArbolEv();
                registrarBaseEv(datosEv);
                res = true;
            }
        }
        return res;
    }

    public boolean modificarPrioridad(int p, String titulo, byte tipo){
        boolean res = false;
        if(tipo == 1){
            Tarea tar =  buscarTareaObjeto(titulo);
            if(tar != null){
                String tAntiguo = tar.toString();
                tar.setPrioridad(p);
                String tNuevo = tar.toString();
                escritorTarea.eliminar(tAntiguo);
                escritorTarea.escribir(tNuevo);
                ArrayList<String[]> datosTar = escritorTarea.leerTodo();
                reiniciarArbolTar();
                registrarBaseTar(datosTar);
                res = true;
            }
        }else{
            Evento ev =  buscarEventoObjeto(titulo);
            if(ev != null){
                String eAntiguo = ev.toString();
                ev.setPrioridad(p);
                String eNuevo = ev.toString();
                escritorEvento.eliminar(eAntiguo);
                escritorEvento.escribir(eNuevo);
                ArrayList<String[]> datosEv = escritorEvento.leerTodo();
                reiniciarArbolEv();
                registrarBaseEv(datosEv);
                res = true;
            }
        }
        return res;
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

    public boolean validarTiempo(Hora hora, Fecha fecha, String tipo){
        boolean res;
        if(tipo.equals("tarea")){
            res = validarTarea(hora, fecha);
        }else{
            res = validarEvento(hora, fecha);
        }
        return res;
    }

    private boolean validarTarea(Hora h, Fecha f){
        boolean valida = true;
        ListaSE<Tarea> lista = pqTarea.inOrder();

        for(int i = 0; i < lista.length(); i++){
            Tarea t = lista.get(i);
            if(t.getHora().equals(h)){
                if(t.getFecha().equals(f)){
                    valida = false;
                } 
            }
        }
        return valida;
    }

    private boolean validarEvento(Hora h, Fecha f){
        boolean valida = true;
        ListaSE<Evento> lista =  pqEvento.inOrder();
        for(int i = 0; i < lista.length(); i++){
            Evento e = lista.get(i);
            if(e.getHora().equals(h)){
                if(e.getFecha().equals(f)){
                    valida = false;
                } 
            }
        }
        return valida;
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

    private void reiniciarArbolTar(){
        // Elimina todos los eventos del árbol de eventos
        pqTarea.deleteAll();
    }

    private void reiniciarArbolEv(){
        // Elimina todos los eventos del árbol de eventos
        pqEvento.deleteAll();
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
    private Tarea buscarTareaObjeto(String s){
        ListaSE<Tarea> listaT = pqTarea.inOrder();
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

        Tarea t = buscarTareaObjeto(s); // Busca la tarea por título
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
    private Evento buscarEventoObjeto(String s){
        ListaSE<Evento> listaE = pqEvento.inOrder(); // Obtiene la lista de eventos ordenados
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

        Evento v = buscarEventoObjeto(s); // Busca el evento por título
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
        Evento v = buscarEventoObjeto(titulo); // Busca el evento por título
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
        Tarea t = buscarTareaObjeto(titulo); // Busca la tarea por título
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

