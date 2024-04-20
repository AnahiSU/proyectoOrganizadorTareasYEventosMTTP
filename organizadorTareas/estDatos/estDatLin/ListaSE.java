package estDatos.estDatLin;

public class ListaSE<T> implements List<T>
{
    private T ini;
    private ListaSE<T> next;

    public ListaSE(){
        ini = null;
        next = null;
    }

    public ListaSE(T dato){
        ini = dato;
        next = new ListaSE();
    }

    public boolean isEmpty(){
        return ini == null;
    }

    public void insert(T data){
        if(isEmpty()){
            ini = data;
            next = new ListaSE();
        }else{
            if(!next.isEmpty()){
                next.insert(data);
            }else{
                next = new ListaSE<T>(data);
            }
        }
    }

    public T delete(int pos){
        T data;
        if(!isEmpty()){
            if(pos == 0){
                data = ini;
                ini = next.ini;
                next = next.next;
            }else{
                data = next.delete(pos-1);
            }
        }else{
            data = null;
        }
        return data;
    }// elimina por posición y recorre los elementos

    public T get(int pos){
        T data;
        if(pos == 0){
            data = ini;
        }else{
            data = next.get(pos-1);
        }
        return data;
    }

    public boolean search(T data){
        boolean res = false;
        if(!isEmpty()){
            if(ini == data){
                res = true;
            }else{
                res = next.search(data);
            }
        }
        return res;
    } // busca por dato

    public int length(){
        int count = 0;
        if(!isEmpty()){
            if(next.isEmpty()){
                count = 1;
            }else{
                count = 1 + next.length();
            }
        }
        return count;
    }

    public void deleteAll(T data){

    } // elimina todos los datos dato de la lista
    public void clear(){
        if(!isEmpty()){
            if(next.isEmpty()){
                ini = null;
                next = null;
            }else{
                ini = null;
                next.clear();
            }
        }
    } //vacia la lista

    public void replace(int pos, T data){
        if(pos == 0){
            ini = data;
        }else{
            next.replace(pos-1,data);
        }
    } //reemplaza sobre la misma lista

    public String toString(){
        String res = "";
        if(!isEmpty()){
            res = "{"+ toString(res) + "}";
        }
        return res;
    }// para imprimir a lista
    private String toString(String aux){
        if(isEmpty()){
            aux = "";
        }else{
            aux = "" + ini + "," + next.toString(aux); 
        }

        return aux;
    }

    /* genera una nueva lista desde posInic hasta posFin inclusivo
    Si no existe posFin, elimina hasta la última posición. Si no existe posIni, devuelve una lista vacía */
    public List<T> subList(int posIni, int posFin){
        return null;
    }

    /*compara si la lista es igual a another en cuanto a contenido y orden*/
    public boolean equals(List<T> another){
        return false;
    }

    /* intercambia los datos de pos1 a pos 2 y viceversa*/
    public void swap(int pos1, int pos2){

    }

    /* inserta todos los elementos de la otra lista al final*/
    public void insertAll(ListaSE <T> another){
        if(!isEmpty()){
            if(next.isEmpty()){
                next = another;
            }else{
                next.insertAll(another);
            }
        }else{
             ini = another.ini; 
             next = another.next;
        }

    } 

    /*inserta una lista desde la posición indicada y recorre el
    resto de elementos, si pos no existe, no hace nada
    {1,2,3} ; list = {4,5,6} 
    0 1 2    
    insertListByPos(list, 3) -> {1,2,3,4,5,6}
    0 1 2 3 4 5*/
    public void insertListByPos(ListaSE<T> another, int pos){
        int listLength = another.length();
        if(pos <= length()){
            if(pos == 1){
                ListaSE <T> aux = next;
                another.insertAll(aux);
                next = another;
            }else{
                if(pos == 0){
                    ListaSE <T> aux = this;
                    ini = another.ini;
                    next = another.next;
                    insertAll(aux);
                }
                next.insertListByPos(another,pos-1);
            }
        }
    } 

    /* indice del primer dato encontrado*/
    public int indexOf(T data){
        int pos = -1;
        if(search(data)){
            if(ini != data && !next.isEmpty()){
                pos = 1 + next.indexOf(data);
            }else{
                if(ini == data){
                    pos = 1;
                }
            }
        }
        return pos-1;
    } 
    
    public void insertByPos(T data,int pos){
        if(!isEmpty()){
            while(pos>0){
                next.insertByPos(data,pos-1);
            }
            ListaSE<T> aux = new ListaSE<T>();
            aux.ini = ini;
            aux.next = next;
            next = aux;
            ini = data;
        }else{
            ini = data;
            next = new ListaSE<T>();
        }
    } 

    public void reverse(){

    } //voltea toda la lista sobre sí misma
    public void deleteFrom(int pos){

    } // borra los datos desde pos hasta el final
}
