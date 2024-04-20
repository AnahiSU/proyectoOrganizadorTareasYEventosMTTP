package estDatos.estDatNoLin;
import estDatos.estDatLin.*;
/**
 * Write a description of class ArbolBB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArbolBB<T extends Comparable<T>>
{
    private ArbolBB<T> left;
    private ArbolBB<T> right;
    T root;
    public ArbolBB(){
        left = null;
        right = null;
        root = null;
    }

    public ArbolBB(T data){
        left = new ArbolBB();
        right = new ArbolBB();
        this.root = data;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insertData(T data){
        if(!isEmpty()){
            if(data.compareTo(root)<=0){
                left.insertData(data);
            }else{
                right.insertData(data);
            }
        }else{
            root = data;
            left = new ArbolBB();
            right = new ArbolBB();
        }
    }

    public T delete(T data){
        T res = null;
        if(!isEmpty()){
            if(root.equals(data)){
                if(isLeaf()){
                    res = root;
                    root = null;
                    left = right = null;
                }
            }else{
                if(root.compareTo(data)<0){
                    right.delete(data);
                }else{
                    left.delete(data);
                }
            }
        }
        return res;
    }

    private boolean isLeaf(){
        return left.isEmpty() && right.isEmpty();
    }

    private ArbolBB<T> descLeft(){
        ArbolBB<T> res;
        if(left.isEmpty()){
            res = this;
        }else{
            res = left.descLeft();
        }
        return res;
    }

    private ArbolBB<T> descRight(){
        ArbolBB<T> res;
        if(right.isEmpty()){
            res = this;
        }else{
            res = right.descLeft();
        }
        return res;
    }
    
    public ListaSE<T> inOrder(){
        ListaSE<T> list = new ListaSE<T>();
        if(!isEmpty()){
            list.insertAll(left.inOrder());
            list.insert(root);
            list.insertAll(right.inOrder());
        }
        return list;
    }

    /*public T deleteNoMatterLeaf(T data){
    T res = null;
    if(!isEmpty()){
    if(data.compareTo(root)<0){
    res = left.deleteNoMatterLeaf(data);
    }else{
    if(data.compareTo(root)>0){
    res = right.deleteNoMatterLeaf(data);
    }else{
    // el dato es igual a la root
    if(isLeaf()){
    res = root;
    left = null;
    right = null;
    root = null;
    }else{
    if(left.isEmpty() && !right.isEmpty()){
    res = root;
    root = right.root;
    right = right.right;
    left = right.left;
    }else{
    if(!left.isEmpty() && right.isEmpty()){
    res = root;
    root = left.root;
    right = left.right;
    left = left.left;
    }else{
    res = root;
    ArbolBB<T> descL = descLeft();
    root = descL.root;
    descL.root = null;
    }
    }
    }
    }
    }
    }
    return res;
    }*/
    public T eliminar(T dato){
        T elDato = null;
        ArbolBB<T> arb;
        if(isEmpty())
            elDato = null;
        else
        if(dato.compareTo(root)<0)
            elDato = left.eliminar(dato);
        else
        if(dato.compareTo(root) > 0)
            elDato = right.eliminar(dato);
        else {
            elDato = root;
            switch(estadoroot()){
                case 1: // hoja
                    root = null;
                    left = null;
                    right = null;
                    break;
                case 2: // tiene solo HI
                    root = left.root;
                    right = left.right;
                    left = left.left;
                    break;
                case 3: // tiene solo HD
                    root = right.root;
                    left = right.left;
                    right = right.right;
                    break;
                case 4: // tiene los dos
                    arb = right.descIzq();
                    root = arb.root;
                    arb.eliminar(root);
            }
        }
        return elDato;
    }

    public void deleteAll(){
        root = null;
        left = null;
        right = null;
    }

    private int estadoroot(){
        int estado;
        if(left.isEmpty())
            if(right.isEmpty()) estado = 1;
            else              estado = 3;
        else
        if(right.isEmpty()) estado = 2;
        else              estado = 4;
        return estado;
    }

    private ArbolBB<T> descIzq(){
        ArbolBB<T> arb;
        if(left.isEmpty())
            arb = this;
        else
            arb = left.descIzq();
        return arb;
    }
}