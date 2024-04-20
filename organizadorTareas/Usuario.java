import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.File;

public class Usuario{
    private String contraseña;
    private String nombre;
    private Path dirAs;
    private File file;

    public Usuario(String name, String contra){
        this.contraseña = contra;
        this.nombre = name;
        this.dirAs = Paths.get("Usuarios", nombre);
        System.out.println(dirAs);
        try {
            Files.createDirectories(dirAs);
        } catch (IOException e) {
            System.err.println("Error al crear la carpeta: " + e.getMessage());
        }
    }

    public String getContra(){
        return contraseña;
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
}