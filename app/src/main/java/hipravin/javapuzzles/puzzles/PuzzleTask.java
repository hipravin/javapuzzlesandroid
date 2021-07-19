package hipravin.javapuzzles.puzzles;

public interface PuzzleTask {

    int puzzleId();
    int titleStringId();
    int headerStringId();
    int codeRawId();
    int cardId();

    default String puzzleIdString() {
        return String.valueOf(puzzleId());
    }

    PuzzleInvocationResult run(PuzzleInput puzzleInput);

}
