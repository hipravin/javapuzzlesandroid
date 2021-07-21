package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import org.junit.Test;

import static org.junit.Assert.*;

public class Puzzle10DstTest {
    PuzzleTask task = new Puzzle10Dst();

    @Test
    public void testSuccess() {
        PuzzleInput input = new PuzzleInput("Europe/London");
        PuzzleInvocationResult result = task.run(input);

        assertTrue(result.isPassed());
        System.out.println(result.getOutput());
    }

    @Test
    public void testFail1() {
        PuzzleInput input = new PuzzleInput("UTC");
        PuzzleInvocationResult result = task.run(input);

        assertFalse(result.isPassed());
        System.out.println(result.getOutput());
    }
}