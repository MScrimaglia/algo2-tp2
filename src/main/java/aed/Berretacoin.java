package aed;

public class Berretacoin {
    private Usuarios usuarios;
    private Blockchain blockchain;
    
    public Berretacoin(int n_usuarios){
        this.usuarios = new Usuarios(n_usuarios);
        this.blockchain = new Blockchain();
    }

    public void agregarBloque(Transaccion[] transacciones){
        /*
         * 
         * Agrega un bloque a la cadena de bloques con las transacciones dadas.
         * La complejidad es O(log(n) * log(p)) porque recorremos todas las transacciones para actualizar los saldos de los usuarios.
         * Sumando en O(log n ) las transacciones al heap de transacciones,y en O(log p) los usuarios al heap de usuarios.
         */
        this.blockchain.agregarBloque(transacciones);
        for (int i = 0; i < transacciones.length; i++) {
            usuarios.sumarSaldo(transacciones[i].id_vendedor(), transacciones[i].monto());
            if (transacciones[i].id_comprador() != 0) {
                usuarios.restarSaldo(transacciones[i].id_comprador(), transacciones[i].monto());
            }
        }
    }

    public Transaccion txMayorValorUltimoBloque(){
        /*
         * La transaccion de mayor valor del último bloque es la que tiene el monto más alto.
         * La guardamos en una variable externa para no tener que recorrer el bloque y no sumar complejidad
         * Por ello es O(1) en vez de O(n) como sería recorrer el bloque.
         */
        return this.blockchain.ultimoBloque().maximaTransaccion();
    }

    public Transaccion[] txUltimoBloque(){ 
        /*
         * Devuelve las transacciones del último bloque.
         * La complejidad es O(1) porque pasa el array como la lista enlazada ya almacenada.
         */
        if (this.blockchain.ultimoBloque() == null) {
            return new Transaccion[0];
        }
        return this.blockchain.ultimoBloque().getTransaccionesPorID();
    }

    public int maximoTenedor(){
        /*
         * El máximo tenedor es el usuario que tiene más saldo acumulado.
         * La complejidad es O(1) porque es un Heap y podemos acceder al máximo directamente.
         * Ya que su maximo esta en la raiz.
         */
        return this.usuarios.maximoTenedor();
    }

    public int montoMedioUltimoBloque(){
        /*
         * El monto medio del último bloque es el promedio de los montos de las transacciones.
         * La complejidad es O(1) porque el bloque ya tiene el monto medio calculado.
         * Esto se debe a que el bloque almacena el monto total y la cantidad de transacciones,
         * por lo que podemos calcular el promedio directamente.
         */
        return this.blockchain.ultimoBloque().montoMedioBloque();
    }

    public void hackearTx(){
        /*
         * 
         */
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
