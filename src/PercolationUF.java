import java.util.*;
public class PercolationUF implements IPercolate{
    
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;
    
    public PercolationUF(IUnionFind finder, int size){
        finder.initialize(size*size + 2);
        myFinder = finder;
    }

    @Override
    public abstract void open(int row, int col){

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
        return row*myGrid.length + col;
    }

    public boolean inBounds(int row, int col){
        if (row <= myGrid.length && col <= myGrid.length) return true;
        return false;
    }
    
}