package aed;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    ArrayList<Handle> heap;
    ArrayList<Handle> array;

    public class Handle {
        int indice;
        T valor;
        
        public Handle(int indice, T valor) {
            this.indice = indice;
            this.valor = valor;
        }

        public int indice() {
            return indice;
        }

        public T valor() {
            return valor;
        }
    }

    public MaxHeap() {
        this.heap = new ArrayList<>();
        this.array = new ArrayList<>();
    }

    public MaxHeap(Handle[] a) {
        heap = new ArrayList<>();

        for(int i=0; i<a.length ; i++){
            
        }


        for(int i = a.length-1/2; i >= 0; i--) {
            siftUp(a[i]);
        }
    }

    public int cantidadElementos() {
        return heap.size();
    }

    public Handle insertar(T valor) {
        Handle newHandle = new Handle(heap.size(), valor);
        heap.add(newHandle);
        siftUp(newHandle);
        return newHandle;
    }

    public T maximo() {
        if (cantidadElementos() == 0) {
            return null;
        }
        return heap.get(0).valor();
    }

    public Handle extraerRaiz(){
        if (cantidadElementos() == 0) {
            return null;
        }
        Handle raiz = heap.get(0);
        Handle ultimoElemento = heap.get(heap.size() - 1);
        intercambiar(raiz.indice, ultimoElemento.indice());
        heap.remove(heap.size() - 1);
        if (cantidadElementos() > 0) {
            siftDown(heap.get(0));
        }
        return raiz;
    }

    private void siftDown(Handle h) {
        if (hijoMayor(h) != null && h.valor.compareTo(hijoMayor(h).valor) < 0) {
            intercambiar(h.indice,hijoMayor(h).indice);
            siftDown(h);
        }
    }
    
    private Handle hijoIzquierdo(Handle h){
        int indiceHijoIzq = h.indice * 2 + 1;
        if (indiceHijoIzq >= cantidadElementos()) {
            return null;
        }
        return heap.get(indiceHijoIzq);
    }
    
    private Handle hijoDerecho(Handle h){
        int indiceHijoDer =h.indice * 2+ 2;
        if (indiceHijoDer >= cantidadElementos()) {
            return null;
        }
        return heap.get(indiceHijoDer);
    }
    
    private Handle hijoMayor(Handle h) {
        if (hijoIzquierdo(h) == null) {
            return null;
        }
        if (hijoDerecho(h) == null) {
            return hijoIzquierdo(h);
        }
        if (hijoIzquierdo(h).valor().compareTo(hijoDerecho(h).valor()) < 0) {
            return hijoDerecho(h);
        }
        return hijoIzquierdo(h);
    }
    
    private Handle padre(Handle h){
        int indicePadre = (int) (h.indice - 1) / 2;
        if (h.indice == 0 || indicePadre >= cantidadElementos()) {
            return null;
        }
        return heap.get(indicePadre);
    }

    private void siftUp(Handle h) {
        if (padre(h) != null && h.valor.compareTo(padre(h).valor) > 0) {
            intercambiar(h.indice(), padre(h).indice());
            siftUp(h);
        }
    }

    private void intercambiar(int i, int j) {
        Handle iTemp = heap.get(i);
        Handle jTemp = heap.get(j);
        iTemp.indice = j;
        jTemp.indice = i;
        heap.set(i, jTemp);
        heap.set(j, iTemp);
    }
}
