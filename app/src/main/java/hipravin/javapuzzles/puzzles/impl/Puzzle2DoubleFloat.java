package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;

public class Puzzle2DoubleFloat extends PuzzleTaskAbstract {
    public Puzzle2DoubleFloat() {
        super(2, R.string.puzzle2title, R.string.commonTaskDescription,
                R.raw.puzzlecode2, R.id.puzzleCard2, R.id.puzzleTriesText2);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        int i = Integer.parseInt(puzzleInput.getInput());
        double x = i / 1000.0 - i / 1000;

        outputAccumulator.add(("x: " + x));

        boolean passed = x != 0 && (float) x == x;

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
