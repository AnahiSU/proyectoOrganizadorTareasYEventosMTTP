package estDatos.estDatLin;

/**
 * Write a description of class ListaSC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaSC<T> implements List<T>
{
    private NodoSE<T> ini;

    public ListaSC(){
        ini = null;
    }

    public boolean isEmpty(){
        return ini == null;
    }

    public void insert(T data){
        if(!isEmpty()){
            NodoSE<T> aux = ini;
            while(ini.getSuc()!=ini){
                aux = aux.getSuc();
            }
            aux.setSuc(new NodoSE(data));
            aux.getSuc().setSuc(ini);
        }else{
            ini = new NodoSE<T>(data);
            ini.setSuc(ini);
        }
    }

    public T delete(int pos){
        return null;
    }
    // elimina por posición y recorre los elementos
    public T get(int pos){
        return null;
    }

    public boolean search(T data){
        return false;
    } // busca por dato
    public int length(){
        return 0;
    }

    public void deleteAll(T data){

    } // elimina todos los datos dato de la lista
    public void clear(){

    }

    public void replace(int pos, T dato){

    } 

    public String toString(){
        return "";
    }// para imprimir a lista

    public List<T> subList(int posIni, int posFin){
        return null;
    }
    // genera una nueva lista desde posInic hasta posFin inclusivo
    /* Si no existe posFin, elimina hasta la última posición. Si no existe posIni, devuelve una lista vacía */

    public boolean equals(List<T> another){
        return false;
    }
    // compara si la lista es igual a another en cuanto a contenido y orden
    public void swap(int pos1, int pos2){

    }// intercambia los datos de pos1 a pos 2 y viceversa
    public void insertAll(List<T> another){

    } // inserta todos los elementos de la another lista al final

    public void insertListByPos(List<T> another, int pos){

    } 

    /*inserta una lista desde la posición indicada y recorre el
    resto de elementos, si pos no existe, no hace nada
    {1,2,3} ; list = {4,5,6} 
    0 1 2    
    insertListByPos(list, 3) -> {1,2,3,4,5,6}
    0 1 2 3 4 5*/

    public int indexOf(T dato){
        return 0;
    } // indice del primer dato encontrado
    public void reverse(){

    } //voltea toda la lista sobre sí misma
    public void deleteFrom(int pos){

    } // borra los datos desde pos hasta el final
}
