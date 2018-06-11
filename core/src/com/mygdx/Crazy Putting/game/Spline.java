package com.mygdx.game;

public class Spline {
    private float[][] terrain = new float[6][6];
    private static Matrix[][] coefficientMatrix;

    public static void makeTerrain(int a, int b) {
        Matrix functionMatrix = new Matrix();
        float[][] AMatrix = functionMatrix.getA();
        float[][] CMatrix = functionMatrix.getC();
        float[][] DMatrix = new float [4][4];
        float[][] EMatrix = new float [4][4];
        coefficientMatrix = new Matrix [5][5];
        float[][] terrain = functionMatrix.getZ();
        functionMatrix.setValue(0,0, terrain[a][b]);
        functionMatrix.setValue(0,1, terrain[a][b+1]);
        functionMatrix.setValue(1,0, terrain[a+1][b]);
        functionMatrix.setValue(1,1, terrain[a+1][b+1]);
        fillMatx(functionMatrix, terrain, a, b);
        fillMaty(functionMatrix, terrain, a, b);
        fillMatxy(functionMatrix, terrain, a, b);
        clearMatrix(DMatrix);
        clearMatrix(EMatrix);
        matrixMultiplication(functionMatrix.getB(),CMatrix,DMatrix);
        matrixMultiplication(AMatrix,DMatrix,EMatrix);
        setCoefficient(EMatrix,functionMatrix);
        coefficientMatrix[a][b]=functionMatrix;
    }

    public static void clearMatrix (float[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        for (int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                mat[i][j]=0;
            }
        }
    }

    public static void matrixMultiplication (float[][] A, float[][] B, float[][] C) {
        int i,j,k;
        for (i=0;i<4;i++) {
            for (j=0;j<4;j++) {
                for (k=0;k<4;k++) {
                    C[i][j] = C[i][j] + (A[i][k]*B[k][j]);
                }
            }
        }
    }

    public static void fillMatx (Matrix mat, float[][] fun, int i, int j){
        if (i==0) {
            mat.setValue(2, 0, (fun[i + 1][j] - fun[i][j]));
            mat.setValue(2, 1, (fun[i + 2][j] - fun[i][j]));
            mat.setValue(3, 0, (fun[i + 1][j+1] - fun[i][j+1]));
            mat.setValue(3, 1, (fun[i + 2][j+1] - fun[i][j+1]));
        }
        else if (i==4) {
            mat.setValue(2, 0, (fun[i + 1][j] - fun[i-1][j]));
            mat.setValue(2, 1, (fun[i + 1][j] - fun[i][j]));
            mat.setValue(3, 0, (fun[i + 1][j+1] - fun[i-1][j+1]));
            mat.setValue(3, 1, (fun[i + 1][j+1] - fun[i][j+1]));
        }
        else {
            mat.setValue(2, 0, (fun[i + 1][j] - fun[i-1][j]));
            mat.setValue(2, 1, (fun[i + 2][j] - fun[i][j]));
            mat.setValue(3, 0, (fun[i + 1][j+1] - fun[i-1][j+1]));
            mat.setValue(3, 1, (fun[i + 2][j+1] - fun[i][j+1]));
        }
    }

    public static void fillMaty (Matrix mat, float[][] fun, int i, int j){
        if (j==0) {
            mat.setValue(0, 2, (fun[i][j+1] - fun[i][j]));
            mat.setValue(0, 3, (fun[i+1][j+1] - fun[i+1][j]));
            mat.setValue(1, 2, (fun[i][j+2] - fun[i][j]));
            mat.setValue(1, 3, (fun[i+1][j+2] - fun[i+1][j]));
        }
        else if (j==4) {
            mat.setValue(0, 2, (fun[i][j+1] - fun[i][j-1]));
            mat.setValue(0, 3, (fun[i+1][j+1] - fun[i+1][j-1]));
            mat.setValue(1, 2, (fun[i][j+1] - fun[i][j]));
            mat.setValue(1, 3, (fun[i+1][j+1] - fun[i+1][j]));
        }
        else {
            mat.setValue(0, 2, (fun[i][j+1] - fun[i][j-1]));
            mat.setValue(0, 3, (fun[i+1][j+1] - fun[i+1][j-1]));
            mat.setValue(1, 2, (fun[i][j+2] - fun[i][j]));
            mat.setValue(1, 3, (fun[i+1][j+2] - fun[i+1][j]));
        }
    }

