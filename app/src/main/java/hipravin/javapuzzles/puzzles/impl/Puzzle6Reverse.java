package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Puzzle6Reverse extends PuzzleTaskAbstract {
    public Puzzle6Reverse() {
        super(6, R.string.puzzle6title, R.string.commonTaskDescription,
                R.raw.puzzlecode6, R.id.puzzleCard6, R.id.puzzleTriesText6);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        String s = puzzleInput.getInput();
        String s1 = new StringBuilder(s).reverse().toString();

        outputAccumulator.add("s1: " + s1);

        boolean passed = s.length() > 3
                && s.intern() == s1.intern();

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
