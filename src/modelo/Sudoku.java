/* 
*Paquete Modelo, con la clase contenida Sudoku
 */
package modelo;

import java.util.Random;
import javax.swing.JTextField;

/*
*Clase Sudoku del paquete modelo, la cual contiene el código de la parte lódiga de juego
 */
public class Sudoku {

    /*
    *Método privado llamado Sudoku de tipo char el cuál tendrá dos arreglos, uno para las filas y otro para las columnas
     */
    private char sudoku[][];

    /*
    * Médoto público llamado Sudoku, el cuál contendrá todo el código lógico de resolverSudoku(), validarCuadradro(), cuadradoActual(),
    * validarFila(), validarColumna() y mostrarSudoku()
     */
    public Sudoku() {
        sudoku = new char[9][9];
        limpiarSudoku();
    }

    public void limpiarSudoku() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                sudoku[i][j] = ' ';
            }
        }
    }

    // Método booleano para resolver el sudoku una vez cargadas las letras
    public boolean resolverSudoku() {
        // Ciclo para recorrer las filas
        for (int i = 0; i < sudoku.length; i++) {
            // Ciclo para recorrer las columnas
            for (int j = 0; j < sudoku[0].length; j++) {
                // Condición para incluir una posible letra en el campo vacío
                if (sudoku[i][j] == ' ') {
                    // Ciclo para recorrer las letras posibles a ingresar
                    for (char letra = 'A'; letra <= 'I'; letra++) {
                        // Condición para validar que la letra no se repita en la fila, columna y cuadrado y así se pueda ingresar la letra
                        if (validarFila(i, letra) && validarColumna(j, letra) && validarCuadrado(i, j, letra)) {
                            sudoku[i][j] = letra;

                            //Se manda a llamar el método recursivo para que no haya valores que no correspondan dentro de las filas y columnas o se muestren espacios en blanco cuando no hay solución
                            if (resolverSudoku()) {
                                return true;
                            }

                            sudoku[i][j] = ' ';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Método booleano para validar el cuadrado de 3x3
    public boolean validarCuadrado(int i, int j, char letra) {
        // Obtiene la posición inicial de la fila del cuadrado
        int posI = cuadradoActual(i);
        // Obtiene la posición inicial de la columna del cuadrado
        int posJ = cuadradoActual(j);

        // Ciclo que recorre las filas del cuadrado actual 
        for (int k = posI - 3; k < posI; k++) {
            // Ciclo que recorre las filas del cuadrado actual 
            for (int l = posJ - 3; l < posJ; l++) {
                // Condicional que verifica si la letra ya existe en el cuadrado
                if (sudoku[k][l] == letra) {
                    // Retorna falso si la letra ya existe en el cuadrado
                    return false;
                }
            }
        }
        // Retorna verdadero si la letra no existe en el cuadrado
        return true;
    }

    public int cuadradoActual(int pos) {
        // Comprueba si la posición es menor o igual a 2
        if (pos <= 2) {
            // Retorna 3 si la posición es menor o igual a 2
            return 3;
            // Comprueba si la posición es menor o igual a 5
        } else if (pos <= 5) {
            // Retorna 6 si la posición es menor o igual a 5
            return 6;
        } else {
            // Retorna 9 si la posición es mayor que 5
            return 9;
        }
    }

    //Metodo booleano valida si una letra dada está presente en la fila especificada de un sudoku.
    public boolean validarFila(int i, char letra) {
        // Ciclo que recorre sobre cada columna en la fila especificada.
        for (int j = 0; j < sudoku[i].length; j++) {
            // Comprueba si la letra dada está presente en la posición actual de la fila.
            if (sudoku[i][j] == letra) {
                // Retorna false si la letra está presente en la fila.
                return false;
            }
        }
        // Retorna true si la letra no está presente en la fila.
        return true;
    }

    //Metodo booleano que valida si una letra dada está presente en la columna especificada de un sudoku.
    public boolean validarColumna(int j, char letra) {
        // Ciclo que recorre sobre cada fila en la columna especificada.
        for (int i = 0; i < sudoku.length; i++) {
            // Comprueba si la letra dada está presente en la posición actual de la columna.
            if (sudoku[i][j] == letra) {
                // Retorna false si la letra está presente en la columna.
                return false;
            }
        }
        // Retorna true si la letra no está presente en la columna.
        return true;
    }

    /**
     * Muestra el sudoku resuelto en la consola. Nota: este método asume que el
     * sudoku ya ha sido resuelto antes de llamarlo.
     */
    public void mostrarSudoku() {
        // Resuelve el sudoku antes de mostrarlo.
        resolverSudoku();
        // Ciclo que recorre sobre cada fila del sudoku.
        for (int i = 0; i < sudoku.length; i++) {
            // Ciclo que recorre sobre cada columna de la fila actual.
            for (int j = 0; j < sudoku[0].length; j++) {
                // Imprime el valor de la celda en la posición actual.
                System.out.print(sudoku[i][j]);
                // Agrega un guion si no es la última columna de la fila.
                if (!(j == sudoku[0].length - 1)) {
                    System.out.print(" - ");
                }
            }
            // Salta a una nueva línea después de imprimir cada fila.
            System.out.println();
        }
    }

    public void generarSudoku(int nivel) {
        limpiarSudoku();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char letra = (char) ('A' + random.nextInt(9));
                if (validarCuadrado(i, j, letra)) {
                    sudoku[i][j] = letra;
                } else {
                    j--;
                }
            }
        }

        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                char letra = (char) ('A' + random.nextInt(9));
                if (validarCuadrado(i, j, letra)) {
                    sudoku[i][j] = letra;
                } else {
                    j--;
                }
            }
        }

        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                char letra = (char) ('A' + random.nextInt(9));
                if (validarCuadrado(i, j, letra)) {
                    sudoku[i][j] = letra;
                } else {
                    j--;
                }
            }
        }

        resolverSudoku();

        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                int aux = j;
                int rand = random.nextInt(nivel + 1);
                j += rand;
                for (int k = aux; k < j && k < sudoku.length; k++) {
                    sudoku[i][k] = ' ';
                }
            }
        }

    }
    
    public boolean comprobarSudoku(){
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                char aux = sudoku[i][j];
                sudoku[i][j]= ' ';
                if(!validarFila(i, aux) || validarColumna(j, aux) || validarCuadrado(i, j, aux)){
                    sudoku[i][j]=aux;
                    return false;
                }
                
                sudoku[i][j]=aux;
            }
        }
        
        return true;
    }

    /*
    * Métodos Getter and Setter de la método privado llamado Sudoku
     */
    // Getter
    public char[][] getSudoku() {
        return sudoku;
    }

    // Setter
    public void setSudoku(char[][] sudoku) {
        this.sudoku = sudoku;
    }
}
