package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;

public class Puzzle3iSquare extends PuzzleTaskAbstract {
    public Puzzle3iSquare() {
        super(3, R.string.puzzle3title, R.string.commonTaskDescription,
                R.raw.puzzlecode3, R.id.puzzleCard3, R.id.puzzleTriesText3);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        int i = Integer.parseInt(puzzleInput.getInput());

        outputAccumulator.add("i*i: " + i * i);

        boolean passed = i != 0 && i * i == 0;

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
