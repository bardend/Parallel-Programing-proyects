package PreWork;
import java.util.concurrent.RecursiveAction;

public class ModifyVectorTask extends RecursiveAction {
    private static final int THRESHOLD = 80; // Umbral para dividir la tarea
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


        if ((height <= THRESHOLD) ) {
            // Caso base: si la altura y el ancho son menores o iguales al umbral,
            // modifica el vector directamente en esta tarea.
            modifyVector();
        } else {
            // Dividir la tarea en sub-tareas más pequeñas

            invokeAll(
                    new ModifyVectorTask(xStart, yStart,height/2, width, xx, yy, vector2D, newVector2D),
                    new ModifyVectorTask(height/2 + xStart, yStart, height - height/2, width, xx, yy, vector2D, newVector2D)
            );
        }
    }

    private void modifyVector() {

        for(int i = xStart; i < xStart+height; i++) {
            for(int j = yStart; j < yStart+width; j++) {
                vector2D.setval(i, j, newVector2D.getval(i-xx, j-yy));
            }
        }
    }
}

