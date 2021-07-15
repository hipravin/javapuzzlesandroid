package hipravin.javapuzzles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import hipravin.javapuzzles.puzzles.PuzzleTaskRepository;

import java.util.ArrayList;
import java.util.List;

public class PuzzleViewModel extends ViewModel {
    private MutableLiveData<List<PuzzleDescription>> puzzles;
    private String activePuzzleId = null;

    public LiveData<List<PuzzleDescription>> getPuzzles() {
        if (puzzles == null) {
            puzzles = new MutableLiveData<>();
            loadPuzzles();
        }
        return puzzles;
    }

    private void loadPuzzles() {
        puzzles.postValue(allPuzzles());
    }

    private static List<PuzzleDescription> allPuzzles() {
        List<PuzzleDescription> puzzles = new ArrayList<>();
        PuzzleTaskRepository puzzleTaskRepository = new PuzzleTaskRepository();

        puzzles.add(ofPuzzleId(puzzleTaskRepository,"1"));

        return puzzles;
    }

    private static PuzzleDescription ofPuzzleId(PuzzleTaskRepository puzzleTaskRepository, String puzzleId) {
        PuzzleTask puzzleTask = puzzleTaskRepository.getForId(puzzleId);

        return new PuzzleDescription(puzzleId, puzzleTask.titleStringId());
    }

    public String getActivePuzzleId() {
        return activePuzzleId;
    }

    public void setActivePuzzleId(String activePuzzleId) {
        this.activePuzzleId = activePuzzleId;
    }
}
