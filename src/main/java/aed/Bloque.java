package aed;

import java.util.ArrayList;

public class Bloque {
    private int id;
    private int montoMedio;    // Este atributo es para que montoMedioUltimoBloque() sea O(1), no cuenta las de creación
    private MaxHeap<Transaccion> transacciones;

    public Bloque(Transaccion[] trans, int id) {
        this.id = id;
        
        int montoTotal = 0;
        for (Transaccion t: trans) {
            montoTotal += t.monto();   
        }
        if (this.id < 3000) {
            montoTotal--;   // si hay menos de 3000 bloques resto el monto de la transacción de creación (1)
        }

        this.montoMedio = montoTotal / trans.length;

        this.transacciones = new MaxHeap<>(trans);
    }

    public int getId() {
        return id;
    }

    public int montoMedio() {
        return montoMedio;
    }

    public Transaccion[] getTransaccionesPorID() {
        ArrayList<MaxHeap<Transaccion>.Handle> handles = transacciones.getHandlesArray();

        Transaccion[] res = new Transaccion[handles.size()];
        for (int i = 0; i < handles.size(); i++) {
            res[i] = handles.get(i).valor();
        }
        return res;
    }

    public Transaccion extraerMaximaTransaccion() {
        return this.transacciones.extraerRaiz();
    }

    public Transaccion maximaTransaccion() {
        return this.transacciones.maximo();
    }

}