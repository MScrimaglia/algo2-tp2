package aed;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<Handle> heap;
    private ArrayList<Handle> array;

    public class Handle {
        int indiceHeap;
        int indiceArray;
        T valor;
        
        public Handle(int indiceHeap, int indiceArray, T valor) {   // O(1)
            this.indiceHeap = indiceHeap;
            this.indiceArray = indiceArray;
            this.valor = valor;
        }

        public int indiceHeap() {       // O(1)
            return indiceHeap;
        }

        public int indiceArray() {      // O(1)
            return indiceArray;
        }

        public T valor() {            // O(1) 
            return valor;
        }
    }

    // O(1)
    public MaxHeap() {
        this.heap = new ArrayList<>();
        this.array = new ArrayList<>();     
    }

    // Constructor que toma array de elementos de tipo T, complejidad O(n log n)
    public MaxHeap(T[] a) {     // O(n) + O(n) = O(n)
        heap = new ArrayList<>(a.length);
        array = new ArrayList<>(a.length);

        // Creo heap y array de handles, O(n)
        for (int i = 0; i < a.length; i++) {
            Handle newHandle = new Handle(i, i, a[i]);
            heap.add(newHandle);
            array.add(newHandle);
        }

        // Ordeno el heap (heapify), O(n log n)
        for(int i = (heap.size() - 1) / 2; i >= 0; i--) {
            siftDown(heap.get(i));
        }
    }

    // O(1)
    public int cantidadElementos() {
        return heap.size();
    }

    // O(1)
    public ArrayList<Handle> getArray() {
        return this.array;
    }

    // O(1) // Preguntar si está bien devolver un handle
    public Handle getHandleArray(int idArray) {
        return this.array.get(idArray);
    }
    
    // O(log n)
    public void bajar(Handle h) {
        siftDown(h); // O(log n)
    }

    // O(log n)
    public void subir(Handle h) {
        siftUp(h);
    }

    // No se va a usar
    /*  // O(log n)
        public Handle insertar(T valor) {
        Handle newHandle = new Handle(heap.size(), valor);
        heap.add(newHandle);
        siftUp(newHandle);
        return newHandle;
    } */

    // O(log n) cambia el valor de un elemento en el heap y lo reordena
    public void modificarPorId(int id, T valor) {
        T valorAnterior = array.get(id).valor();
        int hIndex = array.get(id).indiceHeap();

        heap.get(hIndex).valor = valor;

        if (valor.compareTo(valorAnterior) > 0) {
            siftUp(heap.get(hIndex));   // O(log n)
        }
        else if (valor.compareTo(valorAnterior) < 0) {
            siftDown(heap.get(hIndex)); // O(log n)
        }

    }

    // O(1)
    public T obtenerPorId(int id) {
        return array.get(id - 1).valor();
    }

    // O(1)
    public T maximo() {
        if (cantidadElementos() == 0) {
            return null;
        }
        return heap.get(0).valor();         // O(1)
    }

    // O(log n)
    public T extraerRaiz(){
        if (cantidadElementos() == 0) {
            return null;
        }
        Handle raiz = heap.get(0);
        Handle ultimoElemento = heap.get(heap.size() - 1);

        intercambiar(raiz.indiceHeap, ultimoElemento.indiceHeap());
        heap.remove(heap.size() - 1); // O(1) al eliminar el último elemento
        if (cantidadElementos() > 0) {
            siftDown(heap.get(0)); // O(log n)
        }

        array.remove(raiz.indiceArray); // O(n)
        for (int i = raiz.indiceArray; i < array.size(); i++) { // O(n)
            array.get(i).indiceArray = i;
        }

        return raiz.valor();
    }
    
    // O(1)
    private Handle hijoIzquierdo(Handle h){
        int indiceHijoIzq = h.indiceHeap * 2 + 1;
        if (indiceHijoIzq >= cantidadElementos()) {
            return null;
        }
        return heap.get(indiceHijoIzq);
    }
    
    // O(1)
    private Handle hijoDerecho(Handle h){
        int indiceHijoDer =h.indiceHeap * 2+ 2;
        if (indiceHijoDer >= cantidadElementos()) {
            return null;
        }
        return heap.get(indiceHijoDer);
    }
    
    // O(1)
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
    
    // O(1)
    private Handle padre(Handle h){
        int indicePadre = (int) (h.indiceHeap - 1) / 2;
        if (h.indiceHeap == 0 || indicePadre >= cantidadElementos()) {
            return null;
        }
        return heap.get(indicePadre);
    }

    // O(log n)
    private void siftUp(Handle h) {
        if (padre(h) != null && h.valor.compareTo(padre(h).valor) > 0) {
            intercambiar(h.indiceHeap(), padre(h).indiceHeap());
            siftUp(h);
        }
    }

    // O(log n)
    private void siftDown(Handle h) {
        if (hijoMayor(h) != null && h.valor.compareTo(hijoMayor(h).valor) < 0) {
            intercambiar(h.indiceHeap,hijoMayor(h).indiceHeap);
            siftDown(h); 
        }
    }

    // O(1)
    private void intercambiar(int i, int j) {
        Handle iTemp = heap.get(i);
        Handle jTemp = heap.get(j);
        iTemp.indiceHeap = j;
        jTemp.indiceHeap = i;
        array.set(iTemp.indiceArray, iTemp);
        array.set(jTemp.indiceArray, jTemp);
        heap.set(i, jTemp);
        heap.set(j, iTemp);
    }
}