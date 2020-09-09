package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils;

public class SudokuGenerator {
    public int[] mat[];
    private int N; // number of columns/rows.
    private int rowCount, colCount; // square root of N


    public SudokuGenerator(int N) {
        this.N = N;

        mat = new int[N][N];
    }

    // Constructor
    public SudokuGenerator(int N, int x, int y) {
        this.N = N;
        rowCount = x;
        colCount = y;
        mat = new int[N][N];
    }


    public boolean fillSolution() {
        // Fill the diagonal of rowCount x colCount matrices
        fillDiagonal();

        // Fill remaining blocks
        return fillRemaining(0, colCount);
    }

    // Fill the diagonal rowCount number of rowCount x colCount matrices
    private void fillDiagonal() {

        for (int i = 0, j = 0; i < N; i = i + rowCount, j = j + colCount) {
            // for diagonal box, start coordinates->i==j
            if (j >= N) break;
            fillBox(i, j);
            if (N == 4) {
                break;
            }
        }
    }


    // Returns false if given rowCount x colCount block contains num.
    private boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < rowCount; i++)
            for (int j = 0; j < colCount; j++)
                if (mat[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }


    // Fill a rowCount x colCount matrix.
    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                do {
                    num = randomGenerator(N);
                }
                while (!unUsedInBox(row, col, num));
                mat[row + i][col + j] = num;
            }
        }
    }


    // Random generator
    private int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }


    // Check if safe to put in cell
    private boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % rowCount, j - j % colCount, num));
    }


    // check in the row for existence
    private boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < N; j++)
            if (mat[i][j] == num)
                return false;
        return true;
    }


    // check in the row for existence
    private boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < N; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }


    // A recursive function to fill remaining matrix
    private boolean fillRemaining(int i, int j) {
        if (i >= N - 1 && j >= N)
            return true;

        if (j >= N && i < N - 1) {
            i = i + 1;
            j = 0;
        }
        if (mat[i][j] > 0) {
            return fillRemaining(i, j + 1);
        } else {
            for (int num = 1; num <= N; num++) {
                if (CheckIfSafe(i, j, num)) {
                    mat[i][j] = num;
                    if (fillRemaining(i, j + 1)) return true;
                    mat[i][j] = 0;
                }
            }
        }
        return false;
    }


    // Remove the K no. of digits to complete game (edited)
    public void removeKDigits(int count) {
        //int count = K;
        while (count != 0) {
            int cellId = randomGenerator(N * N);
            int cellId2 = randomGenerator(N * N);

            // extract coordinates i  and j
            int i = cellId % N;
            int j = cellId2 % N;

            if (mat[i][j] != 0) {
                count--;
                mat[i][j] = 0;
            }
        }
    }

    // Print sudoku
    public void printSudoku() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}





