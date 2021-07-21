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

public class Puzzle12 extends PuzzleTaskAbstract {
    public Puzzle12() {
        super(12, R.string.puzzle12title, R.string.commonTaskDescription,
                R.raw.puzzlecode12, R.id.puzzleCard12, R.id.puzzleTriesText12);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        boolean passed = false;
        return new PuzzleInvocationResult(passed, outputAccumulator);

    }
}
