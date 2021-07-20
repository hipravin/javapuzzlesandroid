package hipravin.javapuzzles.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "PUZZLE_STATS")
public class PuzzleStatsEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "PUZZLE_ID")
    private String puzzleId;
    @ColumnInfo(name = "TRIES_BEFORE_PASSED")
    private int triesBeforePassed;
    @ColumnInfo(name = "PASSED")
    private boolean passed;

    public PuzzleStatsEntity() {
    }

    @Ignore
    public PuzzleStatsEntity(@NonNull String puzzleId, int triesBeforePassed, boolean passed) {
        this.puzzleId = puzzleId;
        this.triesBeforePassed = triesBeforePassed;
        this.passed = passed;
    }

    public PuzzleStatsEntity passed() {
        return new PuzzleStatsEntity(puzzleId, triesBeforePassed + 1, true);
    }

    public PuzzleStatsEntity tried() {
        int tries = passed ? triesBeforePassed : triesBeforePassed + 1;
        return new PuzzleStatsEntity(puzzleId, tries, passed);
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(String puzzleId) {
        this.puzzleId = puzzleId;
    }

    public int getTriesBeforePassed() {
        return triesBeforePassed;
    }

    public void setTriesBeforePassed(int triesBeforePassed) {
        this.triesBeforePassed = triesBeforePassed;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
