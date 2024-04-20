package estDatos.estDatLin;


/**
 * Write a description of class NodeTE here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NodeTE<T>
{
    private T data;
    private NodeTE<T> ant;
    private NodeTE<T> suc;
    private NodeTE<T> hijo;
    
    public NodeTE(T data){
        this.data = data;
        ant = null;
        suc = null;
        hijo = null;
    }
    
    public T getData(){
        return data;
    }
    
    public NodeTE<T> getSuc(){
        return suc;
    }
    
    public NodeTE<T> getAnt(){
        return ant;
    }
    
    public NodeTE<T> getHijo(){
        return hijo;
    }
    
    public void setSuc(NodeTE<T> n){
        suc = n;
    }
    
    public void setAnt(NodeTE<T> n){
        ant = n;
    }
    
    public void setHijo(NodeTE<T> n){
        hijo = n;
    }
}
