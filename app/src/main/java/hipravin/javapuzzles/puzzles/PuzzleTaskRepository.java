package hipravin.javapuzzles.puzzles;

import hipravin.javapuzzles.puzzles.impl.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PuzzleTaskRepository {
    private static final PuzzleTaskRepository INSTANCE = new PuzzleTaskRepository();
    private final Map<Integer, PuzzleTask> puzzleTasks = new ConcurrentHashMap<>();

    private PuzzleTaskRepository() {
        puzzleTasks.put(0, new Puzzle0Tutorial());
        puzzleTasks.put(1, new Puzzle1LongShift());
        puzzleTasks.put(2, new Puzzle2DoubleFloat());
        puzzleTasks.put(3, new Puzzle3iSquare());
        puzzleTasks.put(4, new Puzzle4iPool());
        puzzleTasks.put(5, new Puzzle5Charset());
        puzzleTasks.put(6, new Puzzle6Reverse());
        puzzleTasks.put(7, new Puzzle7Regrets());
        puzzleTasks.put(8, new Puzzle8LeapYear());
        puzzleTasks.put(9, new Puzzle9YYYY());
        puzzleTasks.put(10, new Puzzle10Dst());
        puzzleTasks.put(11, new Puzzle11());
        puzzleTasks.put(12, new Puzzle12());
        puzzleTasks.put(13, new Puzzle13());
        puzzleTasks.put(14, new Puzzle14());
        puzzleTasks.put(15, new Puzzle15());
        puzzleTasks.put(16, new Puzzle16());
        puzzleTasks.put(17, new Puzzle17());
        puzzleTasks.put(18, new Puzzle18());
        puzzleTasks.put(19, new Puzzle19());
    }

    public Map<Integer, PuzzleTask> getPuzzleTasks() {
        return puzzleTasks;
    }

    public static PuzzleTaskRepository getInstance() {
        return INSTANCE;
    }

    public PuzzleTask getForId(int puzzleNum) {
        return puzzleTasks.get(puzzleNum);
    }
    public PuzzleTask getForId(String puzzleId) {
        return getForId(Integer.parseInt(puzzleId));
    }

}
