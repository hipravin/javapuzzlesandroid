package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Puzzle10Dst extends PuzzleTaskAbstract {
    public Puzzle10Dst() {
        super(10, R.string.puzzle10title, R.string.commonTaskDescription,
                R.raw.puzzlecode10, R.id.puzzleCard10, R.id.puzzleTriesText10);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        String zoneId = puzzleInput.getInput();
        ZonedDateTime zdt1 =
                LocalDate.of(2021, Month.JANUARY, 1)
                        .atStartOfDay(ZoneId.of(zoneId));

        ZonedDateTime zdt2 =
                LocalDate.of(2021, Month.JULY, 1)
                        .atStartOfDay(ZoneId.of(zoneId));

        outputAccumulator.add("ztd1offset:" + zdt1.getOffset());
        outputAccumulator.add("ztd2offset:" + zdt2.getOffset());

        boolean passed = !zdt1.getOffset().equals(zdt2.getOffset());
        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
