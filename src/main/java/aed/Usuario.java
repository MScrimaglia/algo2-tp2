package aed;

public class Usuario {

    private int id;           // ID del usuario
    private int saldo;        // monto del usuario
    
    //Ponemos su índice en el heap?
    public Usuario(int id) {
        this.id = id;
        this.saldo = 0;
    }

    public int getIt() {
        return id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}