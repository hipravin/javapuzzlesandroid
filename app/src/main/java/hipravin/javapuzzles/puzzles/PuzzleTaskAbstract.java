package hipravin.javapuzzles.puzzles;

import java.util.ArrayList;
import java.util.List;

public abstract class PuzzleTaskAbstract implements PuzzleTask {
    private int titleStringId;
    private int headerStringId;
    private int codeRawId;
    private int prompStringId;
    private int solutionRawId;

    public PuzzleTaskAbstract(int titleStringId, int headerStringId, int codeRawId, int prompStringId, int solutionRawId) {
        this.titleStringId = titleStringId;
        this.headerStringId = headerStringId;
        this.codeRawId = codeRawId;
        this.prompStringId = prompStringId;
        this.solutionRawId = solutionRawId;
    }

    @Override
    public int titleStringId() {
        return titleStringId;
    }

    @Override
    public int headerStringId() {
        return headerStringId;
    }

    @Override
    public int codeRawId() {
        return codeRawId;
    }

    @Override
    public int promptStringId() {
        return prompStringId;
    }

    @Override
    public int solutionRawId() {
        return solutionRawId;
    }

    protected abstract PuzzleInvocationResult runInternal(PuzzleInput puzzleInput, List<String> outputAccumulator);

    @Override
    public PuzzleInvocationResult run(PuzzleInput puzzleInput) {
        List<String> output = new ArrayList<>();
        try {
            output.add(puzzleInput.getInput());
            PuzzleInvocationResult pir = runInternal(puzzleInput, output);
            if(pir.passed) {
                output.add("passed");
            } else {
                output.add("failed");
            }
            return pir;
        } catch (RuntimeException e) {
            output.add(e.toString());
            output.add("failed");
            return new PuzzleInvocationResult(false, output);
        }
    }
}
