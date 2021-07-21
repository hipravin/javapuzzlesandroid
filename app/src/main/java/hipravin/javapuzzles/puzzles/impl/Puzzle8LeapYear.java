package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Puzzle8LeapYear extends PuzzleTaskAbstract {
    public Puzzle8LeapYear() {
        super(8, R.string.puzzle8title, R.string.commonTaskDescription,
                R.raw.puzzlecode8, R.id.puzzleCard8, R.id.puzzleTriesText8);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        int year = Integer.parseInt(puzzleInput.getInput());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String formatted = dtf.format(LocalDate.of(year, 2,29));

        outputAccumulator.add("formatted:" + formatted);

        boolean passed = formatted.contains(String.valueOf(year));

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
