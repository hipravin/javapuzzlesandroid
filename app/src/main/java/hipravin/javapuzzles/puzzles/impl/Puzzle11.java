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

public class Puzzle11 extends PuzzleTaskAbstract {
    public Puzzle11() {
        super(10, R.string.puzzle10title, R.string.commonTaskDescription,
                R.raw.puzzlecode10, R.id.puzzleCard10, R.id.puzzleTriesText10);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {

        boolean passed = false;
        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
