package hipravin.javapuzzles.puzzles;

public interface PuzzleTask {

    int headerStringId();
    int codeRawId();
    int promptStringId();

    PuzzleInvocationResult run(PuzzleInput puzzleInput);

}
