package estDatos.estDatLin;

/**
 * Write a description of class ListaDE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaDE<T> implements List<T>
{
    private NodeDE<T> ini;
    private NodeDE<T> fin;

    public ListaDE(){
        ini = null;
        fin = null;
    }

    public ListaDE(T data){
        ini = new NodeDE<T>(data);
        fin = ini;
    }

    public boolean isEmpty(){
        return ini == null;
    }

    public void insert(T data){
        NodeDE<T> newNode = new NodeDE<T>(data);
        if(isEmpty()){
            fin = ini = newNode;
        }else{
            fin.setSuc(newNode);
            newNode.setAnt(fin);
            fin = newNode;
        }

    }

    /*elimina por posición y recorre los elementos*/
    public T delete(int pos){
        T res = null;
        NodeDE<T> aux = ini;
        if(!isEmpty()){
            while(pos > 0){
                if(aux.getSuc()!=null){
                    aux = aux.getSuc();
                    pos = pos-1; 
                }else{
                    pos = -1;
                }
            } // aux se vuelve el nodo a eliminar
            if(pos <= 0){
                res = aux.getData();
                aux.getSuc().setAnt(aux.getAnt());
                aux.getAnt().setSuc(aux.getSuc());   
            }else{
                if(aux.getSuc()==null){ 
                    // si estamos en el primer nodo
                    res = ini.getData();
                    fin = ini = null;
                }else{
                    res = ini.getData();
                    ini = ini.getSuc();
                }
            }
        }

        return res;
    }

    public void delete(T data){
        NodeDE<T> aux = ini;
        if(!isEmpty()){
            while(!aux.getData().equals(data) && aux.getSuc()!=null){
                aux = aux.getSuc();
            }  
            if(aux.getSuc()!=null){
                aux.getSuc().setAnt(aux.getAnt());
                aux.getAnt().setSuc(aux.getSuc());  
            }else{
                if(aux.getData().equals(data)){
                    if(aux.getAnt()!=null){ //ultimo nodo
                        aux.getAnt().setSuc(null);  
                    }else{
                        fin = ini = null; // primer nodo
                    }
                }
            }
        }

    }

    public T get(int pos){
        NodeDE<T> aux = ini;
        while(pos != 0){
            aux = aux.getSuc();
            pos = pos-1;
        } 
        T res = aux.getData();
        return res;
    }

    public boolean search(T data){
        boolean res = false;
        NodeDE<T> aux = ini;
        if(!isEmpty()){
            while(!aux.getData().equals(data) && aux.getSuc() != null){
                aux = aux.getSuc();
            }
            if(aux.getSuc() != null){
                res = true;
            }else{
                if(aux.getData().equals(data)){
                    res = true;
                }
            }
        }

        return res;
    } // busca por dato

    public int length(){
        int res = 0;
        NodeDE<T> aux = ini;
        if(!isEmpty()){
            while(aux != null){
                res += 1;
                aux = aux.getSuc();
            }
        }
        return res;
    }

    public void deleteAll(T data){
        NodeDE<T> aux = ini;
        while(aux.getSuc()!= null){
            if(aux.getData().equals(data)){
                delete(data);
            }
            aux = aux.getSuc();
        }
        if(aux.getData().equals(data)){
            delete(data);
        } 
    }

    public void clear(){
        ini = null;
        fin = null;
    }

    public void replace(int pos, T data){
        NodeDE<T> aux = ini;
        if(!isEmpty()){
            while(aux.getSuc()!=null && pos!=0){
                aux = aux.getSuc();
                pos = pos-1;
            } 
            if(aux.getSuc()!=null && pos== 0){
                aux.setData(data);  
            }
        }

    } 

    public String toString(){
        String res = "{}";
        if(!isEmpty()){
            NodeDE<T> aux = ini;
            res = "{";
            while(aux.getSuc()!=null){
                res = res + aux.getData() + " <-> "  ;
                aux = aux.getSuc();
            }
            res = res + aux.getData() + "}";
        }
        return res;
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

    /*inserta una lista desde la posición indicada y recorre el
    resto de elementos, si pos no existe, no hace nada
    {1,2,3} ; list = {4,5,6} 
    0 1 2    
    insertListByPos(list, 3) -> {1,2,3,4,5,6}
    0 1 2 3 4 5*/
    public void insertListByPos(List<T> another, int pos){

    }  

    public int indexOf(T data){
        int pos = -1;
        NodeDE<T> aux = ini;
        if(!isEmpty() && search(data)){
            pos = 0;
            while(aux.getSuc()!=null){
                if(!aux.getData().equals(data)){
                    pos = pos+1;
                    aux = aux.getSuc();
                }else{
                    aux = fin;
                }
            }
        }
        return pos;
    } // indice del primer dato encontrado

    public void reverse(){

    } //voltea toda la lista sobre sí misma

    public void insertByPos(T data, int pos){
        NodeDE<T> aux = ini;

        while(pos>0){
            aux = aux.getSuc();
            pos--;
        }
        NodeDE<T> newNode = new NodeDE<T>(data);
        if(isEmpty()){
            ini = fin = new NodeDE<T>(data);
        }else{
            if(aux.equals(ini)){
                aux.setSuc(ini);
                ini = aux;
            }else{
                NodeDE<T> aux2 = aux.getAnt();
                newNode.setSuc(aux);
                aux2.setSuc(newNode);
                newNode.setAnt(aux2);
            }
        }

    }
    public void deleteFrom(int pos){
        NodeDE<T> aux = ini;
        while(pos>0){
            aux = aux.getSuc();
            pos -= 1;
        }
        aux.getAnt().setSuc(null);
    } // borra los datos desde pos hasta el final
}
