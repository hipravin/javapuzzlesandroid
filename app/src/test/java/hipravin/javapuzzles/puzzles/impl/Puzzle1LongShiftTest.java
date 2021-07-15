package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Puzzle1LongShiftTest {
    PuzzleTask task = new Puzzle1LongShift();

    @Test
    public void testSuccess() {
        PuzzleInput input = new PuzzleInput("-123");
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
    @Test
    public void testFail2() {
        PuzzleInput input = new PuzzleInput("12345");
        PuzzleInvocationResult result = task.run(input);

        assertFalse(result.isPassed());
        System.out.println(result.getOutput());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        boolean passed = test(Long.parseLong(input));

        System.out.println(passed ? "passed" : "failed");
    }

    static boolean test(long l) {
        long l2 = l >> 63;

        System.out.println("l bin: " + Long.toBinaryString(l));
        System.out.println("l2 bin: " + Long.toBinaryString(l2));

        return l2 != 0;
    }
}