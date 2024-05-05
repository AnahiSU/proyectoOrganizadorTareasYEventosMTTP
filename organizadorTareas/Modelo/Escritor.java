package Modelo;

import java.io.*;
import java.util.ArrayList;

/**
 * La clase Escritor proporciona mÃ©todos para escribir en un archivo, leer desde un archivo,
 * limpiar un archivo, leer la Ãºltima lÃ­nea de un archivo, buscar por un valor especÃ­fico en un archivo,
 * actualizar valores en un archivo y eliminar lÃ­neas que contienen un valor especÃ­fico en un archivo.
 */
public class Escritor {
    private String archivo;

    /**
     * Constructor de la clase Escritor.
     *
     * @param archivo Nombre del archivo en el que se realizarÃ¡n las operaciones de escritura y lectura.
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
     * Escribe una lÃ­nea en el archivo.
     *
     * @param contenido Contenido que se escribirÃ¡ en el archivo.
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
     * @return ArrayList que contiene todas las lÃ­neas del archivo, donde cada lÃ­nea estÃ¡ representada como un arreglo de cadenas.
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
     * Lee la Ãºltima lÃ­nea del archivo.
     *
     * @return La Ãºltima lÃ­nea del archivo como una cadena.
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
     * Busca una lÃ­nea que contenga el valor especificado en el archivo.
     *
     * @param valorBuscado Valor que se estÃ¡ buscando en el archivo.
     * @return La primera lÃ­nea que contiene el valor buscado como una cadena completa.
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
     * Actualiza todas las lÃ­neas que contienen el valor antiguo con el nuevo valor especificado.
     *
     * @param valorAntiguo Valor que se desea reemplazar en el archivo.
     * @param nuevoValor   Nuevo valor que se utilizarÃ¡ para reemplazar el valor antiguo.
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
                lineasActualizadas.add(linea); // Agrega la lÃ­nea actualizada
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar en el archivo: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (String linea : lineasActualizadas) {
                writer.write(linea);
                writer.newLine(); // Agregar una nueva lÃ­nea despuÃ©s de cada lÃ­nea escrita
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Elimina todas las lÃ­neas que contienen el valor especificado en el archivo.
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
                writer.newLine(); // Agregar una nueva lÃ­nea despuÃ©s de cada lÃ­nea escrita
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Cuenta el nÃºmero de lÃ­neas en el archivo.
     *
     * @return NÃºmero total de lÃ­neas en el archivo.
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