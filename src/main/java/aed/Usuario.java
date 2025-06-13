package aed;

public class Usuario implements Comparable<Usuario>{

    private int id;           // ID del usuario
    private int saldo;        // monto del usuario
    
    //Ponemos su índice en el heap?
    public Usuario(int id) {
        this.id = id;
        this.saldo = 0;
    }

    public int getId() {
        return id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public int compareTo(Usuario otro) {
        return this.saldo - otro.saldo;
    }
}