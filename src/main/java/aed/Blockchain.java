package aed;

public class Blockchain {
    private ListaEnlazada<Bloque> bloques;

    public Blockchain() {
        this.bloques = new ListaEnlazada<>();
    }

    public void agregarBloque(Bloque bloque) {
        bloques.agregar(bloque);
    }

    public Bloque primerBloque() {
        return bloques.primero();
    }

    public Bloque ultimoBloque() {
        return bloques.ultimo();
    }

    public ListaEnlazada<Bloque> bloques() {
        return bloques;
    }
}