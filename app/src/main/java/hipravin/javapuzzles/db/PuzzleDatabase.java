package hipravin.javapuzzles.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PuzzleStatsEntity.class}, version = 1, exportSchema = false)
public abstract class PuzzleDatabase extends RoomDatabase {
    public abstract PuzzleStatsDao puzzleStatsDao();
}