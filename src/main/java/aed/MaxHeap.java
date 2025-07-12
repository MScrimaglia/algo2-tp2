package aed;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<ListaEnlazada<T>.Nodo> heap;
    private ListaEnlazada<T> lista;

    /* public class Handle {
        int indiceHeap;
        int indiceLista;
        T valor;
        
        // O(1)
        public Handle(int indiceHeap, int indiceLista, T valor) {
            this.indiceHeap = indiceHeap;
            this.indiceLista = indiceLista;
            this.valor = valor;
        }

        // O(1)
        public int indiceHeap() {
            return indiceHeap;
        }

        // O(1)
        public int indiceLista() {
            return indiceLista;
        }

        // O(1)
        public T valor() {
            return valor;
        }
    } */

    /* public class HeapHandle {
        private ListaEnlazada.Nodo nodo;
        private T valor;

        public HeapHandle(ListaEnlazada.Nodo nodo, T valor) {
            this.nodo = nodo;
        }

        // O(1)
        public ListaEnlazada.Nodo nodo() {
            return nodo;
        }

        // O(1)
        public T valor() {
            return valor;
        }
    } */

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

    // O(n)
    // No se usa
    /* public Handle getHandleLista(int idLista) {
        return this.lista.obtener(idLista);
    } */
    
    // O(log n)
    public void bajar(ListaEnlazada.Nodo n) {
        siftDown(n);
    }

    // O(log n)
    public void subir(ListaEnlazada.Nodo n) {
        siftUp(n);
    }

    // No se va a usar
    /*  // O(log n)
        public Handle insertar(T valor) {
        Handle newHandle = new Handle(heap.size(), valor);
        heap.add(newHandle);
        siftUp(newHandle);
        return newHandle;
    } */

    // O(log n)
    // No se usa (ya no valido con lista enlazada)
    /* public void modificarPorId(int id, T valor) {
        T valorAnterior = array.get(id).valor();
        int hIndex = array.get(id).indiceHeap();

        heap.get(hIndex).valor = valor;

        if (valor.compareTo(valorAnterior) > 0) {
            siftUp(heap.get(hIndex));
        }
        else if (valor.compareTo(valorAnterior) < 0) {
            siftDown(heap.get(hIndex));
        }

    } */

    // O(n)
    public T obtenerPorId(int id) {
        return lista.obtener(id - 1);
    }

    // O(1)
    public T maximo() {
        if (cantidadElementos() == 0) {
            return null;
        }
        return heap.get(0).elemento;
    }

    // O(log n)
    public T extraerRaiz(){
        if (cantidadElementos() == 0) {
            return null;
        }
        ListaEnlazada<T>.Nodo raiz = heap.get(0);
        ListaEnlazada<T>.Nodo ultimoElemento = heap.get(heap.size() - 1);

        intercambiar(raiz.indiceHeap, ultimoElemento.indiceHeap);   // O(1)
        heap.remove(heap.size() - 1); // O(1) al eliminar el último elemento
        if (cantidadElementos() > 0) {
            siftDown(heap.get(0));  // O(log n)
        }

        lista.eliminarPorNodo(raiz);    // O(1)

        return raiz.elemento;
    }
    
    private ListaEnlazada.Nodo hijoIzquierdo(ListaEnlazada.Nodo n){
        int indiceHijoIzq = n.indiceHeap * 2 + 1;
        if (indiceHijoIzq >= cantidadElementos()) {
            return null;
        }
        return heap.get(indiceHijoIzq);
    }
    
    private ListaEnlazada.Nodo hijoDerecho(ListaEnlazada.Nodo n){
        int indiceHijoDer = n.indiceHeap * 2 + 2;
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
        if (hijoIzquierdo(n).elemento.compareTo(hijoDerecho(n).elemento) < 0) {
            return hijoDerecho(n);
        }
        return hijoIzquierdo(n);
    }
    
    private ListaEnlazada.Nodo padre(ListaEnlazada.Nodo n){
        int indicePadre = (int) (n.indiceHeap - 1) / 2;
        if (n.indiceHeap == 0 || indicePadre >= cantidadElementos()) {
            return null;
        }
        return heap.get(indicePadre);
    }

    // O(log n)
    private void siftUp(ListaEnlazada.Nodo n) {
        if (padre(n) != null && n.elemento.compareTo(padre(n).elemento) > 0) {
            intercambiar(n.indiceHeap, padre(n).indiceHeap);
            siftUp(n);
        }
    }

    // O(log n)
    private void siftDown(ListaEnlazada.Nodo n) {
        if (hijoMayor(n) != null && n.elemento.compareTo(hijoMayor(n).elemento) < 0) {
            intercambiar(n.indiceHeap,hijoMayor(n).indiceHeap);
            siftDown(n);
        }
    }

    // O(1)
    private void intercambiar(int i, int j) {
        ListaEnlazada.Nodo iTemp = heap.get(i);
        ListaEnlazada.Nodo jTemp = heap.get(j);
        iTemp.indiceHeap = j;
        jTemp.indiceHeap = i;
        heap.set(i, jTemp);
        heap.set(j, iTemp);
    }
}