    public static void fillMatxy (Matrix mat, float[][] fun, int i, int j) {
        if (i==0) {
            if(j==0) {
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j])-(fun[i][j+1] - fun[i][j])));
                mat.setValue(2, 3, ((fun[i+1][j+2] - fun[i+1][j])-(fun[i][j+2] - fun[i][j])));
                mat.setValue(3, 2, ((fun[i+2][j+1] - fun[i+2][j])-(fun[i][j+1] - fun[i][j])));
                mat.setValue(3, 3, ((fun[i+2][j+2] - fun[i+2][j])-(fun[i][j+2] - fun[i][j])));
            }
            else if((j>0)&&(j<4)) {
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j-1])-(fun[i][j+1] - fun[i][j-1])));
                mat.setValue(2, 3, ((fun[i+1][j+2] - fun[i+1][j])-(fun[i][j+2] - fun[i][j])));
                mat.setValue(3, 2, ((fun[i+2][j+1] - fun[i+2][j-1])-(fun[i][j+1] - fun[i][j-1])));
                mat.setValue(3, 3, ((fun[i+2][j+2] - fun[i+2][j])-(fun[i][j+2] - fun[i][j])));
            }
            else {  //j=4
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j-1])-(fun[i][j+1] - fun[i][j-1])));
                mat.setValue(2, 3, ((fun[i+1][j] - fun[i+1][j-1])-(fun[i][j] - fun[i][j-1])));
                mat.setValue(3, 2, ((fun[i+2][j+1] - fun[i+2][j-1])-(fun[i][j+1] - fun[i][j-1])));
                mat.setValue(3, 3, ((fun[i+2][j+1] - fun[i+2][j])-(fun[i][j+1] - fun[i][j])));
            }
        }

        else if((i>0)&&(i<4)) {
            if(j==0) {
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j])-(fun[i-1][j+1] - fun[i-1][j])));
                mat.setValue(2, 3, ((fun[i+1][j+2] - fun[i+1][j])-(fun[i-1][j+2] - fun[i-1][j])));
                mat.setValue(3, 2, ((fun[i+2][j+1] - fun[i+2][j])-(fun[i][j+1] - fun[i][j])));
                mat.setValue(3, 3, ((fun[i+2][j+2] - fun[i+2][j])-(fun[i][j+2] - fun[i][j])));
            }
            else if((j>0)&&(j<4)) {
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j-1])-(fun[i-1][j+1] - fun[i-1][j-1])));
                mat.setValue(2, 3, ((fun[i+1][j+2] - fun[i+1][j])-(fun[i-1][j+2] - fun[i-1][j])));
                mat.setValue(3, 2, ((fun[i+2][j+1] - fun[i+2][j-1])-(fun[i][j+1] - fun[i][j-1])));
                mat.setValue(3, 3, ((fun[i+2][j+2] - fun[i+2][j])-(fun[i][j+2] - fun[i][j])));
            }
            else {  //j=4
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j-1])-(fun[i-1][j+1] - fun[i-1][j-1])));
                mat.setValue(2, 3, ((fun[i+1][j+1] - fun[i+1][j])-(fun[i-1][j+1] - fun[i-1][j])));
                mat.setValue(3, 2, ((fun[i+2][j+1] - fun[i+2][j-1])-(fun[i][j+1] - fun[i][j-1])));
                mat.setValue(3, 3, ((fun[i+2][j+1] - fun[i+2][j])-(fun[i][j+1] - fun[i][j])));
            }
        }
        else {      //i=5
            if(j==0) {
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j])-(fun[i-1][j+1] - fun[i-1][j])));
                mat.setValue(2, 3, ((fun[i+1][j+2] - fun[i+1][j])-(fun[i-1][j+2] - fun[i-1][j])));
                mat.setValue(3, 2, ((fun[i+1][j+1] - fun[i+1][j])-(fun[i][j+1] - fun[i][j])));
                mat.setValue(3, 3, ((fun[i+1][j+2] - fun[i+1][j])-(fun[i][j+2] - fun[i][j])));
            }
            else if((j>0)&&(j<4)) {
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j-1])-(fun[i-1][j+1] - fun[i-1][j-1])));
                mat.setValue(2, 3, ((fun[i+1][j+2] - fun[i+1][j])-(fun[i-1][j+2] - fun[i-1][j])));
                mat.setValue(3, 2, ((fun[i+1][j+1] - fun[i+1][j-1])-(fun[i][j+1] - fun[i][j-1])));
                mat.setValue(3, 3, ((fun[i+1][j+2] - fun[i+1][j])-(fun[i][j+2] - fun[i][j])));
            }
            else {  //j=4
                mat.setValue(2, 2, ((fun[i+1][j+1] - fun[i+1][j-1])-(fun[i-1][j+1] - fun[i-1][j-1])));
                mat.setValue(2, 3, ((fun[i+1][j+1] - fun[i+1][j])-(fun[i-1][j+1] - fun[i-1][j])));
                mat.setValue(3, 2, ((fun[i+1][j+1] - fun[i+1][j-1])-(fun[i][j+1] - fun[i][j-1])));
                mat.setValue(3, 3, ((fun[i+1][j+1] - fun[i+1][j])-(fun[i][j+1] - fun[i][j])));
            }
        }
}

    public static void setCoefficient(float[][] coefficient, Matrix ret) {
        int i,j;
        for (i=0;i<4;i++) {
            for (j=0;j<4;j++) {
                ret.setValue(i,j,coefficient[i][j]);
            }
        }
    }


    public static Matrix[][] getTotalMatrix () {
        return coefficientMatrix;
    }
}