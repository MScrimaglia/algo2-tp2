package aed;

import java.util.ArrayList;

public class Bloque implements Comparable<Bloque> {
    private int id;
    private int montoTotal;    // Estos atributos son para que montoMedioUltimoBloque() sea O(1), no cuenta las de creación
    private int cantTransacciones;
    private MaxHeap<Transaccion> transacciones;

    public Bloque(Transaccion[] trans, int id) {
        this.id = id;
        
        this.montoTotal = 0;
        this.cantTransacciones = trans.length;
        for (Transaccion t: trans) {
            this.montoTotal += t.monto();   
        }
        if (this.id < 3000) {
            this.montoTotal--;   // si hay menos de 3000 bloques resto el monto de la transacción de creación (1)
            this.cantTransacciones--;
        }

        this.transacciones = new MaxHeap<>(trans);
    }

    public int getId() {
        return id;
    }

    public int cantTransacciones() {
        return this.cantTransacciones;
    }

    public int montoTotal() {
        return this.montoTotal;
    }

    public void sumarMontoTotal(int monto) {
        this.montoTotal += monto;
    }

    public void restarMontoTotal(int monto) {
        this.montoTotal -= monto;
    }

    public int montoMedioBloque() {
        if (this.cantTransacciones <= 0){
            return 0;
        }
        return this.montoTotal / this.cantTransacciones();
    }

    public Transaccion[] getTransaccionesPorID() {
        ListaEnlazada<Transaccion> t = transacciones.getLista();
        Iterador<Transaccion> iterador = t.iterador();

        Transaccion[] res = new Transaccion[t.longitud()];

        for (int i = 0; i < t.longitud(); i++) {
            res[i] = iterador.siguiente();
        }
        
        return res;
    }

    public Transaccion extraerMaximaTransaccion() {
        this.cantTransacciones--;
        return this.transacciones.extraerRaiz();
    }

    public Transaccion maximaTransaccion() {
        return this.transacciones.maximo();
    }

    @Override
    public int compareTo(Bloque otro) {
        return this.id - otro.id;
    }

}