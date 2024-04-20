package estDatos.estDatLin;

public class Cola<T>
{
    protected NodeDE<T> front;
    protected NodeDE<T> end;

    public Cola(){
        front = null;
        end = null;
    }

    public Cola(T data){
        front = new NodeDE<T>(data);
        end = front;
    }

    public boolean isEmpty(){
        return front == null;
    }

    public void offer(T data){
        if(isEmpty()){
            end = front = new NodeDE<T>(data);
        }else{
            NodeDE<T> newNode = new NodeDE<T>(data);
            newNode.setAnt(end);
            end.setSuc(newNode);
            end = newNode;
        }
    }

    public T poll(){
        T res = null;
        if(!isEmpty()){
            if(front.getSuc() == null){
                res = front.getData();
                front = null;
                end = null;
            }else{
                res = front.getData();
                front = front.getSuc();
                front.setAnt(null);
            }
        }
        return res;
    }
    
    public T see(){
        T res = null;
        if(!isEmpty()){
            res = front.getData();
        }
        return res;
    }
}
