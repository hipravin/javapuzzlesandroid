package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Puzzle9YYYY extends PuzzleTaskAbstract {
    public Puzzle9YYYY() {
        super(9, R.string.puzzle9title, R.string.commonTaskDescription,
                R.raw.puzzlecode9, R.id.puzzleCard9, R.id.puzzleTriesText9);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        int year = Integer.parseInt(puzzleInput.getInput());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String formatted = dtf.format(
                LocalDate.of(year, 12,30));

        outputAccumulator.add("formatted:" + formatted);

        boolean passed = !formatted.contains(String.valueOf(year));

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
