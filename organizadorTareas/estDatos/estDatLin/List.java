package estDatos.estDatLin;


/**
 * Write a description of class Lista here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface List<T>
{
    boolean isEmpty();
    void insert(T data);
    T delete(int pos); // elimina por posición y recorre los elementos
    T get(int pos);
    boolean search(T data); // busca por dato
    int length();
    void deleteAll(T data); // elimina todos los datos dato de la lista
    void clear();
    void replace(int pos, T dato); 
    String toString(); // para imprimir a lista
    
    List<T> subList(int posIni, int posFin); // genera una nueva lista desde posInic hasta posFin inclusivo
    /* Si no existe posFin, elimina hasta la última posición. Si no existe posIni, devuelve una lista vacía */
    
    boolean equals(List<T> another);// compara si la lista es igual a another en cuanto a contenido y orden
    void swap(int pos1, int pos2);// intercambia los datos de pos1 a pos 2 y viceversa
    //void insertAll(List<T> another); // inserta todos los elementos de la another lista al final
    /*
    void insertListByPos(List<T> another, int pos); 
    inserta una lista desde la posición indicada y recorre el
    resto de elementos, si pos no existe, no hace nada
    {1,2,3} ; list = {4,5,6} 
     0 1 2    
    insertListByPos(list, 3) -> {1,2,3,4,5,6}
                                 0 1 2 3 4 5*/
                                 
    int indexOf(T dato); // indice del primer dato encontrado
    void reverse(); //voltea toda la lista sobre sí misma
    void deleteFrom(int pos); // borra los datos desde pos hasta el final
} 
