package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;

public class Puzzle18 extends PuzzleTaskAbstract {
    public Puzzle18() {
        super(18, R.string.puzzle18title, R.string.commonTaskDescription,
                R.raw.puzzlecode18, R.id.puzzleCard18, R.id.puzzleTriesText18);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        boolean passed = false;
        return new PuzzleInvocationResult(passed, outputAccumulator);

    }
}
