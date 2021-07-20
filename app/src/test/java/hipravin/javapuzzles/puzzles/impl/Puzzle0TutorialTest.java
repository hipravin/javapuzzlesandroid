package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import org.junit.Test;

import static org.junit.Assert.*;

public class Puzzle0TutorialTest {
    PuzzleTask task = new Puzzle0Tutorial();

    @Test
    public void testSuccess() {
        PuzzleInput input = new PuzzleInput("Tutorial");
        PuzzleInvocationResult result = task.run(input);

        assertTrue(result.isPassed());
        System.out.println(result.getOutput());
    }

    @Test
    public void testFail1() {
        PuzzleInput input = new PuzzleInput("1234125234234234");
        PuzzleInvocationResult result = task.run(input);

        assertFalse(result.isPassed());
        System.out.println(result.getOutput());

    }
}