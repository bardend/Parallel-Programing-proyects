package PreWork;

public class Vector2D {
    private double[][] data;

    public Vector2D(int rows, int cols) {
        this.data = new double[rows][cols];
    }

    public double[][] getData() {
        return data;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] row : data) {
            for (double value : row) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
