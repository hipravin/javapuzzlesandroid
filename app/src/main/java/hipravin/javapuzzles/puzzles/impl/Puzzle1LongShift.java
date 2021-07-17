package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;

public class Puzzle1LongShift extends PuzzleTaskAbstract {
    public Puzzle1LongShift() {
        super(R.string.puzzle1title, R.string.commonTaskDescription,
                R.raw.puzzlecode1, R.string.puzzle1prompt, 0);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        long l = Long.parseLong(puzzleInput.getInput());
        long l2 = l >> 63;

        outputAccumulator.add(("l bin: " + Long.toBinaryString(l)));
        outputAccumulator.add(("l2 bin: " + Long.toBinaryString(l2)));

        boolean passed = l2 != 0;

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
