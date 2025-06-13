package aed;

public class Bloque {
    private ListaEnlazada<Transaccion> transacciones;
    private int id;
    private int montoMedio;    // Este atributo es para que montoMedioUltimoBloque() sea O(1)

    public Bloque(Transaccion[] transacciones, int id) {
        this.id = id;
        this.transacciones = new ListaEnlazada<>();

        int montoTotal = 0;
        for (int i = 0; i < transacciones.length; i++) {
            this.transacciones.agregar(transacciones[i]);   
            montoTotal += transacciones[i].monto();         // sumo el monto de cada transacción
        };

        // this.transacciones ahora es una lista enlazada con cada transacción del bloque

        this.montoMedio = montoTotal / transacciones.length;    // calculo el monto medio del bloque
    }

    public int getId() {
        return id;
    }

    public int montoMedio() {
        return montoMedio;
    }

    public ListaEnlazada<Transaccion> getTransacciones() {
        return transacciones;
    }

}