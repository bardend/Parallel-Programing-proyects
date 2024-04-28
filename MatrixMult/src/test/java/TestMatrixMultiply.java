import junit.framework.TestCase;
import org.example.MatrixMultiply;
import org.junit.Test;

import java.util.Random;


public class TestMatrixMultiply extends TestCase {

    final static private int n = 600; // Tamaño de las matrices
    final static private int REPEATS = 5;

    private static int[][] generateRandomMatrix(int n) {
        Random random = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(10); // Genera números aleatorios entre 0 y 9
            }
        }
        return matrix;
    }

    private void checkResult(final int[][] ref, final int[][] output, final int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String msg = "Error detected on cell (" + i + ", " + j + ")";
                assertEquals(msg, ref[i][j], output[i][j]);
            }
        }
    }

    public static double testHelper() {
        final int[][] a = generateRandomMatrix(n);
        final int[][] b = generateRandomMatrix(n);
        final int[][] c = new int[n][n];
        final int[][] d = new int[n][n];

        MatrixMultiply.seqMatrixMultiply(a, b, c, n);
        MatrixMultiply.parallelnaiveMatrixMultiply(a, b, d, n);

        //checkResult(c, d, n);

        final long seqStartTime = System.currentTimeMillis();
        for (int r = 0; r < REPEATS; r++) {
            MatrixMultiply.seqMatrixMultiply(a, b, c, n);
        }
        final long seqEndTime = System.currentTimeMillis();

        final long parallelStartTime = System.currentTimeMillis();
        for (int r = 0; r < REPEATS; r++) {
            MatrixMultiply.parallelnaiveMatrixMultiply(a, b, d, n);
        }
        final long parallelEndTime = System.currentTimeMillis();

        final long seqTime = (seqEndTime - seqStartTime);
        final long parallelTime = (parallelEndTime - parallelStartTime);
        System.out.println(seqTime + " ms");
        System.out.println(parallelTime + " ms");
        return (double) seqTime / parallelTime;
    }

    // Se incluye un método de prueba para verificar el rendimiento
    public void testPerformancenaive() {
        for (int i = 0; i < 10; i++) {
            double speedup = testHelper();
            System.out.println("El speedup :" + speedup);
            // Agregar aserción para verificar el rendimiento (por ejemplo, que el speedup sea mayor que cierto valor)
            assertTrue(speedup > 0);
        }
    }
    public static double testHelperChunk() {
        final int[][] a = generateRandomMatrix(n);
        final int[][] b = generateRandomMatrix(n);
        final int[][] c = new int[n][n];
        final int[][] d = new int[n][n];

        MatrixMultiply.seqMatrixMultiply(a, b, c, n);
        MatrixMultiply.parallelchunkMatrixMultiply(a, b, d, n, 4);

        //checkResult(c, d, n);

        final long seqStartTime = System.currentTimeMillis();
        for (int r = 0; r < REPEATS; r++) {
            MatrixMultiply.seqMatrixMultiply(a, b, c, n);
        }
        final long seqEndTime = System.currentTimeMillis();

        int numCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Número de núcleos: " + numCores);

        final long parallelStartTime = System.currentTimeMillis();
        for (int r = 0; r < REPEATS; r++) {
            MatrixMultiply.parallelchunkMatrixMultiply2(a, b, d, n, numCores);
        }
        final long parallelEndTime = System.currentTimeMillis();

        final long seqTime = (seqEndTime - seqStartTime);
        final long parallelTime = (parallelEndTime - parallelStartTime);
        System.out.println(seqTime + " ms");
        System.out.println(parallelTime + " ms");
        return (double) seqTime / parallelTime;
    }

    public void testPerformanceChunk() {
        //El speedup :2.3354922279792745
        for (int i = 0; i < 10; i++) {
            double speedup = testHelperChunk();
            System.out.println("El speedup :" + speedup);
            // Agregar aserción para verificar el rendimiento (por ejemplo, que el speedup sea mayor que cierto valor)
            assertTrue(speedup > 0);
        }
    }
    public void testPerformanceChunk2() {
        //El speedup :4.234782608695652
        for (int i = 0; i < 10; i++) {
            double speedup = testHelperChunk();
            System.out.println("El speedup :" + speedup);
            // Agregar aserción para verificar el rendimiento (por ejemplo, que el speedup sea mayor que cierto valor)
            assertTrue(speedup > 0);
        }
    }
}
