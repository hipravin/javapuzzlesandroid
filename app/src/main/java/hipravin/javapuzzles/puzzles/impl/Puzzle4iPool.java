package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;
import java.util.Scanner;

public class Puzzle4iPool extends PuzzleTaskAbstract {
    public Puzzle4iPool() {
        super(4, R.string.puzzle4title, R.string.commonTaskDescription,
                R.raw.puzzlecode4, R.id.puzzleCard4, R.id.puzzleTriesText4);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        int i = Integer.parseInt(puzzleInput.getInput());

        Long l1 = (long) i;
        Long l2 = (long) i;
        Long l3 = (long) i - 1;
        Long l4 = (long) i - 1;

        outputAccumulator.add("l1!=l2: " + (l1 != l2));
        outputAccumulator.add("l3==l4: " + (l3==l4));

        boolean passed = l1 != l2 && l3 == l4;

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
