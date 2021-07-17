package hipravin.javapuzzles;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import hipravin.javapuzzles.viewmodel.PuzzleViewModel;
import hipravin.javapuzzles.viewmodel.ViewState;

public class MainActivity extends AppCompatActivity {

    private PuzzleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);


        Fragment puzzleListFragment = PuzzleListFragment.newInstance("", "");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, puzzleListFragment)
                .commit();

        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        viewModel = new ViewModelProvider(getViewModelStore(), factory).get(PuzzleViewModel.class);

        viewModel.getViewState().observe(this, state -> {
            if(state == ViewState.PUZZLE_LIST) {
                setActionBarHome();
            } else if(state == ViewState.PUZZLE_TASK) {
                setActionBarPuzzle();
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
            if(viewModel.getPuzzleTask() != null) {
                getSupportActionBar().setTitle(viewModel.getPuzzleTask().titleStringId());
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(viewModel != null) {
            if (viewModel.getViewState().getValue() == ViewState.PUZZLE_TASK) {
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
}