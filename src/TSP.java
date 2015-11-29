/**
 * A 2D Traveling Salesman Problem entity. It will also provide the method of
 * getting the number of cities and calculating the distance between two
 * cities.
 *
 * Note: We do not create an instance for TSP class directly. To create an TSP
 * instance, please see TSPGenerator class.
 */
public class TSP {
    private int numberOfCities;
    private City[] cities;

    /**
     * The constructor which accepts only a parameter n will generate the
     * coordinates of cities randomly.
     */
    public TSP(int n) {
        this.numberOfCities = n;
        this.cities = new City[n];
        for (int i = 0; i < n; i++)
            this.cities[i] = new City(Math.random(), Math.random());
    }

    public TSP(int n, double[] x, double[] y) {
        this.numberOfCities = n;
        this.cities = new City[n];
        this.cities = new City[n];
        for (int i = 0; i < n; i++)
            this.cities[i] = new City(x[i], y[i]);
    }

    public int getNumberOfCities() {
        return this.numberOfCities;
    }

    public double getDistanceBetween(int u, int v) {
        double dx = cities[u].x - cities[v].x;
        double dy = cities[u].y - cities[v].y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public String toString() {
        String res = new String();
        res += this.numberOfCities + "\n";
        for (int i = 0; i < this.numberOfCities; i++) {
            res += this.cities[i].x + " " + this.cities[i].y + "\n";
        }
        return res;
    }
}
