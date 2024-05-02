package PreWork;
import java.util.concurrent.RecursiveAction;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de Vector2D
        Vector2D vector2D = new Vector2D(10, 10);
        Vector2D newVector2D = new Vector2D(5, 5);
        // Llenar newVector2D con los valores deseados

        fillRandomValues(newVector2D);
        fillRandomValues(vector2D);
        System.out.println("Vector2D:");
        System.out.println(vector2D);

        System.out.println("newVector2D:");
        System.out.println(newVector2D);

        // Crear una tarea para modificar el vector
        ModifyVectorTask task = new ModifyVectorTask(2, 2, 5, 5, 2,2, vector2D, newVector2D);

        // Crear un ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // Ejecutar la tarea en el ForkJoinPool
        pool.invoke(task);
        System.out.println("Vector2D:");
        System.out.println(vector2D);

    }
    private static void fillRandomValues(Vector2D vector) {
        Random random = new Random();
        double[][] data = vector.getData();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = random.nextInt(10); // Cambia el rango si es necesario
            }
        }
    }
}