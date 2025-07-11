package aed;

public class Blockchain {
    private ListaEnlazada<Bloque> bloques;

    public Blockchain() {
        this.bloques = new ListaEnlazada<>();
    }

    public void agregarBloque(Transaccion[] trans) {
        Bloque nuevoBloque = new Bloque(trans, bloques.longitud() + 1);
        bloques.agregarAtras(nuevoBloque);
    }

    public Bloque primerBloque() {
        return bloques.primero();
    }

    public Bloque ultimoBloque() {
        return bloques.ultimo();
    }

    public ListaEnlazada<Bloque> getBloques() {
        return this.bloques;
    }
}