package hipravin.javapuzzles.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PuzzleDatabaseHelper extends SQLiteOpenHelper {

    public static final String PUZZLE_DB_NAME = "JAVA_PUZZLES_DB";
    public static final int PUZZLE_DB_VERSION = 1;

    public PuzzleDatabaseHelper(Context context) {
        super(context, PUZZLE_DB_NAME, null, PUZZLE_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =
                "CREATE TABLE PUZZLE ( " +
                " PUZZLE_ID INTEGER PRIMARY KEY , " +
                " TRIES_BEFORE_PASSED INTEGER, " +
                " PASSED BOOLEAN, " +
                " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PUZZLE");
        onCreate(db);
    }
}
