package hipravin.javapuzzles;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.ads.AdView;
import com.google.android.material.card.MaterialCardView;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import hipravin.javapuzzles.puzzles.PuzzleTaskRepository;
import hipravin.javapuzzles.db.PuzzleStatsEntity;
import hipravin.javapuzzles.viewmodel.PuzzleViewModel;

import java.util.Map;


public class PuzzleListFragment extends Fragment {

    private boolean hideAdOnPuzzle = false;
    PuzzleViewModel viewModel;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PuzzleListFragment() {
        // Required empty public constructor
    }


    public static PuzzleListFragment newInstance(String param1, String param2) {
        PuzzleListFragment fragment = new PuzzleListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication());
        viewModel = new ViewModelProvider(requireActivity().getViewModelStore(), factory).get(PuzzleViewModel.class);

        viewModel.getLastSolvedPuzzleId().observe(this, puzzleId -> {
            if (puzzleId != null && !puzzleId.isEmpty()) {
                onPuzzleSolved(puzzleId);
            }
        });

        viewModel.getLastTriedPuzzleId().observe(this, puzzleId -> {
            if (puzzleId != null && !puzzleId.isEmpty()) {
                onPuzzleFailedTry(puzzleId);
            }
        });

        viewModel.getPuzzleStats().observe(this, stats -> {
            if (stats != null) {
                onPuzzleStatsUpdated(getView(), stats);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_puzzle_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assignOnCardClicks(view, savedInstanceState);
        onPuzzleStatsUpdated(view, viewModel.getPuzzleStats().getValue());
    }

    private void assignOnCardClicks(@NonNull View view, @Nullable Bundle savedInstanceState) {
        PuzzleTaskRepository.getInstance().getPuzzleTasks().forEach((id, task) -> {
            MaterialCardView puzzleCard = view.findViewById(task.cardId());
            puzzleCard.setOnClickListener(v -> {
                switchToPuzzleFragment(String.valueOf(id));
            });
        });
    }

    private void onPuzzleStatsUpdated(View view, Map<String, PuzzleStatsEntity> stats) {
        if (view != null) {
            stats.forEach((id, pse) -> {
                TextView triesText = view.findViewById(
                        PuzzleTaskRepository.getInstance().getForId(id).triesTextId());

                if (triesText != null && pse.getTriesBeforePassed() > 0) {
                    triesText.setText(getResources().getString(R.string.triesString, pse.getTriesBeforePassed()));
                }

                if (pse.isPassed()) {
                    MaterialCardView puzzleCard = view.findViewById(
                            PuzzleTaskRepository.getInstance().getForId(pse.getPuzzleId()).cardId());

                    puzzleCard.setChecked(true);
                }
            });
        }
    }

    private void onPuzzleSolved(String puzzleId) {
        if (getView() != null) {
            MaterialCardView puzzleSolvedCard = getView().findViewById(
                    PuzzleTaskRepository.getInstance().getForId(puzzleId).cardId());
            puzzleSolvedCard.setChecked(true);
        }
    }

    private void onPuzzleFailedTry(String puzzleId) {
        if (getView() != null) {
            TextView triesText = getView().findViewById(
                    PuzzleTaskRepository.getInstance().getForId(puzzleId).triesTextId());

            PuzzleStatsEntity puzzleStatsEntity = viewModel.getPuzzleStatsValue().get(puzzleId);
            if (puzzleStatsEntity != null) {
                triesText.setText(getResources().getString(R.string.triesString, puzzleStatsEntity.getTriesBeforePassed()));
            }
        }
    }

    private void switchToPuzzleFragment(String puzzleId) {
        if (getView() != null) {
            AdView adView = requireActivity().findViewById(R.id.adView);
            if(adView != null && hideAdOnPuzzle) {
                adView.setVisibility(View.GONE);
            }
        }

        Fragment puzzleFragment = PuzzleFragment.newInstance(puzzleId);

        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, puzzleFragment)
                    .addToBackStack(null)
                    .commit();

            ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory
                    .getInstance(requireActivity().getApplication());
            PuzzleViewModel model = new ViewModelProvider(requireActivity().getViewModelStore(), factory).get(PuzzleViewModel.class);

            PuzzleTask puzzleTask = PuzzleTaskRepository.getInstance().getForId(puzzleId);

            model.setViewStatePuzzle(puzzleId, puzzleTask);

        }
    }
}