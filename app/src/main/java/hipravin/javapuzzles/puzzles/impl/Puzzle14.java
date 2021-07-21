package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Puzzle14 extends PuzzleTaskAbstract {
    public Puzzle14() {
        super(14, R.string.puzzle14title, R.string.commonTaskDescription,
                R.raw.puzzlecode14, R.id.puzzleCard14, R.id.puzzleTriesText14);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        boolean passed = false;
        return new PuzzleInvocationResult(passed, outputAccumulator);

    }
}
