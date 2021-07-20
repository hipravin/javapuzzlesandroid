package hipravin.javapuzzles.puzzles;

import hipravin.javapuzzles.puzzles.impl.Puzzle0Tutorial;
import hipravin.javapuzzles.puzzles.impl.Puzzle1LongShift;
import hipravin.javapuzzles.puzzles.impl.Puzzle2DoubleFloat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PuzzleTaskRepository {
    private static final PuzzleTaskRepository INSTANCE = new PuzzleTaskRepository();
    private final Map<Integer, PuzzleTask> puzzleTasks = new ConcurrentHashMap<>();

    private PuzzleTaskRepository() {
        puzzleTasks.put(0, new Puzzle0Tutorial());
        puzzleTasks.put(1, new Puzzle1LongShift());
        puzzleTasks.put(2, new Puzzle2DoubleFloat());
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
