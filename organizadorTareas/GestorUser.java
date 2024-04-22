import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class GestorUser{
    private ArrayList<Usuario> users;
    private boolean creado;
    private Escritor escritor;
    
    public GestorUser(){
        users = new ArrayList();
        creado = false;
        escritor = new Escritor("BaseUsuarios.txt");
        
        //gestor lee su base de datos y los usuarios no se pierden
        ArrayList<String[]> base = escritor.leerTodo();
        for(int i = 0; i<base.size();i++){
            String nom = base.get(i)[0];
            String cont = base.get(i)[1];
            
            Usuario us = new Usuario(nom,cont);

            creado = true;
            registrarUsuario(us);
            creado = false;
        }
    }

    public boolean registrarUsuario(Usuario user){
        boolean flag = false;
        if(validarUsuario(user)){
            users.add(user);
            flag = true;
            if(!creado){
                File file;
                try {
                    Files.createDirectories(user.getDirAs());
                } catch (IOException e) {
                    System.err.println("Error al crear la carpeta: " + e.getMessage());
                }
                escritor.escribir(user.toString());
            }

        }
        return flag;
    }

    private boolean validarUsuario(Usuario user){
        boolean flag = false;
        if(buscarUsuario(user.getNombre(),user.getContra()) == null){
            flag = true;
        }
        return flag;
    }

    public Usuario buscarUsuario(String nombUser, String pass){
        Usuario us = null;
        for(Usuario usuario : users){
            if(usuario.getNombre().equals(nombUser)){
                if(usuario.getContra().equals(pass)){
                    us = usuario;
                    break;
                }
            }
        }
        return us;
    }

    public void eliminarUsuario(Usuario usuario) { 
        File carpetaUsuario = new File(usuario.toStringDirAs());
        if (carpetaUsuario.exists()) {
            borrarDirectorio(carpetaUsuario);
        }
        users.remove(usuario);
        escritor.eliminar(usuario.toString());
    }

    private void borrarDirectorio(File directorio) {
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    borrarDirectorio(archivo);
                }
            }
        }
        directorio.delete();
    }
}