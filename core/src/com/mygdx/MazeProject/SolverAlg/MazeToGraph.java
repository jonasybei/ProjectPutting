package com.mygdx.MazeProject.SolverAlg;

public class MazeToGraph {

  /*private Cell[][] maze;
  private Graph graph;
  private ArrayList<Edge> edges;
  private ArrayList<Vertex> vertexes;
  private int counter = 0;

  public MazeToGraph(Cell[][] maze) {
    this.maze = maze;
    this.vertexes = new ArrayList<Vertex>();
    this.edges = new ArrayList<Edge>();
  }

  public void createGraph() {

    for (int i = 0; i < this.maze.length; i++) {
      for (int j = 0; j < this.maze.length; j++) {

        int[] vertexPos = {i, j}; // y and x
        Vertex currentVertex = new Vertex(vertexPos, "Vertex ");
        this.counter++;
        this.vertexes.add(currentVertex);

        boolean[] walls = this.maze[i][j].getCellWalls();

        if (!walls[0]) { // connection to top vertex

          int yPos = i - 1;
          int xPos = j;
          int[] pathVertexPos = {yPos, xPos};
          Vertex pathVertex = new Vertex(pathVertexPos, "Vertex");

          Edge topEdge = new Edge("edge", currentVertex, pathVertex);
          this.edges.add(topEdge);

        }
        if (!walls[1]) {

        }
        if (!walls[2]) {

        }
        if (walls[3]) {

        }

      }
    }

  }

  public boolean vertexExists(Vertex vertex) {
    for (Vertex v : this.vertexes) {
      if (v.equals(vertex)) {
        return true;
      }
    }
    return false;
  }

  public Graph getGraph() {
    createGraph();
    return this.graph;
  }*/


}
