package hipravin.javapuzzles.puzzles;

public interface PuzzleTask {

    int titleStringId();
    int headerStringId();
    int codeRawId();
    int promptStringId();
    int solutionRawId();

    PuzzleInvocationResult run(PuzzleInput puzzleInput);

}
