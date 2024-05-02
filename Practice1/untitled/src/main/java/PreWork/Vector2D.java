package PreWork;

public class Vector2D {
    private double[][] data;

    public Vector2D(int rows, int cols) {
        this.data = new double[rows][cols];
    }

    public double[][] getData() {
        return data;
    }
    public double getval(int row, int col) {
        return data[row][col];
    }
    public void setval(int row, int col, double val) {
        data[row][col] = val;
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
