package aed;
import java.util.ArrayList;

public class Usuarios {

    private MaxHeap heap;                          // heap de usuarios
    private int cantidadUsuarios;

    // Crear el heap
    
    public Usuarios(int n) {

        this.cantidadUsuarios = n;
        this.arregloUsuarios = new Usuario[n];
        this.heap = new HeapUsuarios(n);

        // Inicialización de usuarios
        for (int i = 0; i < n; i++) {
            arregloUsuarios[i] = new Usuario(i+1);  // i + 1 porque son números enteros consecutivos
            heap.insertar(arregloUsuarios[i]);
        }
    }

    public Usuario getUsuario(int id) {
        return arregloUsuarios[id - 1];  // id - 1 porque el arreglo empieza en 0
    }

    public void sumarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);
        u.setSaldo(u.getSaldo() + monto);
    }

    public void restarSaldo(int id, int monto) {
        Usuario u = getUsuario(id);
        u.setSaldo(u.getSaldo() - monto);
    }
}