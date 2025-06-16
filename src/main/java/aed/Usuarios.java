package aed;

public class Usuarios {
    private Usuario[] arrayUsuarios;
    private MaxHeap<Usuario> heapUsuarios;  // heap de usuarios
    private int cantidadUsuarios;


    // Crear el heap
    
    public Usuarios(int n) {

        this.cantidadUsuarios = n;
        this.arrayUsuarios = new Usuario[n];

        for (int i = 0; i < n; i++) {
            this.arrayUsuarios[i] = new Usuario(i + 1);
        }

        this.heapUsuarios = new MaxHeap<>(arrayUsuarios);
    }

    public Usuario getUsuario(int id) {
        return this.arrayUsuarios[id - 1];  // id - 1 porque el arreglo empieza en 0
    }   

    public void sumarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);
        u.setSaldo(u.getSaldo() + monto);

        //reordeno el heap
        this.heapUsuarios.modificarPorId(id - 1, u);
    }

    public void restarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);
        u.setSaldo(u.getSaldo() - monto);

        this.heapUsuarios.modificarPorId(id - 1, u);
    }

    public int cantidadUsuarios() {
        return this.cantidadUsuarios;
    }

    public int maximoTenedor() {
        return this.heapUsuarios.maximo().getId();
    }
}