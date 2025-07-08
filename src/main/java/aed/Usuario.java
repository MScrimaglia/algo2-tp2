package aed;

public class Usuario implements Comparable<Usuario>{

    private int id;           // ID del usuario
    private int saldo;        // monto del usuario
    

    public Usuario(int id) {   // O(1)
        this.id = id;          // O(1)
        this.saldo = 0;        // O(1)
    }

    public int getId() { 
        return id;      // O(1)
    }

    public int getSaldo() {
        return saldo;   // O(1)
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo; // O(1)
    }

    @Override
    public int compareTo(Usuario otro) {
        if (this.saldo != otro.saldo) {     // O(1)
            return this.saldo - otro.saldo; // O(1)
        } 
        return otro.id - this.id;           // O(1)
    }
}