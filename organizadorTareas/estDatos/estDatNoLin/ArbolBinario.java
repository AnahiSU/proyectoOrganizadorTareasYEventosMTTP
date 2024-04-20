package estDatos.estDatNoLin;
import estDatos.estDatLin.*;

public class ArbolBinario<T>
{
    private ArbolBinario<T> left,right;
    private T root;

    public ArbolBinario(){
        left = null;
        right = null;
        root = null;
    }

    public ArbolBinario(T data){
        left = new ArbolBinario<T>();
        right = new ArbolBinario<T>();
        root = data;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean insertRoute(ListaSE<T> route, T dato){
        boolean res = false;
        if(!isEmpty()){
            if(route.get(0).equals(root)){
                if(route.isEmpty()){
                    res = true;
                    left = new ArbolBinario<T>(dato);
                }else{
                    route.delete(0);
                    res = left.insertRoute(route, dato);
                    if(res == false){
                        route.delete(0);
                        res = right.insertRoute(route,dato);
                    } 
                }
            }
        }
        return res;
    }

    public void insertByAmplitude(T dato){
        Cola<ArbolBinario<T>> cola = new Cola<ArbolBinario<T>>();
        cola.offer(this);
        insertByAmplitude(dato,cola);
    }

    private void insertByAmplitude(T dato, Cola<ArbolBinario<T>> cola){
        ArbolBinario<T> arbAux = cola.poll();
        if(arbAux.isEmpty()){
            arbAux.root = dato;
            arbAux.left = new ArbolBinario();
            arbAux.right = new ArbolBinario();
        }else{
            cola.offer(arbAux.left);
            cola.offer(arbAux.right);
            insertByAmplitude(dato,cola);
        }
    }

    private boolean isLeaf(){
        return left.isEmpty() && right.isEmpty();
    }

    public T deleteIfIsLeaf(T dato){
        T res = null;
        ArbolBinario<T> tree = search(dato);
        if(tree != null){
            if(tree.isLeaf()){
                res = tree.root;
                tree.root = null;
                tree.left = null;
                tree.right = null;
            }
        }
        return res;
    }

    public T deleteNoMatterWhat(T dato){
        T res = null;
        ArbolBinario<T> tree = search(dato);
        if(tree != null){
            res = tree.root;
            tree.root = null;
            tree.left = null;
            tree.right = null;    
        }
        return res;
    }

    private ArbolBinario<T> search(T dato){
        ArbolBinario<T> res = null;
        if(!isEmpty()){
            if(root.equals(dato)){
                res = this;
            }else{
                res = left.search(dato);
                if(res == null){
                    res = right.search(dato);
                }
            }
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

    public ListaSE<T> preOrder(){
        ListaSE<T> list = new ListaSE<T>();
        if(!isEmpty()){
            list.insert(root);
            list.insertAll(left.preOrder());
            list.insertAll(right.preOrder());
        }
        return list;
    }

    public ListaSE<T> posOrder(){
        ListaSE<T> list = new ListaSE<T>();
        if(!isEmpty()){
            list.insertAll(left.posOrder());
            list.insertAll(right.posOrder());
            list.insert(root);
        }
        return list;
    }

    public int height(){
        int res = -1;
        if(!isEmpty()){
            res = 1 + Math.max(left.height(),right.height());
        }
        return res;
    }

    public void flip(){
        if(!isEmpty()){
            ArbolBinario <T> aux = left;
            left = right;
            right = aux;
            left.flip();
            right.flip();
        }
    }

    public ListaDE<ArbolBinario<T>> getWay(T data){
        ListaDE<ArbolBinario<T>> res = new ListaDE<ArbolBinario<T>>();
        boolean flag = false;
        getWay(data,res);
        return res;
    }

    public void getWay(T data, ListaDE<ArbolBinario<T>>way){
        //recolectar el camino en arboles binarios
        if(!isEmpty()){
            if(!root.equals(data)){
                left.getWay(data,way);
                if(!way.isEmpty()){
                    way.insertByPos(this,0);    
                }else{
                    if(way.isEmpty()){
                        right.getWay(data,way); 
                        if(!way.isEmpty()){
                            way.insertByPos(this,0);    
                        }
                    }else{
                        way.insertByPos(this,0);
                    }    
                }
            }else{
                way.insertByPos(this,0);
            }
        }
    }

    public void rotate(T data){
        ListaDE<ArbolBinario<T>> way = this.getWay(data);
        ListaDE<T> roots = new ListaDE<T>();
        int l = way.length();
        for(int i = 0;i<way.length();i++){
            roots.insert(way.get(i).root);
        }
        roots.insertByPos(roots.get(0),0);
        roots.replace(1,roots.get(l));
        roots.delete(l);
        while(!way.isEmpty()){
            way.get(0).root = roots.get(0);
            way.delete(0);
            roots.delete(0);
        }
    }

    public void rotate(T data, ListaDE<ArbolBinario<T>> arb, T aux){
        
    }
    
    public boolean isBalanced(){
        boolean res = false;
        if(!isEmpty()){
            int leftH = left.height();
            int rightH = right.height();
            if(Math.abs(leftH-rightH)<=1){
                res = left.isBalanced() && right.isBalanced();
            }
        }else{
            res = true;
        }
        return res;
    }
    
    public Bicola<T> getLevel(int level){
        
        Bicola<T> levelRoots = new Bicola<T>();
        Cola<ArbolBinario<T>> cola = new Cola<ArbolBinario<T>>(this);
        levelRoots = getLevel(level,cola);
        
        return levelRoots;
    }
                    
    private Bicola<T> getLevel(int level, Cola<ArbolBinario<T>> cola){
        ArbolBinario<T> arbAux = cola.poll();
        Bicola<T> levelRoots = new Bicola<T>();
        Bicola<T> levelRootsLeft = new Bicola<T>();
        Bicola<T> levelRootsRight = new Bicola<T>();
        
        if(level==0){
            levelRoots.offer(arbAux.root);
        }else{
            levelRootsLeft = left.getLevel(level-1);
            levelRootsRight = right.getLevel(level-1);
        }
        
        while(!levelRootsLeft.isEmpty()){
            T aux = levelRootsLeft.poll();
            if(aux!=null){
                levelRoots.offer(aux);
            }
        }
        while(!levelRootsRight.isEmpty()){
            T aux = levelRootsRight.poll();
            if(aux!=null){
                levelRoots.offer(aux);
            }
        }
        return levelRoots;
    }
    
    public ListaSE<T> specialOrder(){
        ListaSE<T> order = new ListaSE<T>();
        int height = height();
        while(height>-1){
            Bicola <T> levelRoots = getLevel(height);
            while(!levelRoots.isEmpty()){
                order.insert(levelRoots.poll());
                if(!levelRoots.isEmpty()){
                    order.insert(levelRoots.exitEnd());
                }
            }
            height = height-1;
        }
        return order;
    }
    
    
}
