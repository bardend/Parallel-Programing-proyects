package Test_PreWork;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Phaser;
import PreWork.ModifyVectorTask;
import junit.framework.TestCase;
import PreWork.Vector2D;

public class ModifyVectorTaskTest extends TestCase {

    final static private int niterations = 2;
    final static private int n = 9000;

    private static int getNCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    public void runSequential(Vector2D vector, Vector2D chague, int xx, int yy) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                vector.setval(i, j, chague.getval(i-xx, j-yy));

            }
        }
    }
    private double forkJoinTest() {

        Vector2D vector2D = new Vector2D(n, n);
        Vector2D newVector2D = new Vector2D(n, n);

        fillRandomValues(newVector2D);
        fillRandomValues(vector2D);

        final long seqStartTime = System.currentTimeMillis();
        for(int r = 0; r < niterations; r++) {
            runSequential(vector2D, newVector2D, 0, 0);
        }
        final long seqEndTime = System.currentTimeMillis();

        final long forJoinStartTime = System.currentTimeMillis();

        for(int r = 0; r < niterations; r++) {
            ModifyVectorTask task = new ModifyVectorTask(0, 0, n, n ,0, 0, vector2D, newVector2D);
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(task);
        }
        final long forJoinEndTime = System.currentTimeMillis();


        final long seqTime = seqEndTime - seqStartTime;
        final long forJoinTime = forJoinEndTime - forJoinStartTime;

        System.out.println("Sequence time: " + seqTime);
        System.out.println("ForJoin time: " + forJoinTime);
        return (double) seqTime / forJoinTime;
    }

    public void testPerformance() {
        for(int i = 0; i < 3; i++) {
            double speedup = forkJoinTest();
            System.out.println("El speedup :" + speedup);
            // Agregar aserción para verificar el rendimiento (por ejemplo, que el speedup sea mayor que cierto valor)
            assertTrue(speedup > 0);
        }
    }

    private static void fillRandomValues(Vector2D vector) {
        //El speedup :0.9682539682539683 : (
        Random random = new Random();
        double[][] data = vector.getData();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = random.nextInt(10); // Cambia el rango si es necesario
            }
        }
    }

}
