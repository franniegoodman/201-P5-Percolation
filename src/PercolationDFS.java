import java.util.Stack;

public class PercolationDFS extends PercolationDefault{
    
    public PercolationDFS(int size) {
        super(size);
    }
    @Override
    protected void search(int row, int col) {
		// out of bounds?
		if (! inBounds(row,col)) return;
		
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col)){
			return;
		}

		int[] rowDelta = {-1, 1, 0 , 0};
		int[] colDelta = {0, 0, -1, 1};
		if (myGrid[row][col] != FULL) return;
		Stack<int[]> stack = new Stack<>();
		myGrid[row][col] = FULL;
		stack.push(new int[]{row, col});
		while (stack.size() != 0){
			int[] cords = stack.pop();
			for (int k = 0; k < rowDelta.length; k++){
				row = cords[0] + rowDelta[k];
				col = cords[1] + colDelta[k];
				if (inBounds(row, col) && myGrid[row][col] != FULL){
					stack.push(new int[]{row, col});
					myGrid[row][col] = FULL;
				}
			}
		}

	}

    

}