import java.util.Stack;

public class PercolationDFS extends PercolationDefault{
    
    public PercolationDFS(int size) {
        super(size);
    }
    @Override
    protected void search(int row, int col) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{row, col});
        
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int myRow = current[0];
            int myCol = current[1];
            
            if (!inBounds(myRow, myCol)) return;
            if (isFull(myRow, myCol) || !isOpen(myRow, myCol)) return;
        
            
            myGrid[myRow][myCol] = FULL;
            
            int[] deltaRow = {-1, 0, 0, 1};
            int[] deltaCol = {0, -1, 1, 0};
            
            for (int i = 0; i < deltaRow.length; i++) {
                int newRow = myRow + deltaRow[i];
                int newCol = myCol + deltaCol[i];
                stack.push(new int[]{newRow, newCol});
            }
        }
    }
}
