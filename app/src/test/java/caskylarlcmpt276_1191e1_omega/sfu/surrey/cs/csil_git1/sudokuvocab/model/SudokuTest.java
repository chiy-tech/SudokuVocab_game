package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import org.junit.Test;

import caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils.SudokuGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SudokuTest {

    @Test
    public void generate4SudokuTest() {
        SudokuGenerator generator = new SudokuGenerator(4,2,2);
        generator.fillSolution();
        int pluzzNum = 4;
        generator.removeKDigits(pluzzNum);
        int[][] mat = generator.mat;
        int emptyCount = 0;
        for (int[] aMat : mat) {
            for (int anAMat : aMat) {
                if (anAMat == 0) emptyCount++;
            }
        }
        assertEquals(emptyCount,pluzzNum);
    }

    @Test
    public void generate6SudokuTest() {
        SudokuGenerator generator = new SudokuGenerator(6,2,3);
        generator.fillSolution();
        int pluzzNum = 10;
        generator.removeKDigits(pluzzNum);
        int[][] mat = generator.mat;
        int emptyCount = 0;
        for (int[] aMat : mat) {
            for (int anAMat : aMat) {
                if (anAMat == 0) emptyCount++;
            }
        }
        assertEquals(emptyCount,pluzzNum);
    }

    @Test
    public void generate9SudokuTest() {
        SudokuGenerator generator = new SudokuGenerator(9,3,3);
        generator.fillSolution();
        int pluzzNum = 13;
        generator.removeKDigits(pluzzNum);
        int[][] mat = generator.mat;
        int emptyCount = 0;
        for (int[] aMat : mat) {
            for (int anAMat : aMat) {
                if (anAMat == 0) emptyCount++;
            }
        }
        assertEquals(emptyCount,pluzzNum);
    }

    @Test
    public void generate12SudokuTest() {
        SudokuGenerator generator = new SudokuGenerator(12,3,4);
        generator.fillSolution();
        int pluzzNum = 14;
        generator.removeKDigits(pluzzNum);
        int[][] mat = generator.mat;
        int emptyCount = 0;
        for (int[] aMat : mat) {
            for (int anAMat : aMat) {
                if (anAMat == 0) emptyCount++;
            }
        }
        assertEquals(emptyCount,pluzzNum);
    }

    @Test
    public void solve4SudokuTest() {
        SudokuGenerator generator = new SudokuGenerator(4,2,2);
        generator.fillSolution();
        int[][] mat = generator.mat;
        assertEquals(mat.length,4);
        assertEquals(mat[0].length,4);
        for (int[] aMat : mat) {
            for (int anAMat : aMat) {
                assertNotEquals(anAMat, 0);
            }
        }
    }

    @Test
    public void solve6SudokuTest() {
        SudokuGenerator generator = new SudokuGenerator(6,2,3);
        generator.fillSolution();
        int[][] mat = generator.mat;
        assertEquals(mat.length,6);
        assertEquals(mat[0].length,6);
        for (int[] aMat : mat) {
            for (int anAMat : aMat) {
                assertNotEquals(anAMat, 0);
            }
        }
    }

    @Test
    public void solve9SudokuTest() {
        SudokuGenerator generator = new SudokuGenerator(9,3,3);
        generator.fillSolution();
        int[][] mat = generator.mat;
        assertEquals(mat.length,9);
        assertEquals(mat[0].length,9);
        for (int[] aMat : mat) {
            for (int anAMat : aMat) {
                assertNotEquals(anAMat, 0);
            }
        }
    }

    @Test
    public void solve12SudokuTest() {
        SudokuGenerator generator = new SudokuGenerator(12,3,4);
        generator.fillSolution();
        int[][] mat = generator.mat;
        assertEquals(mat.length,12);
        assertEquals(mat[0].length,12);
        for (int[] aMat : mat) {
            for (int anAMat : aMat) {
                assertNotEquals(anAMat, 0);
            }
        }
    }


}