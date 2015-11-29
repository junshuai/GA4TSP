import java.util.Scanner;
import java.io.*;

/**
 * A generator for TSP. It provides two static methods for
 * generating TSP randomly or by reading from files.
 */
public class TSPGenerator {
    /**
     * Generate TSP randomly.
     *
     * The coordinates of cities is between [0, 1).
     */
    public static TSP generateTSPRandomly(int numberOfCities) {
        return new TSP(numberOfCities);
    }

    /**
     * Generate TSP from a file.
     *
     * The file should have the following format:
     *
     * The first line contains one integer n, which is the number of cities.
     * In the next n lines, each line contains two floating numbers x_i and
     * y_i, which is the coordinate of a city.
     */
    public static TSP generateTSPFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner s = new Scanner(file);

        int n = s.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = s.nextDouble();
            y[i] = s.nextDouble();
        }

        return new TSP(n, x, y);
    }
}
