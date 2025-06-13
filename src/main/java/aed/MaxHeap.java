package aed;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    ArrayList<Handle> heap;

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
        heap = new ArrayList<>();
    }

    public void insertar(T valor) {
        Handle newHandle = new Handle(heap.size(), valor);
        heap.add(newHandle);
        siftUp(newHandle);
    }

    public T maximo() {
        return heap.get(0).valor();
    }

    private void siftDown(Handle h) {
        if (hijoMayor(h) != null && h.valor.compareTo(hijoMayor(h).valor) < 0) {
            intercambiar(h.indice,hijoMayor(h).indice);
            siftDown(h);
        }
    }
    
    private Handle hijoIzquierdo(Handle h){
        int indiceHijoIzq = h.indice * 2 + 1;
        return heap.get(indiceHijoIzq);
    }
    
    private Handle hijoDerecho(Handle h){
        int indiceHijoDer =h.indice * 2+ 2;
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
