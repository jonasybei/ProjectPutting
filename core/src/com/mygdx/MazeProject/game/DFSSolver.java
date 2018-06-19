import java.util.ArrayList;

public class DFSSolver {

  public ArrayList<Cell> solve(Cell[][] maze){
    return convertAll(exitToCell(findExit(maze)));
  }

  public DFSCell findExit(Cell[][] maze) {
    
    //make the entire maze unvisited
    for(int i = 0; i < maze.length; i++) {
      for(int j = 0; j < maze[i].length; j++) {
        maze[i][j].unvisit();
      }
    }

    ArrayList<DFSCell> currentCells = new ArrayList<DFSCell>();
    Cell start = maze[0][0];
    currentCells.add(DFSCell.convertFromCell(start, null));
    int[] goalposition = {maze.length - 1, maze.length - 1};

    boolean done = false;

    while(!done) {
      int amount = currentCells.size();

      for(int i = 0; i < amount; i++) {
        if(currentCells.get(i).getCellPos() == goalposition) {
          done = true;
          return currentCells.get(i);
        }
      }

      for(int i = 0; i < amount; i++) {
        DFSCell currentCell = currentCells.get(0);
        int[] currentCellPosition = currentCell.getCellPos();
        maze[currentCellPosition[0]][currentCellPosition[1]].visit();

        //Add upper cell
        if(!currentCell.getCellWalls[0] && currentCellPosition[0] < maze.length -1 && !maze[currentCellPosition[0] + 1][currentCellPosition[1]].isVisited()) {
          currentCells.add(DFSCell.convertFromCell(maze[currentCellPosition[0] + 1][currentCellPosition[1]], currentCell));
        }

        //Add lower cell
        if(!currentCell.getCellWalls[1] && currentCellPosition[0] > 0 && !maze[currentCellPosition[0] - 1][currentCellPosition[1]].isVisited()) {
          currentCells.add(DFSCell.convertFromCell(maze[currentCellPosition[0] - 1][currentCellPosition[1]], currentCell));
        }

        //Add left Cell
        if(!currentCell.getCellWalls[2] && currentCellPosition[1] > 0 && !maze[currentCellPosition[0]][currentCellPosition[1] - 1].isVisited()) {
          currentCells.add(DFSCell.convertFromCell(maze[currentCellPosition[0]][currentCellPosition[1] - 1], currentCell));
        }

        //Add right Cell
        if(!currentCell.getCellWalls[3] && currentCellPosition[1] < maze[0].length -1 && !maze[currentCellPosition[0]][currentCellPosition[1] + 1].isVisited()) {
          currentCells.add(DFSCell.convertFromCell(maze[currentCellPosition[0]][currentCellPosition[1] + 1], currentCell));
        }
      }
    }

  }

  public ArrayList<DFSCell> exitToCell(DFSCell cell) {
    ArrayList<DFSCell> result = new ArrayList<DFSCell>();
    DFSCell current = cell;

    while(current!=null) {
      result.add(current);
      current = current.getParent();
    }

    return result;
  }

  public ArrayList<Cell> convertAll(ArrayList<DFSCell> original) {
    ArrayList<Cell> result = new ArrayList<Cell>();
    for(int i = 0; i < original.size(); i++) {
      result.add(original.get(i).convertToCell());
    }
    return result;
  }

}
