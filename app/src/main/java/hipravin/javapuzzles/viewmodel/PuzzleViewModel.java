package hipravin.javapuzzles.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import hipravin.javapuzzles.db.PuzzleStatsEntity;
import hipravin.javapuzzles.puzzles.PuzzleTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class PuzzleViewModel extends ViewModel {
    private MutableLiveData<ViewState> viewState;
    private MutableLiveData<String> lastSolvedPuzzleId;
    private MutableLiveData<String> lastTriedPuzzleId;
    private PuzzleTask puzzleTask;
    private MutableLiveData<Map<String, PuzzleStatsEntity>> puzzleStats = null;

    public synchronized LiveData<Map<String, PuzzleStatsEntity>> getPuzzleStats() {
        if(puzzleStats == null) {
            puzzleStats = new MutableLiveData<>();
            puzzleStats.setValue(defaultPuzzleStats());
        }
        return puzzleStats;
    }

    public synchronized void postPuzzleStatsValue(Map<String, PuzzleStatsEntity> value) {
        if(puzzleStats == null) {
            puzzleStats = new MutableLiveData<>();
        }

        puzzleStats.postValue(value);
    }

    public synchronized Map<String, PuzzleStatsEntity> getPuzzleStatsValue() {
        if(puzzleStats == null) {
            puzzleStats = new MutableLiveData<>();
        }
        return puzzleStats.getValue();
    }

    public synchronized LiveData<String> getLastTriedPuzzleId() {
        if (lastTriedPuzzleId == null) {
            lastTriedPuzzleId = new MutableLiveData<>();
        }
        return lastTriedPuzzleId;
    }

    public synchronized void setLastTriedPuzzleId(String puzzleId) {
        if (lastTriedPuzzleId == null) {
            lastTriedPuzzleId = new MutableLiveData<>();
        }
        getPuzzleStats().getValue().computeIfPresent(puzzleId, (id, pse) -> pse.tried());
        lastTriedPuzzleId.postValue(puzzleId);
    }

    public synchronized LiveData<String> getLastSolvedPuzzleId() {
        if (lastSolvedPuzzleId == null) {
            lastSolvedPuzzleId = new MutableLiveData<>();
        }
        return lastSolvedPuzzleId;
    }

    public synchronized void setLastSolvedPuzzleId(String puzzleId) {
        if (lastSolvedPuzzleId == null) {
            lastSolvedPuzzleId = new MutableLiveData<>();
        }
        if(puzzleStats == null) {
            puzzleStats = new MutableLiveData<>();
        }
        getPuzzleStats().getValue().computeIfPresent(puzzleId, (id, pse) -> pse.passed());

        lastSolvedPuzzleId.postValue(puzzleId);
    }

    public synchronized LiveData<ViewState> getViewState() {
        if (viewState == null) {
            viewState = new MutableLiveData<>();
            viewState.postValue(ViewState.PUZZLE_LIST);
        }
        return viewState;
    }

    public synchronized void setViewStatePuzzleList() {
        this.puzzleTask = null;
        viewState.postValue(ViewState.PUZZLE_LIST);
    }

    public synchronized void setViewStatePuzzle(String puzzleId, PuzzleTask puzzleTask) {
        this.puzzleTask = puzzleTask;
        viewState.postValue(ViewState.PUZZLE_TASK);
    }

     public synchronized PuzzleTask getPuzzleTask() {
        return puzzleTask;
    }

    public static Map<String, PuzzleStatsEntity> defaultPuzzleStats() {
        Map<String, PuzzleStatsEntity> result = new HashMap<>();

        result.put("1", new PuzzleStatsEntity("1", 0, false));

        return result;
    }
}
