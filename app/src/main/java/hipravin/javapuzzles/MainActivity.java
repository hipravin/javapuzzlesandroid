package hipravin.javapuzzles;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import hipravin.javapuzzles.db.PuzzleDatabase;
import hipravin.javapuzzles.db.PuzzleStatsDao;
import hipravin.javapuzzles.db.PuzzleStatsEntity;
import hipravin.javapuzzles.viewmodel.PuzzleViewModel;
import hipravin.javapuzzles.viewmodel.ViewState;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String DB_NAME = "JAVA_PUZZLES_DB_01";

    private boolean adEnabled = false;
    private AdView admobBannerView;
    private PuzzleViewModel viewModel;
    private PuzzleDatabase puzzleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(adEnabled) {
            MobileAds.initialize(this, initializationStatus -> {
            });
            admobBannerView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            admobBannerView.loadAd(adRequest);
        }

        puzzleDatabase = Room.databaseBuilder(getApplicationContext(),
                PuzzleDatabase.class, DB_NAME).build();

        Toolbar mainToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);

        Fragment puzzleListFragment = PuzzleListFragment.newInstance("", "");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, puzzleListFragment)
                .commit();

        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(getViewModelStore(), factory).get(PuzzleViewModel.class);
        //init in main thread
        viewModel.getPuzzleStats();
        new LoadPuzzleStatsTask().execute();

        viewModel.getViewState().observe(this, state -> {
            if (state == ViewState.PUZZLE_LIST) {
                setActionBarHome();
            } else if (state == ViewState.PUZZLE_TASK) {
                setActionBarPuzzle();
            }
        });

        viewModel.getLastSolvedPuzzleId().observe(this, puzzleId -> {
            if (puzzleId != null && !puzzleId.isEmpty()) {
                onPuzzleStatsChanged(puzzleId);
            }
        });

        viewModel.getLastTriedPuzzleId().observe(this, puzzleId -> {
            if (puzzleId != null && !puzzleId.isEmpty()) {
                onPuzzleStatsChanged(puzzleId);
            }
        });
    }

    private void setActionBarHome() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }

    private void setActionBarPuzzle() {
        if (getSupportActionBar() != null && viewModel != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (viewModel.getPuzzleTask() != null) {
                getSupportActionBar().setTitle(viewModel.getPuzzleTask().titleStringId());
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (viewModel != null) {
            if (viewModel.getViewState().getValue() == ViewState.PUZZLE_TASK) {
                if(admobBannerView != null && adEnabled) {
                    admobBannerView.setVisibility(View.VISIBLE);
                }
                viewModel.setViewStatePuzzleList();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onPuzzleStatsChanged(String puzzleId) {
        new UpdatePuzzleStatsTask(puzzleId).execute();
    }

    class LoadPuzzleStatsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                PuzzleStatsDao puzzleStatsDao = puzzleDatabase.puzzleStatsDao();
                if(viewModel != null) {
                    Map<String, PuzzleStatsEntity> stats = puzzleStatsDao.getAllWithDefaults(viewModel.getPuzzleStatsValue());
                    viewModel.postPuzzleStatsValue(stats);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    class UpdatePuzzleStatsTask extends AsyncTask<Void, Void, Void> {

        private final String puzzleId;

        UpdatePuzzleStatsTask(String puzzleId) {
            this.puzzleId = puzzleId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                PuzzleStatsEntity puzzleStatsEntity = viewModel.getPuzzleStatsValue().get(puzzleId);

                if (puzzleStatsEntity != null) {
                    puzzleDatabase.puzzleStatsDao().saveOrUpdate(puzzleStatsEntity);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}