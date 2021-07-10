package hipravin.javapuzzles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PuzzleListViewModel extends ViewModel {
    private MutableLiveData<List<PuzzleDescription>> puzzles;

    public LiveData<List<PuzzleDescription>> getPuzzles() {
        if (puzzles == null) {
            puzzles = new MutableLiveData<>();
            loadPuzzles();
        }
        return puzzles;
    }

    private void loadPuzzles() {
        puzzles.postValue(samplePussles());
    }

    private static List<PuzzleDescription> samplePussles() {
        List<PuzzleDescription> puzzles = new ArrayList<>();

        puzzles.add(new PuzzleDescription("1. Boolean"));
        puzzles.add(new PuzzleDescription("2. Boolean"));
        puzzles.add(new PuzzleDescription("3. String"));
        puzzles.add(new PuzzleDescription("4. BigDecimal"));

        return puzzles;
    }


}
