import java.util.Stack;

public class PercolationDFS extends PercolationDefault{
    
    public PercolationDFS(int size) {
        super(size);
    }
    @Override
    protected void search(int startRow, int startCol) {
        // out of bounds?
        if (!inBounds(startRow, startCol)) return;

        // full or NOT open, don't process
        if (isFull(startRow, startCol) || !isOpen(startRow, startCol)) {
            return;
        }

        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startRow, startCol});

        while (!stack.isEmpty()) {
            int[] coordinates = stack.pop();
            int row = coordinates[0];
            int col = coordinates[1];

            myGrid[row][col] = FULL;

            for (int k = 0; k < rowDelta.length; k++) {
                int newRow = row + rowDelta[k];
                int newCol = col + colDelta[k];

                if (inBounds(newRow, newCol) && myGrid[newRow][newCol] != FULL) {
                    stack.push(new int[]{newRow, newCol});
                }
            }
        }
    }

    

}