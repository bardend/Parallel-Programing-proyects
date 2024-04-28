package org.example;
import java.util.Random;

public class MatrixMultiply {

    private MatrixMultiply() {
    }

    public static void seqMatrixMultiply(int[][] matrix1, int[][] matrix2, int [][] result, final int n) {

        for(int i = 0; i < matrix1.length; i++) {
            for(int j =0; j < matrix2[1].length; j++){
                for(int k = 0; k < matrix1[0].length; k++){
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
    }

    public static void parallelnaiveMatrixMultiply(int[][] matrix1, int[][] matrix2, int [][] result, final int n) {

        Thread[] threads = new Thread[2*n];
        for(int ii = 0; ii < matrix1.length; ii++) {
            final int i = ii;
            threads[ii] = new Thread(() -> {
                for(int jj =0; jj < matrix2[1].length; jj++){
                    final int j = jj;
                    threads[jj+n] = new Thread(() -> {
                        for(int k = 0; k < matrix1[0].length; k++){
                            result[i][j] += matrix1[i][k] * matrix2[k][j];
                        }
                    });
                    threads[jj+n].start();
                }
            });
            threads[i].start();
        }

        for(int ii =0; ii < 2*n; ii++) {
            try{
                threads[ii].join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void parallelchunkMatrixMultiply2(int[][] matrix1, int[][] matrix2,
                                                   int [][] result, final int n, final int tasks) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
            }
        }

        Thread[] threads = new Thread[2 * tasks];

        for(int ii = 0; ii < tasks; ii++) {
            final int i = ii;
            threads[i] = new Thread(() -> {
                final int chunkSize = (n + tasks - 1) / tasks;
                final int left = (i * chunkSize);
                int right = (left + chunkSize) - 1;
                if (right >= n) right = n - 1;
                final int r = right;
                for (int jj = 0; jj < tasks; jj++) {
                    final int j = jj;
                    threads[j + tasks] = new Thread(() -> {
                        final int left2 = (j * chunkSize);
                        int right2 = (left2 + chunkSize) - 1;
                        if (right2 >= n) right2 = n - 1;
                        for (int i1 = left; i1 <= r; i1++) {
                            for (int id2 = left2; id2 <= right2; id2++) {
                                for (int k = 0; k < n; k++) {
                                    result[i1][id2] += matrix1[i1][k] * matrix2[k][id2];
                                }
                            }
                        }
                    });
                    threads[j + tasks].start();
                }
            });
            threads[i].start();
        }

        for(int ii = 0; ii < 2*tasks; ii++) {
            try{
                threads[ii].join();
            } catch(InterruptedException e){
                System.out.println("Interrupted");
                e.printStackTrace();
            }
        }
    }

    public static void parallelchunkMatrixMultiply(int [][] matrix1, int[][] matrix2, int [][] result,
                                                   final int n, final int tasks) {


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
            }
        }

        Thread[] threads = new Thread[2 * tasks];

        for(int ii = 0; ii < tasks; ii++) {
            final int i = ii;
            threads[i] = new Thread(() -> {
                final int chunkSize = (n + tasks - 1) / tasks;
                final int left = (i * chunkSize);
                int right = (left + chunkSize) - 1;
                if (right >= n) right = n-1;

                for(int i1 = left; i1 <= right; i1++) {
                    final int id1 = i1;

                    for(int jj = 0; jj < tasks; jj++) {
                        final int j = jj;
                        threads[j+tasks] = new Thread(() -> {
                            final int left2 = (j * chunkSize) ;
                            int right2 = (left2 + chunkSize) - 1;
                            if (right2 >= n) right2 = n-1;
                            for(int id2 = left2; id2 <= right2; id2++) {
                                for(int k = 0; k < n; k++){
                                    result[id1][id2] += matrix1[id1][k] * matrix2[k][id2];
                                }
                            }
                        });
                        threads[j+tasks].start();
                    }
                }
            });
            threads[i].start();
        }

        for(int ii = 0; ii < 2*tasks; ii++) {
            try{
                threads[ii].join();
            } catch(InterruptedException e){
                System.out.println("Interrupted");
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {

        int n = 4; // Puedes cambiar este valor al tamaño que desees

        // Matrices aleatorias
        int[][] matrix1 = generateRandomMatrix(n);
        int[][] matrix2 = generateRandomMatrix(n);
        int[][] result = new int[n][n];

        printMatrix(matrix1);
        printMatrix(matrix2);

        parallelchunkMatrixMultiply2(matrix1, matrix2, result, n, 2);
        printMatrix(result);


    }

    public static int[][] generateRandomMatrix(int n) {
        Random random = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(10); // Genera números aleatorios entre 0 y 99
            }
        }
        return matrix;
    }
    // Sin embargo, los parámetros de tipo referencia (como matrices, objetos, etc.)

    // Método para imprimir una matriz
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
