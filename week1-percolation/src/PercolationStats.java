import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;

    private final int trials;
    private final int n;
    private double[] openedSitedPerExperiment;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0)
            throw new IllegalArgumentException("Parameter 'n' must be greater than 0");

        this.n = n;
        this.trials = trials;
        this.openedSitedPerExperiment = new double[trials];
        for (int i = 0; i < trials; i++)
            runExperiment(i);
    }

    private void runExperiment(int nthExperiment) {
        int numberOfSites = n * n;
        Percolation percolation = new Percolation(n);
        for (int i = 0; i < numberOfSites; i++) {
            openRandomCell(percolation);
            if (percolation.percolates()) {
                openedSitedPerExperiment[nthExperiment] = (((double) percolation.numberOfOpenSites()) / (n * n));
                return;
            }
        }
    }

    private void openRandomCell(Percolation percolation) {
        boolean cellNotOpenedYet = true;
        while (cellNotOpenedYet) {
            int row = 1 + StdRandom.uniform(n);
            int col = 1 + StdRandom.uniform(n);
            if (!percolation.isOpen(row, col)) {
                cellNotOpenedYet = false;
                percolation.open(row, col);
            }
        }
    }

    public double mean() {
        return StdStats.mean(this.openedSitedPerExperiment);
    }

    public double stddev() {
        return StdStats.stddev(this.openedSitedPerExperiment);
    }

    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials =  Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}