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
    }

    public Usuario getUsuario(int id) {
        return this.heapUsuarios.obtenerPorId(id);  // id - 1 porque el arreglo empieza en 0
    }   

    public void sumarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);
        u.setSaldo(u.getSaldo() + monto);

        MaxHeap<Usuario>.Handle h = heapUsuarios.getArray().get(id - 1);

        //reordeno el heap
        this.heapUsuarios.subir(h);
    }

    public void restarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);
        u.setSaldo(u.getSaldo() - monto);

        MaxHeap<Usuario>.Handle h = heapUsuarios.getArray().get(id - 1);

        this.heapUsuarios.bajar(h);
    }

    public int cantidadUsuarios() {
        return this.cantidadUsuarios;
    }

    public int maximoTenedor() {
        return this.heapUsuarios.maximo().getId();
    }
}