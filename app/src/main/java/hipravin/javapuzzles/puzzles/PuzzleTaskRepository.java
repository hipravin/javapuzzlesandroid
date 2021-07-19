package hipravin.javapuzzles.puzzles;

import hipravin.javapuzzles.puzzles.impl.Puzzle1LongShift;

public class PuzzleTaskRepository {
    private static final PuzzleTaskRepository INSTANCE = new PuzzleTaskRepository();

    private PuzzleTaskRepository() {
    }

    public static PuzzleTaskRepository getInstance() {
        return INSTANCE;
    }

    public PuzzleTask getForId(int puzzleNum) {
        switch (puzzleNum) {
            case 1:
                return new Puzzle1LongShift();
            default:
                throw new RuntimeException("Puzzle not found:" + puzzleNum);
        }
    }
    public PuzzleTask getForId(String puzzleId) {
        return getForId(Integer.parseInt(puzzleId));
    }

}
