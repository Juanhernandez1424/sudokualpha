package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    @Test
    public void testResolverSudoku() {
        Sudoku sudoku = new Sudoku();
        assertTrue(sudoku.resolverSudoku());
    }

    @Test
    public void testValidarCuadrado() {
        Sudoku sudoku = new Sudoku();
        assertTrue(sudoku.validarCuadrado(0, 0, 'A'));
    }

    @Test
    public void testCuadradoActual() {
        Sudoku sudoku = new Sudoku();
        assertEquals(3, sudoku.cuadradoActual(0));
    }

    @Test
    public void testValidarFila() {
        Sudoku sudoku = new Sudoku();
        assertTrue(sudoku.validarFila(0, 'A'));
    }

    @Test
    public void testValidarColumna() {
        Sudoku sudoku = new Sudoku();
        assertTrue(sudoku.validarColumna(0, 'A'));
    }
}
