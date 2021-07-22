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
import java.util.Scanner;

public class Puzzle12TrimStrip extends PuzzleTaskAbstract {
    public Puzzle12TrimStrip() {
        super(12, R.string.puzzle12title, R.string.commonTaskDescription,
                R.raw.puzzlecode12, R.id.puzzleCard12, R.id.puzzleTriesText12);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        int len = Integer.parseInt(puzzleInput.getInput());

        String s = " Hello, nbsp\u00A0";

        String trim = s.trim();

        outputAccumulator.add("s_: '" + s + "'");
        outputAccumulator.add("st: '" + trim + "'");

        boolean passed = len == trim.length();

        return new PuzzleInvocationResult(passed, outputAccumulator);

    }
}
