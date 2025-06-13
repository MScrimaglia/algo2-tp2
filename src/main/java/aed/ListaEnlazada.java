package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;


    private class Nodo {
        private Nodo siguiente;
        private T valor;

        // Constructor de Nodo
        public Nodo(T valor) {
            this.siguiente = null;
            this.valor = valor;
        }

        public T getValor() {
            return this.valor;
        }

        public Nodo getSiguiente() {
            return this.siguiente;
        }


        public void setValor(T valor) {
            this.valor = valor;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }



    }

    public ListaEnlazada() {
        this.longitud = 0;
        this.primero = null;
        this.ultimo = null;
    }

    public int longitud() {
        return this.longitud;
    }

    public T primero() {
        return this.primero.valor;
    }

    public T ultimo() {
        return this.ultimo.valor;
    }

    public void agregar(T elem) {
       
        Nodo nuevoNodo = new Nodo(elem);
        
        if (longitud == 0) {

            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;

        } else {

            this.ultimo.setSiguiente(nuevoNodo);
            this.ultimo = nuevoNodo;

        }

        this.longitud++;

    }

    public T obtener(int i) {
        Nodo nodoActual = this.primero;

        for (int j = 0; j < i; j++) {
            nodoActual = nodoActual.getSiguiente();
        };

        return nodoActual.getValor();
    }

    public void eliminar(int i) {

        Nodo nodoActual = this.primero;
        Nodo nodoAnterior = null;
        for (int j = 0; j < i; j++) {
           
            nodoAnterior = nodoActual; // al final de este loop, nodoAnterior apunta al nodo anterior al que quiero eliminar
            nodoActual = nodoActual.getSiguiente();
        
        }

        if (this.longitud == 1) {
            this.primero = null;
            this.ultimo = null;
            this.longitud--;
            return;
        }

        if (nodoActual == this.primero) {

            this.primero = nodoActual.getSiguiente();
            
        } else if (nodoActual == this.ultimo) {

            this.ultimo = nodoAnterior;             // chequeo los casos borde en los que el nodo es el primero o el último
            this.ultimo.setSiguiente(null);

        } else {

            nodoAnterior.setSiguiente(nodoActual.getSiguiente());

        }

        this.longitud--;

    }

    public void modificarPosicion(int indice, T elem) {
        Nodo nodoActual = this.primero;
        for (int j = 0; j < indice; j++) {
            nodoActual = nodoActual.getSiguiente();
        }

        nodoActual.setValor(elem);
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        this();
        for (int i = 0; i < lista.longitud(); i++) {
            this.agregar(lista.obtener(i));
        }
    }
    
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.longitud; i++){
            
            if(i != this.longitud - 1) {
                res = res + obtener(i) + ", ";
            } else {
                res = res + obtener(i); // caso en el que i es el último elemento
            }
            
        }
        return "[" + res + "]";
    }

    private class ListaIterador implements Iterador<T> {
    	private Nodo actual;

        public ListaIterador() {
            this.actual = primero;
        }

        public boolean haySiguiente() {         // el iterador apunta al principio, a la izquierda de cada Nodo y me dice si a la derecha (en el Nodo actual) hay algo, no hay que pensarlo como si fuese el Nodo actual.
            return this.actual != null;
        }
        
        // public boolean hayAnterior() {
	    //     if (longitud() == 0) {              // caso lista vacía
        //         return false;
        //     } else if (!haySiguiente()) {       // caso en el que el puntero está justo delante del último elemento no nulo
        //         return true;
        //     } else {
        //     return this.actual.anterior != null;
        //     }
        // }

        public T siguiente() {
	        T res = this.actual.getValor();
            this.actual = this.actual.getSiguiente();   // primero devuelve el valor actual y después avanza en la lista
            return res;
        }
        

        // public T anterior() {
	    //     if (longitud() == 0) {
        //         return null;
        //     } else if (!haySiguiente()) {
        //         this.actual = ultimo;
        //         return ultimo.getValor();
        //     } else {
        //         this.actual = this.actual.getAnterior();    // primero retrocede y después devuelve el valor 
        //         T res = this.actual.getValor();
        //         return res;
        //     }
        // }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
