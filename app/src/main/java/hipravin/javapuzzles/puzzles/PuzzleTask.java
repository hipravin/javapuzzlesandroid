package hipravin.javapuzzles.puzzles;

public interface PuzzleTask {

    int puzzleId();
    int titleStringId();
    int headerStringId();
    int codeRawId();
    int cardId();
    int triesTextId();

    default String puzzleIdString() {
        return String.valueOf(puzzleId());
    }

    PuzzleInvocationResult run(PuzzleInput puzzleInput);

}
