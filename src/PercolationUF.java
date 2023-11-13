
public class PercolationUF implements IPercolate{
    
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;
    private int mySize;
    
    public PercolationUF(IUnionFind finder, int size){
        if (size <= 0) throw new IllegalArgumentException("Grid is null");
        myFinder = finder;
        finder.initialize(size*size + 2);
        myGrid = new boolean[size][size];
        myOpenCount = 0;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        mySize = size;

        if (size >= 1) {
            for (int i = 0; i < size; i++) {
                myFinder.union(VTOP, getIndex(0, i));
                myFinder.union(VBOTTOM, getIndex(size - 1, i));
            }
        }

    }

    @Override
    public void open(int row, int col){
        if (! inBounds(row, col)) throw new IndexOutOfBoundsException(getIndex(row, col));
        if (!isOpen(row, col)){
        if (this.myGrid == null) throw new IllegalArgumentException("Grid null");
            myGrid[row][col] = true;
            myOpenCount++;
        }
        if (inBounds(row-1, col) && isOpen(row-1, col)) myFinder.union(getIndex(row, col), getIndex(row-1, col));
        if (inBounds(row+1, col) && isOpen(row+1, col)) myFinder.union(getIndex(row, col), getIndex(row+1, col));
        if (inBounds(row, col-1) && isOpen(row, col-1)) myFinder.union(getIndex(row, col), getIndex(row, col-1));
        if (inBounds(row, col+1) && isOpen(row, col+1)) myFinder.union(getIndex(row, col), getIndex(row, col+1));
        if (getIndex(row, col) <= mySize) myFinder.union(getIndex(row, col), VTOP);
        if (getIndex(row, col) >= (mySize)*(mySize) - mySize) myFinder.union(getIndex(row, col), VBOTTOM);
        
    }

    @Override
    public boolean isOpen(int row, int col){
        if (! inBounds(row, col)) throw new IndexOutOfBoundsException(getIndex(row, col));
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col){
        if (! inBounds(row, col)) throw new IndexOutOfBoundsException(getIndex(row, col));
        return myFinder.connected(getIndex(row, col), VTOP);
    }

    @Override
    public boolean percolates(){
        return myFinder.connected(VTOP, VBOTTOM);
    }

    public int getIndex(int row, int col){
        return row*mySize + col;
    }

    public boolean inBounds(int row, int col) {
        if (this.myGrid == null) return false;
        return row >= 0 && row < mySize && col >= 0 && col < mySize;
    }

    @Override
    public int numberOfOpenSites(){
        return myOpenCount;
    }
}