package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    @Override
    public int compareTo(Transaccion otro) {
        return this.monto - otro.monto;
    }

    @Override
    public boolean equals(Object otro){
        boolean otroEsNull = (otro == null);
        boolean claseDistinta = this.getClass() != otro.getClass();

        if (otroEsNull || claseDistinta) {
            return false;
        }

        Transaccion otraTransaccion = (Transaccion) otro;

        return this.id_comprador() == otraTransaccion.id_comprador()
            && this.id_vendedor() == otraTransaccion.id_vendedor()
            && this.monto() == otraTransaccion.monto()
            && this.id() == otraTransaccion.id();   // Hay que agregar el id en equals?
    }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }

    public int id() {
        return id;
    }

    // public class Cola <T> {

    //     public class Handle {

    //         private Nodo nodoApuntado ;

    //         public Handle ( Nodo n ) {...}
    //         // Obtiene el dato apuntado
    //         public T obtener () {...}
    //         // Elimina el dato apuntado de la cola
    //         public void eliminar () {...}
    //     }
    // }

}