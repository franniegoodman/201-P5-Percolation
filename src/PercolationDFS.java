import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PercolationDFS extends PercolationDefault{
    
    public PercolationDFS(int size) {
        super(size);
    }


    @Override
    protected void search(int row, int col) {
        if (!inBounds(row, col)) return;
        
        if (isFull(row, col) || !isOpen(row, col)) {
            return;
        }
        
        Stack<int[]> stack = new Stack<>();
        
        myGrid[row][col] = FULL;
        stack.push(new int[] {row, col});
        
        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int myRow = cell[0];
            int myCol = cell[1];
            
            int[] deltaRow = {-1, 0, 0, 1};
            int[] deltaCol = {0, -1, 1, 0};
            
            for (int k = 0; k < deltaRow.length; k++) {
                int newRow = myRow + deltaRow[k];
                int newCol = myCol + deltaCol[k];
                
                if (inBounds(newRow, newCol) && isOpen(newRow, newCol) && !isFull(newRow, newCol)) {
                    myGrid[newRow][newCol] = FULL;
                    stack.push(new int[] {newRow, newCol});
                }
            }
        }
    }
}
