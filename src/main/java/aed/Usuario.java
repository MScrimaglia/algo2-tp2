package aed;

public class Usuario implements Comparable<Usuario>{

    private int id;           // ID del usuario
    private int saldo;        // monto del usuario
    private ListaEnlazada<Usuario>.Nodo nodoEnHeap;
    
    //Ponemos su índice en el heap?
    public Usuario(int id) {
        this.id = id;
        this.saldo = 0;
    }

    public int getId() {
        return id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public ListaEnlazada<Usuario>.Nodo getNodoEnHeap() {
        return nodoEnHeap;
    }

    public void setNodoEnHeap(ListaEnlazada<Usuario>.Nodo n) {
        this.nodoEnHeap = n;
    }

    @Override
    public int compareTo(Usuario otro) {
        if (this.saldo != otro.saldo) {
            return this.saldo - otro.saldo;
        } 
        return otro.id - this.id;
    }
}