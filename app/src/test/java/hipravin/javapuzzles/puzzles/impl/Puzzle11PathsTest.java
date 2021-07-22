package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import org.junit.Test;

import static org.junit.Assert.*;

public class Puzzle11PathsTest {
    PuzzleTask task = new Puzzle11Paths();

    @Test
    public void testSuccess() {
        PuzzleInput input = new PuzzleInput("a/../b");
        PuzzleInvocationResult result = task.run(input);

        System.out.println(result.getOutput());
        assertTrue(result.isPassed());
    }

    @Test
    public void testFail1() {
        PuzzleInput input = new PuzzleInput("a/b/c");
        PuzzleInvocationResult result = task.run(input);

        System.out.println(result.getOutput());
        assertFalse(result.isPassed());
    }
}