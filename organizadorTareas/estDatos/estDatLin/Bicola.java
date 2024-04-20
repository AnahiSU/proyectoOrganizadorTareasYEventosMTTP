package estDatos.estDatLin;

public class Bicola<T> extends Cola<T>
{
    public Bicola(){
        super();

    }

    public Bicola(T data){
        super(data);
    }

    public T exitEnd(){
        T data = null;
        if(!isEmpty()){
            if(front.getSuc() == null){
                data = front.getData();
                front = null;
                end = null;
            }else{
                data = end.getData();
                end = end.getAnt();
                end.setSuc(null);
            }

        }
        return data;
    }

    public void insertFront(T data){
        if(!isEmpty()){
            NodeDE<T> newNode= new NodeDE<T>(data);
            newNode.setSuc(front);
            front.setAnt(newNode);
            front = newNode;
        }else{
            end = front = new NodeDE<T>(data);
        }
    }
}
