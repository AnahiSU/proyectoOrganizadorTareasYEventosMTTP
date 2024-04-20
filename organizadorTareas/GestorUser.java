import java.util.ArrayList;
import java.io.File;

public class GestorUser{
    private ArrayList<Usuario> users;
    
    public GestorUser(){
        users = new ArrayList<>();
    }
    
    public void registrarUsuario(Usuario user){
        users.add(user);
    }

    public void eliminarUsuario(Usuario usuario) {
        File carpetaUsuario = new File(usuario.toStringDirAs());
        if (carpetaUsuario.exists()) {
            borrarDirectorio(carpetaUsuario);
        }
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

    public Usuario buscarUsuario(String str){
        return null;
    }

}