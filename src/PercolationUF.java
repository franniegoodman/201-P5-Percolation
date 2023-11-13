
public class PercolationUF implements IPercolate{
    
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;
    
    public PercolationUF(IUnionFind finder, int size){
        finder.initialize(size*size + 2);
        myFinder = finder;
        VTOP = size*size;
        VBOTTOM = size*size + 1;
    }

    @Override
    public void open(int row, int col){
        if (! inBounds(row, col)) throw new IndexOutOfBoundsException(getIndex(row, col));
        if (isOpen(row, col)) throw new IllegalArgumentException("Cell already open");
        if (this.myGrid == null) throw new IllegalArgumentException("Grid null");
        myGrid[row][col] = true;
        if (isOpen(row-1, col)) myFinder.union(getIndex(row, col), getIndex(row-1, col));
        if (isOpen(row+1, col)) myFinder.union(getIndex(row, col), getIndex(row+1, col));
        if (isOpen(row, col-1)) myFinder.union(getIndex(row, col), getIndex(row, col-1));
        if (isOpen(row, col+1)) myFinder.union(getIndex(row, col), getIndex(row-1, col+1));
        if (getIndex(row, col) <= myGrid.length) myFinder.union(getIndex(row, col), VTOP);
        if (getIndex(row, col) >= (myGrid.length)*(myGrid.length) - myGrid.length) myFinder.union(getIndex(row, col), VBOTTOM);
        
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
        if (this.myGrid == null) throw new IndexOutOfBoundsException(getIndex(row, col));
        return row*myGrid.length + col;
    }

    public boolean inBounds(int row, int col){
        if (this.myGrid == null) return false;
        if (row < myGrid.length && col < myGrid.length) return true;
        return false;
    }
    @Override
    public int numberOfOpenSites(){
        return myOpenCount;
    }
}