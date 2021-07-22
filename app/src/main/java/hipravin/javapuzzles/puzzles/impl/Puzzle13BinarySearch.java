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
import java.util.Random;

public class Puzzle13BinarySearch extends PuzzleTaskAbstract {
    public Puzzle13BinarySearch() {
        super(13, R.string.puzzle13title, R.string.commonTaskDescription,
                R.raw.puzzlecode13, R.id.puzzleCard13, R.id.puzzleTriesText13);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        int actual = Integer.parseInt(puzzleInput.getInput());

        int expected =
                new Random(LocalDate.now().hashCode())
                        .nextInt();

        if (actual < expected) {
            outputAccumulator.add("actual < expected");
        } else {
            outputAccumulator.add("actual >= expected");
        }
        boolean passed = actual == expected;

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
