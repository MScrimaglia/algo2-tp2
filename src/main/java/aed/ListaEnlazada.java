package aed;

public class ListaEnlazada<T extends Comparable<T>> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;

    public class Nodo {
        Nodo anterior;
        Nodo siguiente;
        int indiceHeap;
        T elemento;
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
    }

    public T primero() {
        return this.primero.elemento;
    }

    public T ultimo() {
        return this.ultimo.elemento;
    }

    public Nodo primerNodo() {
        return this.primero;
    }

    public Nodo ultimoNodo() {
        return this.ultimo;
    }

    public int longitud() {
        int res = 0;
        Nodo nodoActual = primero;
        while (nodoActual != null) {
            res++;
            nodoActual = nodoActual.siguiente;
        }
        return res;
    }

    public void agregarAdelante(T elem, int indiceHeap) {
        Nodo nuevoNodo = new Nodo();
        nuevoNodo.elemento = elem;
        nuevoNodo.indiceHeap = indiceHeap;
        if (primero != null) {
            primero.anterior = nuevoNodo;
        }
        nuevoNodo.anterior = null;
        nuevoNodo.siguiente = primero;
        primero = nuevoNodo;
        if (ultimo == null) {
            ultimo = nuevoNodo;
        }
    }

    public void agregarAdelante(T elem) {
        agregarAdelante(elem, -1); // -1 para indicar que no tiene índice de heap
    }

    public void agregarAtras(T elem, int indiceHeap) {
        Nodo nuevoNodo = new Nodo();
        nuevoNodo.elemento = elem;
        nuevoNodo.indiceHeap = indiceHeap;
        if (primero != null) {
            ultimo.siguiente = nuevoNodo;
        }
        nuevoNodo.anterior = ultimo;
        nuevoNodo.siguiente = null;
        ultimo = nuevoNodo;
        if (primero == null) {
            primero = nuevoNodo;
        }
    }

    public void agregarAtras(T elem) {
        agregarAtras(elem, -1); // -1 para indicar que no tiene índice de heap
    }

    public T obtener(int i) {
        Nodo nodoActual = primero;
        while (i > 0) {
            nodoActual = nodoActual.siguiente;
            i--;
        }
        return nodoActual.elemento;
    }

    public Nodo obtenerNodo(int i) {
        Nodo nodoActual = primero;
        while (i > 0) {
            nodoActual = nodoActual.siguiente;
            i--;
        }
        return nodoActual;
    }

    public void eliminar(int i) {
        Nodo nodoActual = primero;
        while (i > 0) {
            nodoActual = nodoActual.siguiente;
            i--;
        }
        if (nodoActual == primero) {
            primero = nodoActual.siguiente;
        }
        if (nodoActual == ultimo) {
            ultimo = nodoActual.anterior;
        }
        if (nodoActual.anterior != null) {
            nodoActual.anterior.siguiente = nodoActual.siguiente;
        }
        if (nodoActual.siguiente != null) {
            nodoActual.siguiente.anterior = nodoActual.anterior;
        }
        
    }

    // O(1)
    public void eliminarPorNodo(Nodo nodo) {
        if (this.longitud() == 1) {
            primero = null;
            ultimo = null;
        }
        else if (nodo == primero) {
            primero = nodo.siguiente;
            primero.anterior = null;
        }
        else if (nodo == ultimo) {
            ultimo = nodo.anterior;
            ultimo.siguiente = null;
        }
        else if (nodo.anterior != null && nodo.siguiente != null) {
            nodo.anterior.siguiente = nodo.siguiente;
            nodo.siguiente.anterior = nodo.anterior;
        }
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo nodoActual = primero;
        while (indice > 0) {
            nodoActual = nodoActual.siguiente;
            indice--;
        }
        nodoActual.elemento = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo nuevoPrimerNodo = new Nodo();
        if (lista.longitud() == 0) {
            this.primero = null;
            this.ultimo = null;
            this.longitud = 0;
            return;
        }
        nuevoPrimerNodo.elemento = lista.primero.elemento;
        this.primero = nuevoPrimerNodo;
        Nodo nodoActual = lista.primero.siguiente;
        Nodo ultimoNuevoNodo = this.primero;
        while (nodoActual != null) {
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.elemento = nodoActual.elemento;
            nuevoNodo.anterior = ultimoNuevoNodo;
            ultimoNuevoNodo.siguiente = nuevoNodo;
            nodoActual = nodoActual.siguiente;
            ultimoNuevoNodo = nuevoNodo;
        }
        this.ultimo = ultimoNuevoNodo;
        this.longitud = lista.longitud();
    }
    
    @Override
    public String toString() {
        String res = "[";
        for (int i = 0; i < this.longitud(); i++) {
            res += this.obtener(i).toString();
            if (i < this.longitud() - 1) {
                res += ", ";
            }
        }
        res += "]";
        return res;
    }

    private class ListaIterador implements Iterador<T> {
    	Nodo nodoActual;
        Nodo nodoAnterior = null;

        public boolean haySiguiente() {
	        return nodoActual != null;
        }
        
        public boolean hayAnterior() {
	        return nodoAnterior != null;
        }

        public T siguiente() {
            T res = nodoActual.elemento;
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
            return res;
        }
        

        public T anterior() {
            nodoActual = nodoAnterior;
            nodoAnterior = nodoActual.anterior;
            return nodoActual.elemento;
        }
    }

    public Iterador<T> iterador() {
	    ListaIterador iterador = new ListaIterador();
        iterador.nodoActual = primero;
        return iterador;
    }

}