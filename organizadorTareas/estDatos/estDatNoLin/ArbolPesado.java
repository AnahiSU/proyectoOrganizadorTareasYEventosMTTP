package estDatos.estDatNoLin;
import estDatos.estDatLin.*;
public class ArbolPesado<T extends Comparable<T>>
{
    private T root;
    private ArbolPesado<T> right;
    private ArbolPesado<T> left;

    public ArbolPesado(){
        root = null;
        right = null;
        left = null;
    }

    public ArbolPesado( T data){
        root = data;
        right = new ArbolPesado();
        left = new ArbolPesado();
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void insert(T data){
        Cola<ArbolPesado<T>> cola = new Cola<ArbolPesado<T>>();
        cola.offer(this);
        insert(cola,data);
        reubicar(data);
    }

    private void insert(Cola<ArbolPesado<T>> cola, T data){
        ArbolPesado<T> aux = cola.poll();
        if(aux.isEmpty()){
            aux.root = data;
            aux.right = new ArbolPesado();
            aux.left = new ArbolPesado();
        }else{
            cola.offer(aux.left);
            cola.offer(aux.right);
            insert(cola,data);
        }
    }

    private void reubicar(T data){
        Pila<ArbolPesado<T>> way = getWay(data);
        reubicar(data, way);

    }
    private void reubicar(T data,Pila<ArbolPesado<T>> way){
        ArbolPesado<T> aux = way.pop();
        if(!way.isEmpty()){
            if(aux.root.compareTo(way.top().root)>0){
                T aux2 = aux.root;
                aux.root = way.top().root;
                way.top().root = aux2;
                reubicar(data,way);
            }
        }

    }
    
    public Pila<ArbolPesado<T>> getWay(T data){
        Pila<ArbolPesado<T>> way = new Pila<ArbolPesado<T>>();
        getWay(data,way);
        return way;
    }
    private void getWay(T data, Pila<ArbolPesado<T>> way){
        //recolectar el camino en arboles binarios
        if(!isEmpty()){
            if(!root.equals(data)){
                left.getWay(data,way);
                if(!way.isEmpty()){
                    way.push(this);    
                }else{
                    if(way.isEmpty()){
                        right.getWay(data,way); 
                        if(!way.isEmpty()){
                            way.push(this);    
                        }
                    }else{
                        way.push(this);
                    }    
                }
            }else{
                way.push(this);
            }
        }
    }
    
    
}
