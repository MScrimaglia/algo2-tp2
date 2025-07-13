package aed;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MaxHeapTests {
    private MaxHeap heap;
    private Transaccion[] transacciones;

    @Test
    public void heapVacio() {
        heap = new MaxHeap<>();
        assertEquals(heap.cantidadElementos(), 0);
        assertNull(heap.maximo());
        assertNull(heap.extraerRaiz());
    }

    @Test
    public void heapEnteros() {
        Integer[] arregloEnteros = {4, 2, 5, 6, 1, 7, 3};
        heap = new MaxHeap<>(arregloEnteros);
        assertEquals(heap.cantidadElementos(), 7);
        assertEquals(heap.maximo(), 7);
        assertEquals(heap.extraerRaiz(), 7);
        assertEquals(heap.extraerRaiz(), 6);
        assertEquals(heap.extraerRaiz(),5);
        assertEquals(heap.cantidadElementos(), 4);
        assertEquals(heap.extraerRaiz(), 4);
        assertEquals(heap.extraerRaiz(), 3);
        assertEquals(heap.extraerRaiz(), 2);
        assertEquals(heap.extraerRaiz(), 1);
        assertEquals(heap.cantidadElementos(), 0);
        assertNull(heap.maximo());
        assertNull(heap.extraerRaiz());
    }

    @Test
    public void transaccionesDiferentesMontos() {
        transacciones = new Transaccion[] {
            new Transaccion(0, 0, 1, 5),
            new Transaccion(1, 1, 2, 2),
            new Transaccion(2, 2, 3, 3),
            new Transaccion(3, 3, 1, 6),
            new Transaccion(4, 1, 2, 1),
            new Transaccion(5, 2, 3, 4) 
        };
        heap = new MaxHeap<Transaccion>(transacciones);
        assertEquals(heap.maximo(), new Transaccion(3, 3, 1, 6));
        assertEquals(heap.cantidadElementos(), 6);
        assertEquals(heap.extraerRaiz(), new Transaccion(3, 3, 1, 6));
        assertEquals(heap.cantidadElementos(), 5);
        heap.extraerRaiz();
        heap.extraerRaiz();
        heap.extraerRaiz();
        heap.extraerRaiz();
        assertEquals(heap.maximo(), new Transaccion(4, 1, 2, 1));
        heap.extraerRaiz();
        assertEquals(heap.cantidadElementos(), 0);
        assertNull(heap.maximo());
        assertNull(heap.extraerRaiz());
    }

    @Test
    public void transaccionesMontosRepetidos() {
        transacciones = new Transaccion[] {
            new Transaccion(0, 0, 1, 5),
            new Transaccion(1, 1, 2, 2),
            new Transaccion(2, 2, 3, 5),
            new Transaccion(3, 3, 1, 8),
            new Transaccion(4, 1, 2, 2),
            new Transaccion(5, 2, 3, 4) 
        };
        heap = new MaxHeap<Transaccion>(transacciones);
        assertEquals(heap.maximo(), new Transaccion(3, 3, 1, 8));
        assertEquals(heap.extraerRaiz(), new Transaccion(3, 3, 1, 8));
        assertEquals(heap.cantidadElementos(), 5);
        assertEquals(heap.extraerRaiz(), new Transaccion(2, 2, 3, 5));
        assertEquals(heap.extraerRaiz(), new Transaccion(0, 0, 1, 5));
        heap.extraerRaiz();
        assertEquals(heap.extraerRaiz(), new Transaccion(4, 1, 2, 2));
        assertEquals(heap.extraerRaiz(), new Transaccion(1, 1, 2, 2));
    }
}