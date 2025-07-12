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
        for (int i = 0; i < transacciones.length; i++) {    // O(n)
            usuarios.sumarSaldo(transacciones[i].id_vendedor(), transacciones[i].monto());  // O(log P)
            if (transacciones[i].id_comprador() != 0) {
                usuarios.restarSaldo(transacciones[i].id_comprador(), transacciones[i].monto());    // O(log P)
            }
        }
    }

    public Transaccion txMayorValorUltimoBloque(){
        /*
         * La transaccion de mayor valor del último bloque es la que tiene el monto más alto.
         * La guardamos en una variable externa para no tener que recorrer el bloque y no sumar complejidad
         * Por ello es O(1) en vez de O(n) como sería recorrer el bloque.
         */
        return this.blockchain.ultimoBloque().maximaTransaccion(); // O(1)
    }

    public Transaccion[] txUltimoBloque(){ 
        /*
         * Devuelve un arreglo con las transacciones del último bloque.
         * La complejidad es O(n) porque se recorre la lista enlazada del heap para agregar cada elemento al arreglo.
         */
        if (this.blockchain.ultimoBloque() == null) {
            return new Transaccion[0];
        }
        return this.blockchain.ultimoBloque().getTransaccionesPorID();  // O(n)
    }

    public int maximoTenedor(){
        /*
         * El máximo tenedor es el usuario que tiene más saldo acumulado.
         * La complejidad es O(1) porque al estar almacenado en un Heap podemos acceder al máximo directamente
         * ya que este se encuentra en la raiz.
         */
        return this.usuarios.maximoTenedor();   // O(1)
    }

    public int montoMedioUltimoBloque(){
        /*
         * El monto medio del último bloque es el promedio de los montos de las transacciones.
         * La complejidad es O(1) porque el bloque cuenta con el monto total y la cantidad de transacciones almacenadas,
         * por lo que podemos calcular el promedio directamente con una división.
         */
        return this.blockchain.ultimoBloque().montoMedioBloque();   // O(1)
    }

    public void hackearTx(){
        /*
         * Tras eliminar la máxima transacción debemos reordenar el heap de transacciones con complejidad O(log n)
         * Luego al actualizar los saldos de los usuarios también debemos reordenar el heap de usuarios, con complehidad O(log P)
         * La complejidad final es O(log n + log P)
         */
        // restauro el monto de la transacción al comprador y al vendedor
        Transaccion transaccionAHackear = this.blockchain.ultimoBloque().maximaTransaccion();   // O(1)

        if (transaccionAHackear == null) {
            return;
        }

        int idVendedor = transaccionAHackear.id_vendedor();
        int idComprador = transaccionAHackear.id_comprador();
        int monto = transaccionAHackear.monto();

        if (idComprador == 0) {
            usuarios.restarSaldo(idVendedor, monto);    // O(log P) ya que se debe reordenar el heap
        } 
        else {
            this.usuarios.sumarSaldo(idComprador, monto);   // O(log P)
            this.usuarios.restarSaldo(idVendedor, monto);   // O(log P)
        }

        // extraigo la transacción de mayor monto del último bloque
        this.blockchain.ultimoBloque().extraerMaximaTransaccion();  // O(log n)
    }
    // Complejidad total: O(log n + log P)
}
