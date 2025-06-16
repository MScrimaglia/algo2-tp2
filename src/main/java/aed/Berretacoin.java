package aed;

public class Berretacoin {
    private Usuarios usuarios;
    private Blockchain blockchain;
    
    public Berretacoin(int n_usuarios){
        this.usuarios = new Usuarios(n_usuarios);
        this.blockchain = new Blockchain();
    }

    public void agregarBloque(Transaccion[] transacciones){
        this.blockchain.agregarBloque(transacciones);
    }

    public Transaccion txMayorValorUltimoBloque(){
        return this.blockchain.ultimoBloque().maximaTransaccion();
    }

    public Transaccion[] txUltimoBloque(){
        return this.blockchain.ultimoBloque().getTransaccionesPorID();
    }

    public int maximoTenedor(){
        return this.usuarios.maximoTenedor();
    }

    public int montoMedioUltimoBloque(){
        return this.blockchain.ultimoBloque().montoMedio();
    }

    public void hackearTx(){
        // restauro el monto de la transacción al comprador y al vendedor
        Transaccion transaccionAHackear = this.blockchain.ultimoBloque().maximaTransaccion();
        if (transaccionAHackear == null) return;

        int idVendedor = transaccionAHackear.id_vendedor();
        int idComprador = transaccionAHackear.id_comprador();
        int monto = transaccionAHackear.monto();

        if (idComprador == 0) {
            usuarios.restarSaldo(idVendedor, monto);
        } else {
            this.usuarios.sumarSaldo(idComprador, monto);
            this.usuarios.restarSaldo(idVendedor, monto);
        }

        // extraigo la transacción de mayor monto del último bloque
        this.blockchain.ultimoBloque().extraerMaximaTransaccion();
    }
}
