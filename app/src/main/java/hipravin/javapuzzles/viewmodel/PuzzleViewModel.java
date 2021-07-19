package hipravin.javapuzzles.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import hipravin.javapuzzles.PuzzleDescription;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import hipravin.javapuzzles.puzzles.PuzzleTaskRepository;

import java.util.ArrayList;
import java.util.List;

public class PuzzleViewModel extends ViewModel {
    private MutableLiveData<ViewState> viewState;
    private MutableLiveData<String> lastSolvedPuzzleId;
    private String puzzleId;
    private PuzzleTask puzzleTask;

    public synchronized LiveData<String> getLastSolvedPuzzleId() {
        if (lastSolvedPuzzleId == null) {
            lastSolvedPuzzleId = new MutableLiveData<>();
        }
        return lastSolvedPuzzleId;
    }

    public void setLastSolvedPuzzleId(String puzzleId) {
        if (lastSolvedPuzzleId == null) {
            lastSolvedPuzzleId = new MutableLiveData<>();
        }
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
        this.puzzleId = null;
        this.puzzleTask = null;
        viewState.postValue(ViewState.PUZZLE_LIST);
    }

    public synchronized void setViewStatePuzzle(String puzzleId, PuzzleTask puzzleTask) {
        this.puzzleId = puzzleId;
        this.puzzleTask = puzzleTask;
        viewState.postValue(ViewState.PUZZLE_TASK);
    }

    public synchronized String getPuzzleId() {
        return puzzleId;
    }

    public synchronized PuzzleTask getPuzzleTask() {
        return puzzleTask;
    }
}
