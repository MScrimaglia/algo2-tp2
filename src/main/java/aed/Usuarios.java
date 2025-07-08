package aed;

public class Usuarios {
    private MaxHeap<Usuario> heapUsuarios;  // heap de usuarios
    private int cantidadUsuarios;

    // O(n)
    public Usuarios(int n) {

        this.cantidadUsuarios = n;
        Usuario[] arrayUsuarios = new Usuario[n];

        for (int i = 0; i < n; i++) { // O(n)
            arrayUsuarios[i] = new Usuario(i + 1);
        } 

        this.heapUsuarios = new MaxHeap<>(arrayUsuarios);
    }

    // O(1)
    public Usuario getUsuario(int id) {
        return this.heapUsuarios.obtenerPorId(id);  // id - 1 porque el arreglo empieza en 0
    }   

    // O(log n)
    public void sumarSaldo(int id, int monto) {
        Usuario u = getUsuario(id); // O(1)
        u.setSaldo(u.getSaldo() + monto); // O(1)

        MaxHeap<Usuario>.Handle h = heapUsuarios.getArray().get(id - 1); // O(1)

        //reordeno el heap
        this.heapUsuarios.subir(h); // O(log n)
    }

    // O(log n)
    public void restarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);             // O(1)
        u.setSaldo(u.getSaldo() - monto);       // O(1)

        MaxHeap<Usuario>.Handle h = heapUsuarios.getArray().get(id - 1);     // O(1) 

        this.heapUsuarios.bajar(h); // O(log n)
    }

    // O(1)
    public int cantidadUsuarios() {
        return this.cantidadUsuarios;               
    }

    // O(1)
    public int maximoTenedor() {
        return this.heapUsuarios.maximo().getId();  
    }
}