package hipravin.javapuzzles.db;

import androidx.room.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dao
public interface PuzzleStatsDao {
    @Query("SELECT * FROM PUZZLE_STATS")
    List<PuzzleStatsEntity> loadAll();

    @Query("SELECT * FROM PUZZLE_STATS WHERE PUZZLE_ID IN (:puzzleIds)")
    List<PuzzleStatsEntity> loadAllByIds(String[] puzzleIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PuzzleStatsEntity... puzzleStats);

    @Delete
    void delete(PuzzleStatsEntity puzzleStatsEntity);

    default Map<String, PuzzleStatsEntity> getAllWithDefaults(Map<String, PuzzleStatsEntity> defaults) {
        Map<String, PuzzleStatsEntity> result = new HashMap<>(defaults);
        loadAll().forEach(pse -> {
            result.put(pse.getPuzzleId(), pse);
        });

        return result;
    }

    default void saveOrUpdate(PuzzleStatsEntity puzzleStatsEntity) {
        insertAll(puzzleStatsEntity);
    }
}
