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
        
        public Nodo(T elemento, int indiceHeap) {
            this.anterior = null;
            this.siguiente = null;
            this.elemento = elemento;
            this.indiceHeap = indiceHeap;
        }

        public Nodo getAnterior() {
            return this.anterior;
        }

        public Nodo getSiguiente() {
            return this.siguiente;
        }

        public int getIndiceHeap() {
            return this.indiceHeap;
        }

        public T getElemento() {
            return this.elemento;
        }

        public void setAnterior(Nodo anterior) {
            this.anterior = anterior;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }

        public void setIndiceHeap(int indiceHeap) {
            this.indiceHeap = indiceHeap;
        }

        public void setElemento(T elemento) {
            this.elemento = elemento;
        }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
    }

    public T primero() {
        return this.primero.getElemento();
    }

    public T ultimo() {
        return this.ultimo.getElemento();
    }

    public Nodo primerNodo() {
        return this.primero;
    }

    public Nodo ultimoNodo() {
        return this.ultimo;
    }

    public int longitud() {
        return this.longitud;
    }

    public void agregarAdelante(T elem, int indiceHeap) {
        Nodo nuevoNodo = new Nodo(elem, indiceHeap);
        if (primero != null) {
            primero.setAnterior(nuevoNodo);
        }
        nuevoNodo.setAnterior(null);
        nuevoNodo.setSiguiente(primero);
        primero = nuevoNodo;
        if (ultimo == null) {
            ultimo = nuevoNodo;
        }
        longitud++;
    }

    public void agregarAdelante(T elem) {
        agregarAdelante(elem, -1); // -1 para indicar que no tiene índice de heap
        longitud++;
    }

    public void agregarAtras(T elem, int indiceHeap) {
        Nodo nuevoNodo = new Nodo(elem, indiceHeap);

        if (primero != null) {
            ultimo.setSiguiente(nuevoNodo);
        }
        nuevoNodo.setAnterior(ultimo);
        nuevoNodo.setSiguiente(null);
        ultimo = nuevoNodo;
        if (primero == null) {
            primero = nuevoNodo;
        }
        longitud++;
    }

    public void agregarAtras(T elem) {
        agregarAtras(elem, -1); // -1 para indicar que no tiene índice de heap
        longitud++;
    }

    public T obtener(int i) {
        Nodo nodoActual = primero;
        while (i > 0) {
            nodoActual = nodoActual.getSiguiente();
            i--;
        }
        return nodoActual.getElemento();
    }

    public Nodo obtenerNodo(int i) {
        Nodo nodoActual = primero;
        while (i > 0) {
            nodoActual = nodoActual.getSiguiente();
            i--;
        }
        return nodoActual;
    }

    public void eliminar(int i) {
        Nodo nodoActual = primero;
        while (i > 0) {
            nodoActual = nodoActual.getSiguiente();
            i--;
        }
        if (nodoActual == primero) {
            primero = nodoActual.getSiguiente();
        }
        if (nodoActual == ultimo) {
            ultimo = nodoActual.getAnterior();
        }
        if (nodoActual.getAnterior() != null) {
            nodoActual.getAnterior().setSiguiente(nodoActual.getSiguiente());
        }
        if (nodoActual.getSiguiente() != null) {
            nodoActual.getSiguiente().setAnterior(nodoActual.getAnterior());
        }
        longitud--;
    }

    // O(1)
    public void eliminarPorNodo(Nodo nodo) {
        if (this.longitud() == 1) {
            primero = null;
            ultimo = null;
        }
        else if (nodo == primero) {
            primero = nodo.getSiguiente();
            primero.setAnterior(null);
        }
        else if (nodo == ultimo) {
            ultimo = nodo.getAnterior();
            ultimo.setSiguiente(null);
        }
        else if (nodo.getAnterior() != null && nodo.getSiguiente() != null) {
            nodo.getAnterior().setSiguiente(nodo.getSiguiente());
            nodo.getSiguiente().setAnterior(nodo.getAnterior());
        }
        else {
            return;
        }
        longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo nodoActual = primero;
        while (indice > 0) {
            nodoActual = nodoActual.getSiguiente();
            indice--;
        }
        nodoActual.setElemento(elem);
    }

    // Constructor por copia, no lo utilizamos.
    // public ListaEnlazada(ListaEnlazada<T> lista) {
    //     Nodo nuevoPrimerNodo = new Nodo();
    //     if (lista.longitud() == 0) {
    //         this.primero = null;
    //         this.ultimo = null;
    //         this.longitud = 0;
    //         return;
    //     }
    //     nuevoPrimerNodo.elemento = lista.primero.elemento;
    //     this.primero = nuevoPrimerNodo;
    //     Nodo nodoActual = lista.primero.siguiente;
    //     Nodo ultimoNuevoNodo = this.primero;
    //     while (nodoActual != null) {
    //         Nodo nuevoNodo = new Nodo();
    //         nuevoNodo.elemento = nodoActual.elemento;
    //         nuevoNodo.anterior = ultimoNuevoNodo;
    //         ultimoNuevoNodo.siguiente = nuevoNodo;
    //         nodoActual = nodoActual.siguiente;
    //         ultimoNuevoNodo = nuevoNodo;
    //     }
    //     this.ultimo = ultimoNuevoNodo;
    //     this.longitud = lista.longitud();
    // }
    
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
            T res = nodoActual.getElemento();
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
            return res;
        }
        

        public T anterior() {
            nodoActual = nodoAnterior;
            nodoAnterior = nodoActual.getAnterior();
            return nodoActual.getElemento();
        }
    }

    public Iterador<T> iterador() {
	    ListaIterador iterador = new ListaIterador();
        iterador.nodoActual = primero;
        return iterador;
    }

}
