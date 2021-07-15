package hipravin.javapuzzles.puzzles;

import hipravin.javapuzzles.puzzles.impl.Puzzle1LongShift;

public class PuzzleTaskRepository {


    public PuzzleTask getForId(int puzzleNum) {
        switch (puzzleNum) {
            case 1:
                return new Puzzle1LongShift();
            default:
                throw new RuntimeException("Puzzle not found:" + puzzleNum);
        }
    }

}
