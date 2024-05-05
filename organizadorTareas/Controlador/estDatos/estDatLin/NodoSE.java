package  Controlador.estDatos.estDatLin;


/**
 * Write a description of class NodoSe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NodoSE<T>
{
    private NodoSE suc;
    private T data;
    public NodoSE(){
        suc = null;
        data = null;
    }
    public NodoSE(T data){
        suc = new NodoSE();
        data = data;
    }
    
    public T getData(){
        return data;
    }
    
    public NodoSE<T> getSuc(){
        return suc;
    }
    public void setData(T d){
        data = d;
    }
    
    public void setSuc(NodoSE<T> s){
        suc = s;
    }
}
