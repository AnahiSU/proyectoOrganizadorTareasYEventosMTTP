import java.io.*;
import java.util.ArrayList;

/**
 * La clase Escritor proporciona métodos para escribir en un archivo, leer desde un archivo,
 * limpiar un archivo, leer la última línea de un archivo, buscar por un valor específico en un archivo,
 * actualizar valores en un archivo y eliminar líneas que contienen un valor específico en un archivo.
 */
public class Escritor {
    private String archivo;

    /**
     * Constructor de la clase Escritor.
     *
     * @param archivo Nombre del archivo en el que se realizarán las operaciones de escritura y lectura.
     */
    public Escritor(String archivo) {
        this.archivo = archivo;
        // Verificar si el archivo existe, si no existe, crearlo
        File file = new File(archivo);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    /**
     * Escribe una línea en el archivo.
     *
     * @param contenido Contenido que se escribirá en el archivo.
     */
    public void escribir(String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(contenido);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee todo el contenido del archivo y lo devuelve como una lista de matrices de cadenas.
     *
     * @return ArrayList que contiene todas las líneas del archivo, donde cada línea está representada como un arreglo de cadenas.
     */
    public ArrayList<String[]> leerTodo() {
        ArrayList<String[]> contenido = new ArrayList<String[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.add(linea.split("\\|"));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return contenido;
    }

    /**
     * Elimina todo el contenido del archivo.
     */
    public void limpiar() {
        try (PrintWriter writer = new PrintWriter(archivo)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            System.err.println("Error al limpiar el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee la última línea del archivo.
     *
     * @return La última línea del archivo como una cadena.
     */
    public String leerUltimaLinea(){
        String ultimaLinea = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                ultimaLinea = linea;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return ultimaLinea;
    }

    /**
     * Busca una línea que contenga el valor especificado en el archivo.
     *
     * @param valorBuscado Valor que se está buscando en el archivo.
     * @return La primera línea que contiene el valor buscado como una cadena completa.
     */
    public String buscarPorValor(String valorBuscado) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                for (String parte : partes) {
                    if (parte.equals(valorBuscado)) {
                        return linea;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al buscar en el archivo: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza todas las líneas que contienen el valor antiguo con el nuevo valor especificado.
     *
     * @param valorAntiguo Valor que se desea reemplazar en el archivo.
     * @param nuevoValor   Nuevo valor que se utilizará para reemplazar el valor antiguo.
     */
    public void actualizar(String valorAntiguo, String nuevoValor) {
        ArrayList<String> lineasActualizadas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains(valorAntiguo)) {
                    while (linea.contains(valorAntiguo)) {
                        linea = linea.replaceFirst(valorAntiguo, nuevoValor);
                    }
                }
                lineasActualizadas.add(linea); // Agrega la línea actualizada
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar en el archivo: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineasActualizadas) {
                writer.write(linea);
                writer.newLine(); // Agregar una nueva línea después de cada línea escrita
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Elimina todas las líneas que contienen el valor especificado en el archivo.
     *
     * @param valor Valor que se desea eliminar del archivo.
     */
    public void eliminar(String valor) {
        ArrayList<String> lineasActualizadas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.contains(valor)) {
                    lineasActualizadas.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar en el archivo: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineasActualizadas) {
                writer.write(linea);
                writer.newLine(); // Agregar una nueva línea después de cada línea escrita
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Cuenta el número de líneas en el archivo.
     *
     * @return Número total de líneas en el archivo.
     */
    public int contarElementos() {
        int contador = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            while (reader.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            System.err.println("Error al contar elementos en el archivo: " + e.getMessage());
        }
        return contador;
    }
}