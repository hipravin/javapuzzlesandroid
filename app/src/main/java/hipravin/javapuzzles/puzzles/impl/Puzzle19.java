package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;

public class Puzzle19 extends PuzzleTaskAbstract {
    public Puzzle19() {
        super(19, R.string.puzzle19title, R.string.commonTaskDescription,
                R.raw.puzzlecode19, R.id.puzzleCard19, R.id.puzzleTriesText19);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        boolean passed = false;
        return new PuzzleInvocationResult(passed, outputAccumulator);

    }
}
