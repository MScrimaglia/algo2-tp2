package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;                       // O(1) 
        this.id_comprador = id_comprador;   // O(1)
        this.id_vendedor = id_vendedor;     // O(1)
        this.monto = monto;                 // O(1)
    }

    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto != otro.monto) {          // O(1)
            return this.monto - otro.monto;      // O(1)
        } 
        return this.id - otro.id;                // O(1)
    }

    @Override
    public boolean equals(Object otro){ 
        boolean otroEsNull = (otro == null);                        // O(1)
        boolean claseDistinta = this.getClass() != otro.getClass(); // O(1)

        if (otroEsNull || claseDistinta) {                          // O(1)
            return false;                                           // O(1)
        }

        Transaccion otraTransaccion = (Transaccion) otro;   // O(1)

        return this.id_comprador() == otraTransaccion.id_comprador()    // O(1)
            && this.id_vendedor() == otraTransaccion.id_vendedor()      // O(1)
            && this.monto() == otraTransaccion.monto()                  // O(1)
            && this.id() == otraTransaccion.id();                       // O(1)     
    }

    public int monto() {
        return monto;           // O(1)
    }

    public int id_comprador() {
        return id_comprador;    // O(1)
    }
    
    public int id_vendedor() {
        return id_vendedor;     // O(1)   
    }

    public int id() {
        return id;             // O(1)
    }
}