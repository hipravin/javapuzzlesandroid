package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import org.junit.Test;

import static org.junit.Assert.*;

public class Puzzle4iPoolTest {
    PuzzleTask task = new Puzzle4iPool();

    @Test
    public void testSuccess() {
        PuzzleInput input = new PuzzleInput("128");
        PuzzleInvocationResult result = task.run(input);

        assertTrue(result.isPassed());
        System.out.println(result.getOutput());
    }

    @Test
    public void testFail1() {
        PuzzleInput input = new PuzzleInput("123");
        PuzzleInvocationResult result = task.run(input);

        assertFalse(result.isPassed());
        System.out.println(result.getOutput());
    }
}