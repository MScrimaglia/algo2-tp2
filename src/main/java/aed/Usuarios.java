package aed;

public class Usuarios {
    private MaxHeap<Usuario> heapUsuarios;  // heap de usuarios
    private int cantidadUsuarios;

    public Usuarios(int n) {

        this.cantidadUsuarios = n;
        Usuario[] arrayUsuarios = new Usuario[n];

        for (int i = 0; i < n; i++) {
            arrayUsuarios[i] = new Usuario(i + 1);
        }

        this.heapUsuarios = new MaxHeap<>(arrayUsuarios);

        ListaEnlazada<Usuario> listaUsuarios = this.heapUsuarios.getLista();
        ListaEnlazada<Usuario>.Nodo nodoActual = listaUsuarios.primerNodo();
        while (nodoActual != null) {
            Usuario usuarioActual = nodoActual.getElemento();
            usuarioActual.setNodoEnHeap(nodoActual);
            nodoActual = nodoActual.getSiguiente();
        }
    }

    public Usuario getUsuario(int id) {
        return this.heapUsuarios.obtenerPorId(id);  // id - 1 porque el arreglo empieza en 0
    }   

    public void sumarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);
        u.setSaldo(u.getSaldo() + monto);

        ListaEnlazada.Nodo n = u.getNodoEnHeap(); // O(1)

        //reordeno el heap
        this.heapUsuarios.subir(n); // O(log n)
    }

    public void restarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);
        u.setSaldo(u.getSaldo() - monto);

        ListaEnlazada.Nodo n = u.getNodoEnHeap(); // O(1)

        this.heapUsuarios.bajar(n); // O(log n)
    }

    public int cantidadUsuarios() {
        return this.cantidadUsuarios;
    }

    public int maximoTenedor() {
        return this.heapUsuarios.maximo().getId();
    }
}
