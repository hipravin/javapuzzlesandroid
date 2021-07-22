package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import org.junit.Test;

import static org.junit.Assert.*;

public class Puzzle12TrimStripTest {
    PuzzleTask task = new Puzzle12TrimStrip();

    @Test
    public void testSuccess() {
        PuzzleInput input = new PuzzleInput("12");
        PuzzleInvocationResult result = task.run(input);

        System.out.println(result.getOutput());
        assertTrue(result.isPassed());
    }

    @Test
    public void testFail1() {
        PuzzleInput input = new PuzzleInput("13");
        PuzzleInvocationResult result = task.run(input);

        System.out.println(result.getOutput());
        assertFalse(result.isPassed());
    }
}