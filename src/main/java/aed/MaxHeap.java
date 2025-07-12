package aed;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<ListaEnlazada<T>.Nodo> heap;
    private ListaEnlazada<T> lista;

    // O(1)
    public MaxHeap() {
        this.heap = new ArrayList<>();
        this.lista = new ListaEnlazada<>();
    }

    // Constructor que toma array de elementos de tipo T
    // O(n)
    public MaxHeap(T[] a) {
        heap = new ArrayList<>(a.length);
        lista = new ListaEnlazada<>();

        // Creo heap y lista de handles, O(n)
        for (int i = 0; i < a.length; i++) {
            lista.agregarAtras(a[i], i);
            heap.add(lista.ultimoNodo());
        }

        // Ordeno el heap (heapify), O(n)
        for(int i = (heap.size() - 1) / 2; i >= 0; i--) {
            siftDown(heap.get(i));
        }
    }

    // O(1)
    public int cantidadElementos() {
        return heap.size();
    }

    // O(1)
    public ListaEnlazada<T> getLista() {
        return this.lista;
    }
    
    // O(log n)
    public void bajar(ListaEnlazada.Nodo n) {
        siftDown(n);
    }

    // O(log n)
    public void subir(ListaEnlazada.Nodo n) {
        siftUp(n);
    }

    // O(n)
    public T obtenerPorId(int id) {
        return lista.obtener(id - 1);
    }

    // O(1)
    public T maximo() {
        if (cantidadElementos() == 0) {
            return null;
        }
        return heap.get(0).getElemento();
    }

    // O(log n)
    public T extraerRaiz(){
        if (cantidadElementos() == 0) {
            return null;
        }
        ListaEnlazada<T>.Nodo raiz = heap.get(0);
        ListaEnlazada<T>.Nodo ultimoElemento = heap.get(heap.size() - 1);

        intercambiar(raiz.getIndiceHeap(), ultimoElemento.getIndiceHeap());   // O(1)
        heap.remove(heap.size() - 1); // O(1) al eliminar el último elemento
        if (cantidadElementos() > 0) {
            siftDown(heap.get(0));  // O(log n)
        }

        lista.eliminarPorNodo(raiz);    // O(1)

        return raiz.getElemento();
    }
    
    private ListaEnlazada.Nodo hijoIzquierdo(ListaEnlazada.Nodo n){
        int indiceHijoIzq = n.getIndiceHeap() * 2 + 1;
        if (indiceHijoIzq >= cantidadElementos()) {
            return null;
        }
        return heap.get(indiceHijoIzq);
    }
    
    private ListaEnlazada.Nodo hijoDerecho(ListaEnlazada.Nodo n){
        int indiceHijoDer = n.getIndiceHeap() * 2 + 2;
        if (indiceHijoDer >= cantidadElementos()) {
            return null;
        }
        return heap.get(indiceHijoDer);
    }
    
    private ListaEnlazada.Nodo hijoMayor(ListaEnlazada.Nodo n) {
        if (hijoIzquierdo(n) == null) {
            return null;
        }
        if (hijoDerecho(n) == null) {
            return hijoIzquierdo(n);
        }
        if (hijoIzquierdo(n).getElemento().compareTo(hijoDerecho(n).getElemento()) < 0) {
            return hijoDerecho(n);
        }
        return hijoIzquierdo(n);
    }
    
    private ListaEnlazada.Nodo padre(ListaEnlazada.Nodo n){
        int indicePadre = (int) (n.getIndiceHeap() - 1) / 2;
        if (n.getIndiceHeap() == 0 || indicePadre >= cantidadElementos()) {
            return null;
        }
        return heap.get(indicePadre);
    }

    // O(log n)
    private void siftUp(ListaEnlazada.Nodo n) {
        if (padre(n) != null && n.getElemento().compareTo(padre(n).getElemento()) > 0) {
            intercambiar(n.getIndiceHeap(), padre(n).getIndiceHeap());
            siftUp(n);
        }
    }

    // O(log n)
    private void siftDown(ListaEnlazada.Nodo n) {
        if (hijoMayor(n) != null && n.getElemento().compareTo(hijoMayor(n).getElemento()) < 0) {
            intercambiar(n.getIndiceHeap(), hijoMayor(n).getIndiceHeap());
            siftDown(n);
        }
    }

    // O(1)
    private void intercambiar(int i, int j) {
        ListaEnlazada.Nodo iTemp = heap.get(i);
        ListaEnlazada.Nodo jTemp = heap.get(j);
        iTemp.setIndiceHeap(j);
        jTemp.setIndiceHeap(i);
        heap.set(i, jTemp);
        heap.set(j, iTemp);
    }
}
