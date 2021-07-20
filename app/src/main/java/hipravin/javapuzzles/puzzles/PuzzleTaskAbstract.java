package hipravin.javapuzzles.puzzles;

import java.util.ArrayList;
import java.util.List;

public abstract class PuzzleTaskAbstract implements PuzzleTask {
    private int puzzleId;
    private int titleStringId;
    private int headerStringId;
    private int codeRawId;
    private int cardId;
    private int triesTextId;

    public PuzzleTaskAbstract(int puzzleId, int titleStringId, int headerStringId, int codeRawId, int cardId, int triesTextId) {
        this.puzzleId = puzzleId;
        this.titleStringId = titleStringId;
        this.headerStringId = headerStringId;
        this.codeRawId = codeRawId;
        this.cardId = cardId;
        this.triesTextId = triesTextId;
    }

    @Override
    public int puzzleId() {
        return puzzleId;
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
    public int cardId() {
        return cardId;
    }

    @Override
    public int triesTextId() {
        return triesTextId;
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
