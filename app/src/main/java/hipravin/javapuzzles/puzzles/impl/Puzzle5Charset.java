package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Puzzle5Charset extends PuzzleTaskAbstract {
    public Puzzle5Charset() {
        super(5, R.string.puzzle5title, R.string.commonTaskDescription,
                R.raw.puzzlecode5, R.id.puzzleCard5, R.id.puzzleTriesText5);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        String s = puzzleInput.getInput();
        String s2 = new String(s.getBytes(), StandardCharsets.US_ASCII);

        outputAccumulator.add("s2: " + s2);

        boolean passed = !s.equals(s2);

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
