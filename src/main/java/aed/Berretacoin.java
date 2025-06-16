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
        throw new UnsupportedOperationException("Implementar!");
    }

    public int maximoTenedor(){
        return this.usuarios.maximoTenedor();
    }

    public int montoMedioUltimoBloque(){
        return this.blockchain.ultimoBloque().montoMedio();
    }

    public void hackearTx(){
        // restauro el monto de la transacción al comprador y al vendedor
        int idVendedor = this.blockchain.ultimoBloque().maximaTransaccion().id_vendedor();
        int idComprador = this.blockchain.ultimoBloque().maximaTransaccion().id_comprador();
        int monto = this.blockchain.ultimoBloque().maximaTransaccion().monto();

        this.usuarios.sumarSaldo(idComprador, monto);
        this.usuarios.restarSaldo(idVendedor, monto);

        // extraigo la transacción de mayor monto del último bloque
        this.blockchain.ultimoBloque().extraerMaximaTransaccion();
    }
}
