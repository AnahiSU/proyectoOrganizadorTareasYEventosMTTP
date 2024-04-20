package estDatos.estDatLin;

public class Pila<T>
{
    private T top;
    private Pila<T> base;

    public Pila(){
        top = null;
        base = null;
    }

    public Pila(T data){
        top = data;
        base = new Pila<T>();
    }

    public Pila(T top, Pila<T> base){
        this.top = top;
        this.base = base;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public void push(T data){
        if(!isEmpty()){
            base = new Pila<T>(top,base);
            top = data;
        }else{
            top = data;
            base = new Pila<T>();
        }
    }

    public T pop(){
        T res = top;
        if(!isEmpty()){
            top = base.top;
            base = base.base;
        }
        return res;
    }

    public T top(){
        return top;
    }
}
