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
        for (int i = 0; i < transacciones.length; i++) {
            usuarios.sumarSaldo(transacciones[i].id_vendedor(), transacciones[i].monto());
            if (transacciones[i].id_comprador() != 0) {
                usuarios.restarSaldo(transacciones[i].id_comprador(), transacciones[i].monto());
            }
        }
    }

    public Transaccion txMayorValorUltimoBloque(){
        return this.blockchain.ultimoBloque().maximaTransaccion();
    }

    public Transaccion[] txUltimoBloque(){
        if (this.blockchain.ultimoBloque() == null) {
            return new Transaccion[0];
        }
        return this.blockchain.ultimoBloque().getTransaccionesPorID();
    }

    public int maximoTenedor(){
        return this.usuarios.maximoTenedor();
    }

    public int montoMedioUltimoBloque(){
        return this.blockchain.ultimoBloque().montoMedioBloque();
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
        } 
        else {
            this.usuarios.sumarSaldo(idComprador, monto);
            this.usuarios.restarSaldo(idVendedor, monto);
        }

        this.blockchain.ultimoBloque().restarMontoTotal(monto);

        // extraigo la transacción de mayor monto del último bloque
        this.blockchain.ultimoBloque().extraerMaximaTransaccion();
    }
}
