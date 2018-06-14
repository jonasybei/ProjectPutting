package com.mygdx.CrazyPutting.game;

public class Matrix {
  private float A[][] = new float[4][4];
  private float B[][];
  private float C[][] = new float[4][4];
  private float Z[][] = new float[6][6];

  public Matrix() {
    B = new float[4][4];
    createA();
    createC();
    createZ();
  }

  public float[][] getA() {
    return A;
  }

  public float[][] getB() {
    return B;
  }

  public float[][] getC() {
    return C;
  }


  public float[][] getZ() {
    return Z;
  }

  public float getCoefficient(int i, int j) {
    return this.B[i][j];
  }

  public void createA() {
    A[0][0] = 1;
    A[0][1] = 0;
    A[0][2] = 0;
    A[0][3] = 0;
    A[1][0] = 0;
    A[1][1] = 0;
    A[1][2] = 1;
    A[1][3] = 0;
    A[2][0] = -3;
    A[2][1] = 3;
    A[2][2] = -2;
    A[2][3] = -1;
    A[3][0] = 2;
    A[3][1] = -2;
    A[3][2] = 1;
    A[3][3] = 1;
  }

  public void createC() {
    int i, j;
    for (i = 0; i < 4; i++) {
      for (j = 0; j < 4; j++) {
        C[i][j] = A[j][i];
      }
    }
  }

  public void createZ() {    //ogni chiamata crea nuovi punti
    Z[0][0] = 1;
    Z[0][1] = 1;
    Z[0][2] = 1;
    Z[0][3] = 1;
    Z[0][4] = 1;
    Z[0][5] = 0;
    Z[1][0] = 1;
    Z[1][1] = 2;
    Z[1][2] = 2;
    Z[1][3] = 3;
    Z[1][4] = 1;
    Z[1][5] = 0;
    Z[2][0] = 4;
    Z[2][1] = 1;
    Z[2][2] = 1;
    Z[2][3] = 2;
    Z[2][4] = 1;
    Z[2][5] = 3;
    Z[3][0] = 1;
    Z[3][1] = 2;
    Z[3][2] = 1;
    Z[3][3] = 0;
    Z[3][4] = 2;
    Z[3][5] = 2;
    Z[4][0] = 1;
    Z[4][1] = 2;
    Z[4][2] = 3;
    Z[4][3] = 4;
    Z[4][4] = 1;
    Z[4][5] = 0;
    Z[5][0] = 1;
    Z[5][1] = 2;
    Z[5][2] = 1;
    Z[5][3] = 1;
    Z[5][4] = 1;
    Z[5][5] = 0;
  }

  public void setValue(int i, int j, float value) {
    B[i][j] = value;
  }

  public void navigate(int x, int y, float value) {
    this.B[x][y] = value;
  }
}
