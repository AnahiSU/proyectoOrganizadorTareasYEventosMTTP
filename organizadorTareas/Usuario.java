import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.File;

public class Usuario{
    private String contrasena;
    private String nombre;
    private Path dirAs;
    private File file;

    public Usuario(String name, String contra){
        this.contrasena = contra;
        this.nombre = name;
        this.dirAs = Paths.get("Usuarios", nombre);
    }

    public String getContra(){
        return contrasena;
    }

    public String getNombre(){
        return nombre;
    }

    public Path getDirAs(){
        return dirAs;
    }
    
    public String toStringDirAs(){
        return dirAs.toString();
    }
    
    public String toString(){
        return nombre+"|"+contrasena;
    }
}