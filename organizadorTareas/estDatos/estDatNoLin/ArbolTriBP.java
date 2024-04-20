package estDatos.estDatNoLin;
import estDatos.estDatLin.*;
public class ArbolTriBP<T extends Comparable<T>>
{
    private ArbolTriBP<T> left,right,med;
    private T root;

    public ArbolTriBP(){
        left=right=med = null;
        root = null;
    }

    public ArbolTriBP(T data){
        root = data;
        left = new ArbolTriBP();
        right = new ArbolTriBP();
        med = new ArbolTriBP();
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(T data){
        Cola<ArbolTriBP<T>> cola = new Cola<ArbolTriBP<T>>();
        cola.offer(this);
        insert(data,cola);
    }

    private void insert(T data, Cola<ArbolTriBP<T>> cola){
        ArbolTriBP<T> aux = cola.poll();
        if(aux.left.isEmpty() || aux.right.isEmpty() || aux.med.isEmpty()){
            if(aux.left.isEmpty() && aux.right.isEmpty() && aux.med.isEmpty()){
                aux.left = new ArbolTriBP(data);
            }else{
                if(aux.med.isEmpty()){
                    if(data.compareTo(aux.left.root)<0){
                        aux.med =  new ArbolTriBP(aux.left.root);
                        aux.left =  new ArbolTriBP(data);
                    }else{
                        aux.med = new ArbolTriBP(data);
                    }
                }else{
                    if(data.compareTo(aux.left.root)<0){
                        aux.right = new ArbolTriBP(aux.med.root);
                        aux.med = new ArbolTriBP(aux.left.root) ;
                        aux.left = new ArbolTriBP(data) ;
                    }else{
                        if(data.compareTo(aux.med.root)<0){
                            aux.right = new ArbolTriBP(aux.med.root);
                            aux.med = new ArbolTriBP(data) ;
                        }else{
                            aux.right = new ArbolTriBP(data);
                        }
                    }
                }
            }
        }else{
            cola.offer(left);
            cola.offer(med);
            cola.offer(right);
            insert(data,cola);
        }
    }
}
