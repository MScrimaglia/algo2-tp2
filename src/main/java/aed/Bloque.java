package aed;

public class Bloque {
    private int id;
    private int montoMedio;    // Este atributo es para que montoMedioUltimoBloque() sea O(1)
    private MaxHeap<Transaccion> transacciones;

    public Bloque(Transaccion[] trans, int id) {
        this.id = id;
        
        int montoTotal = 0;
        for (Transaccion t: trans) {
            montoTotal += t.monto();   
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

    // public ListaEnlazada<Transaccion> getTransacciones() {
    //     return transacciones;
    // }

    public Transaccion extraerMaximaTransaccion() {
        return this.transacciones.extraerRaiz();
    }

    public Transaccion maximaTransaccion() {
        return this.transacciones.maximo();
    }

}