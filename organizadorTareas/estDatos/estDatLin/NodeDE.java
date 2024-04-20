package estDatos.estDatLin;

public class NodeDE<T>
{
    private NodeDE<T> ant;
    private T data;
    private NodeDE<T> suc;
    
    public NodeDE(T data){
        this.data = data;
        ant = null;
        suc = null;
    }
    
    public T getData(){
        return data;
    }
    
    public NodeDE<T> getSuc(){
        return suc;
    }
    
    public NodeDE<T> getAnt(){
        return ant;
    }
    
    public void setSuc(NodeDE<T> n){
        suc = n;
    }
    
    public void setAnt(NodeDE<T> n){
        ant = n;
    }
    public void setData(T d){
        data = d;
    }
}
