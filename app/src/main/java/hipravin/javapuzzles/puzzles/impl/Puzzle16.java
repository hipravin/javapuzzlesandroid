package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;

public class Puzzle16 extends PuzzleTaskAbstract {
    public Puzzle16() {
        super(16, R.string.puzzle16title, R.string.commonTaskDescription,
                R.raw.puzzlecode16, R.id.puzzleCard16, R.id.puzzleTriesText16);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        boolean passed = false;
        return new PuzzleInvocationResult(passed, outputAccumulator);

    }
}
