package PreWork;
import java.util.concurrent.RecursiveAction;

public class ModifyVectorTask extends RecursiveAction {
    private static final int THRESHOLD = 5; // Umbral para dividir la tarea
    private int xStart;
    private int yStart;
    private int height;
    private int width;
    private Vector2D vector2D;
    private Vector2D newVector2D;
    public int xx, yy;

    public ModifyVectorTask(int xStart, int yStart, int height, int width, int xx, int yy, Vector2D vector2D, Vector2D newVector2D) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.height = height;
        this.width = width;
        this.vector2D = vector2D;
        this.newVector2D = newVector2D;
        this.xx = xx; this.yy = yy;
    }

    @Override
    protected void compute() {
        if ((height <= THRESHOLD) && (width <= THRESHOLD)) {
            // Caso base: si la altura y el ancho son menores o iguales al umbral,
            // modifica el vector directamente en esta tarea.
            modifyVector();
        } else {
            // Dividir la tarea en sub-tareas más pequeñas
            int halfHeight = height / 2;

            invokeAll(
                    new ModifyVectorTask(xStart, yStart, halfHeight/2, width, xx, yy, vector2D, newVector2D),
                    new ModifyVectorTask(halfHeight/2 + xStart, yStart, halfHeight - halfHeight/2, width, xx, yy, vector2D, newVector2D)
            );
        }
    }

    private void modifyVector() {
        // Modificar la parte del vector especificada
        double[][] vectorData = vector2D.getData();
        double[][] newVectorData = newVector2D.getData();

        for(int i = xStart; i < xStart+height; i++) {
            for(int j = yStart; j < yStart+width; j++) {
                vectorData[i][j] = newVectorData[i-xx][j-yy];
            }
        }
    }
}

