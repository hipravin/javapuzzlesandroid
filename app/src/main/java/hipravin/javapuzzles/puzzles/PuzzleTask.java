package hipravin.javapuzzles.puzzles;

public interface PuzzleTask {

    int titleStringId();
    int headerStringId();
    int codeRawId();

    PuzzleInvocationResult run(PuzzleInput puzzleInput);

}
