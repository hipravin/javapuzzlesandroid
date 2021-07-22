package hipravin.javapuzzles.puzzles.impl;

import hipravin.javapuzzles.R;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTaskAbstract;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Puzzle11Paths extends PuzzleTaskAbstract {
    public Puzzle11Paths() {
        super(11, R.string.puzzle11title, R.string.commonTaskDescription,
                R.raw.puzzlecode11, R.id.puzzleCard11, R.id.puzzleTriesText11);
    }

    @Override
    protected PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator) {
        String qValue = puzzleInput.getInput();

        Path p = Paths.get("/var/log");
        Path q = Paths.get(qValue);

        Path qq = p.relativize(p.resolve(q));

        outputAccumulator.add("p res q: " + p.resolve(q));
        outputAccumulator.add("qq: " + qq);

        boolean passed = !q.equals(qq);
        return new PuzzleInvocationResult(passed, outputAccumulator);
    }
}
