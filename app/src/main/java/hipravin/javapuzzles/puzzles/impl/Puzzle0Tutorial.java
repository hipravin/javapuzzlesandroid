package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.util.List;
import java.util.Scanner;

public class Puzzle0Tutorial extends PuzzleTaskAbstract {
    public Puzzle0Tutorial() {
        super(0, R.string.puzzle0title, R.string.tutorialTaskDescription,
                R.raw.puzzlecode0, R.id.puzzleCard0, R.id.puzzleTriesText0);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        String input = puzzleInput.getInput();

        outputAccumulator.add(("input: " + input));

        boolean passed = input.equals("Tutorial");

        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
