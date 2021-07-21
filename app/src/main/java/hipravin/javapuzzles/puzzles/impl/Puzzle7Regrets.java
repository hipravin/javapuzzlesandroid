package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;

public class Puzzle7Regrets extends PuzzleTaskAbstract {
    public Puzzle7Regrets() {
        super(7, R.string.puzzle7title, R.string.regexpRegrets,
                R.raw.puzzlecode7, R.id.puzzleCard7, R.id.puzzleTriesText7);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        String s = puzzleInput.getInput();
        String s2 = (s + s).replaceAll(s, s);

        outputAccumulator.add("s2: " + s2);

        boolean passed = !s.isEmpty() && s.equals(s2);

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
