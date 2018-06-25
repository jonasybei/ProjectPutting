package com.mygdx.MazeProject.BotAlgorithms;

import com.mygdx.MazeProject.game.Cell;
import com.mygdx.MazeProject.game.MazeGeneratorABalg;

import java.util.ArrayList;

public class MazeTester {

    public static void main(String[] args) {
        MazeGeneratorABalg generator = new MazeGeneratorABalg(5);
        Cell[][] maze = generator.getMaze();
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                Cell currentCell = maze[i][j];
                System.out.println("CellX: " + currentCell.getCellPos()[1] + " CellY: " + currentCell.getCellPos()[0]);
                System.out.println("TopWall: " + currentCell.getCellWalls()[0] + " BottomWall: " + currentCell.getCellWalls()[1] + " LeftWall: " + currentCell.getCellWalls()[2] + " RightWall: " + currentCell.getCellWalls()[3]);
            }
        }
        System.out.println();
        BFS solver = new BFS(maze);
        ArrayList<Cell> solution = solver.getSequenceToExit();
        solution = shortenSequenceToExit(solution);
        for(int i = 0; i < solution.size(); i++) {
            System.out.println("Cell: " + (i + 1));
            System.out.println("CellX: " + solution.get(i).getCellPos()[1] + " CellY: " + solution.get(i).getCellPos()[0]);
        }
    }

    public static ArrayList<Cell> shortenSequenceToExit(ArrayList<Cell> sequenceToExit) {
        for(int i = 0; i < sequenceToExit.size() - 2; i++) {
            int firstY = sequenceToExit.get(i).getCellPos()[0];
            int secondY = sequenceToExit.get(i + 1).getCellPos()[0];
            int thirdY = sequenceToExit.get(i + 2).getCellPos()[0];
            int firstX = sequenceToExit.get(i).getCellPos()[1];
            int secondX = sequenceToExit.get(i + 1).getCellPos()[1];
            int thirdX = sequenceToExit.get(i + 2).getCellPos()[1];
            if((firstY == secondY) && (firstY == thirdY) || (firstX == secondX) && (firstX == thirdX)) {
                sequenceToExit.remove(i + 1);
                i--;
            }
        }
        return sequenceToExit;
    }

}
