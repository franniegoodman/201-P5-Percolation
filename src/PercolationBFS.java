
import java.util.*;

public class PercolationBFS extends PercolationDefault{
    
    public PercolationBFS(int size){
        super(size);
    }

    @Override
    protected void search(int row, int col) {
        if (!inBounds(row, col)) return;
        
        if (isFull(row, col) || !isOpen(row, col)) {
            return;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        myGrid[row][col] = FULL;
        queue.add(new int[] {row, col});
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int myRow = cell[0];
            int myCol = cell[1];
            
            int[] deltaRow = {-1, 0, 0, 1};
            int[] deltaCol = {0, -1, 1, 0};
            
            for (int k = 0; k < deltaRow.length; k++) {
                int newRow = myRow + deltaRow[k];
                int newCol = myCol + deltaCol[k];
                
                if (inBounds(newRow, newCol) && isOpen(newRow, newCol) && !isFull(newRow, newCol)) {
                    myGrid[newRow][newCol] = FULL;
                    queue.add(new int[] {newRow, newCol});
                }
            }
        }
    }
}
