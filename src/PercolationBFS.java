
import java.util.*;

public class PercolationBFS extends PercolationDefault{
    
    public PercolationBFS(int size){
        super(size);
    }

    @Override
    protected void search(int startRow, int startCol) {
        if (isFull(startRow, startCol) || !isOpen(startRow, startCol)) {
            return;
        }

        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        myGrid[startRow][startCol] = FULL;
        queue.add(new int[]{startRow, startCol});

        while (!queue.isEmpty()) {
            int[] coordinates = queue.remove();
            int row = coordinates[0];
            int col = coordinates[1];

            for (int k = 0; k < rowDelta.length; k++) {
                int newRow = row + rowDelta[k];
                int newCol = col + colDelta[k];

                if (inBounds(newRow, newCol) && myGrid[newRow][newCol] == FULL) {
                    queue.add(new int[]{newRow, newCol});
                    myGrid[newRow][newCol] = FULL;
                }
            }
        }
    }
}
