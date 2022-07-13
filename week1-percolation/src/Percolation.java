import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF system;
    private final int n;
    private final boolean[] openedSites;
    private int openSites;

    public Percolation(int n) {
        this.n = n;
        this.openSites = 0;
        this.openedSites = new boolean[n * n];

        if (n <= 0)
            throw new IllegalArgumentException("Argument 'n' must be greater than 0");

        this.system = new WeightedQuickUnionUF((n * n) + 2);
        for (int i = 1; i <= n; i++) {
            connect(getCell(1, i), getTopVirtualNode());
            connect(getCell(n, i), getBottomVirtualNode());
        }
    }

    public void open(int row, int col) {
        validateRowAndCol(row, col);
        
        if (isOpen(row, col))
            return;

        openSites++;
        int cell = getCell(row, col);
        openedSites[cell] = true;

        if (row > 1) {
            int top = getCell(row - 1, col);
            connectIfOpenned(cell, top);
        }
        if (row < n) {
            int bottom = getCell(row + 1, col);
            connectIfOpenned(cell, bottom);
        }
        if (col > 1) {
            int left = getCell(row, col - 1);
            connectIfOpenned(cell, left);
        }
        if (col < n) {
            int right = getCell(row, col + 1);
            connectIfOpenned(cell, right);
        }
    }

    public boolean isOpen(int row, int col) {
        validateRowAndCol(row, col);
        int cell = getCell(row, col);

        return openedSites[cell];
    }

    public boolean isFull(int row, int col) {
        validateRowAndCol(row, col);
        int cell = getCell(row, col);
        if (!isOpen(row, col))
            return false;
        int top = getTopVirtualNode();
        return system.find(cell) == system.find(top);
    }

    public int numberOfOpenSites() {
        return this.openSites;
    }

    public boolean percolates() {
        int top = getTopVirtualNode();
        int bottom = getBottomVirtualNode();
        return system.find(bottom) == system.find(top);
    }

    private void connectIfOpenned(int cell1, int cell2) {
        if (areOpened(cell1, cell2))
            connect(cell1, cell2);
    }

    private boolean areOpened(int cell1, int cell2) {
        return openedSites[cell1] && openedSites[cell2];
    }

    private void connect(int cell1, int cell2) {
        this.system.union(cell1, cell2);
    }

    private void validateRowAndCol(int row, int col) {
        if (row <= 0)
            throw new IllegalArgumentException("Argument 'row' must be greater than 0");
        if (col <= 0)
            throw new IllegalArgumentException("Argument 'column' must be greater than 0");
    }

    private int getCell(int row, int col) {
        return ((row - 1) * n) + (col - 1);
    }

    private int getTopVirtualNode() {
        return n * n;
    }

    private int getBottomVirtualNode() {
        return n * n + 1;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        System.out.println(p.isFull(1, 1));
    }
}