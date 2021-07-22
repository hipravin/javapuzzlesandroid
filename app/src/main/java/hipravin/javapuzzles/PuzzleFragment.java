package hipravin.javapuzzles;

import android.os.Bundle;
import android.text.Spanned;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.ads.AdView;
import com.google.android.material.textfield.TextInputEditText;
import hipravin.javapuzzles.markup.CodeMarkupUtil;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import hipravin.javapuzzles.puzzles.PuzzleTaskRepository;
import hipravin.javapuzzles.viewmodel.PuzzleViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PuzzleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PuzzleFragment extends Fragment {

    private static final String ARG_PUZZLE_ID = "puzzle_id";

    private String puzzleId;
    PuzzleTask puzzleTask = null;
    PuzzleViewModel puzzleViewModel;

    public PuzzleFragment() {
        // Required empty public constructor
    }

    public static PuzzleFragment newInstance(String puzzleId) {
        PuzzleFragment fragment = new PuzzleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PUZZLE_ID, puzzleId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            puzzleId = getArguments().getString(ARG_PUZZLE_ID);
            puzzleTask = PuzzleTaskRepository.getInstance().getForId(puzzleId);
        }

        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication());
        puzzleViewModel = new ViewModelProvider(requireActivity().getViewModelStore(), factory).get(PuzzleViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_puzzle, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (puzzleTask != null) {
            loadCode(view, savedInstanceState);
            setPuzzleViewText(view);
            assignOnRunButton(view);
//            assignHelpButton(view);
        }
    }

    private void loadCode(View view, Bundle savedInstanceState) {
        try (InputStream is = getResources().openRawResource(puzzleTask.codeRawId());
             Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
            String text = scanner.useDelimiter("\\A").next();

            String processed = CodeMarkupUtil.plainToHtml(text);

            Spanned spanned = HtmlCompat.fromHtml(processed, HtmlCompat.FROM_HTML_MODE_COMPACT);

            TextView textView = view.findViewById(R.id.puzzleCodeTextView);
            textView.setText(spanned);


        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }

    private void setPuzzleViewText(View view) {
        TextView header = view.findViewById(R.id.puzzleHeader);
        header.setText(getResources().getString(puzzleTask.headerStringId()));
    }

//    private void assignHelpButton(View view) {
//        ImageButton help = view.findViewById(R.id.buttonPuzzleHelp);
//
//        help.setOnClickListener(v -> {
//            DialogFragment dialog = new PuzzleHelpDialogFragment(puzzleTask.promptStringId());
//            dialog.show(getFragmentManager(), "PuzzleHelpDialogTag");
//        });
//    }

    private void assignOnRunButton(View view) {
        ImageButton runButton = view.findViewById(R.id.runPuzzleButton);
        TextView consoleTextView = view.findViewById(R.id.puzzleConsoleTextView);
        TextInputEditText consoleInput = view.findViewById(R.id.puzzleTextInput1);

        consoleInput.setOnFocusChangeListener((view1, b) -> {
            AdView adView = requireActivity().findViewById(R.id.adView);
            if(adView != null) {
                adView.setVisibility(View.GONE);
            }
        });

        runButton.setOnClickListener(v -> {
            PuzzleInput puzzleInput = new PuzzleInput(
                    consoleInput.getText() != null
                            ? consoleInput.getText().toString()
                            : "");
            PuzzleInvocationResult puzzleInvocationResult = puzzleTask.run(puzzleInput);

            if (puzzleInvocationResult.isPassed()) {
                onPuzzleSolved();
            } else {
                onPuzzleFailedTry();
            }

//            Toast.makeText(getContext(), puzzleInvocationResult.isPassed() ? "passed" : "failed", Toast.LENGTH_SHORT).show();

            consoleTextView.setText("");
            String consoleOut = puzzleInvocationResult.getOutput().stream()
                    .map(line -> ">" + line + System.getProperty("line.separator"))
                    .collect(Collectors.joining());
            consoleTextView.setText(consoleOut);
        });
    }

    private void onPuzzleSolved() {
        DialogFragment dialog = new PuzzleSolvedDialogFragment();
        puzzleViewModel.setLastSolvedPuzzleId(puzzleTask.puzzleIdString());

        if (getFragmentManager() != null) {
            dialog.show(getFragmentManager(), "PuzzleSolvedDialog");
        }
    }

    private void onPuzzleFailedTry() {
        puzzleViewModel.setLastTriedPuzzleId(puzzleTask.puzzleIdString());
    }
}