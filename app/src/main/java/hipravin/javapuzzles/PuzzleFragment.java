package hipravin.javapuzzles;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Spanned;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.textfield.TextInputEditText;
import hipravin.javapuzzles.markup.CodeMarkupUtil;
import hipravin.javapuzzles.puzzles.PuzzleInput;
import hipravin.javapuzzles.puzzles.PuzzleInvocationResult;
import hipravin.javapuzzles.puzzles.PuzzleTask;
import hipravin.javapuzzles.puzzles.PuzzleTaskRepository;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
            puzzleTask = new PuzzleTaskRepository().getForId(Integer.parseInt(puzzleId));
        }
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

        if(puzzleTask != null) {
            loadCode(view, savedInstanceState);
            setPuzzleViewText(view);
            assignOnRunButton(view);
        }
        ImageButton backButton = view.findViewById(R.id.buttonPuzzleBack);
        backButton.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
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
//            throw new RuntimeException(e);
        }
    }

    private void setPuzzleViewText(View view) {
        TextView title = view.findViewById(R.id.puzzleTitle);
        title.setText(getResources().getString(puzzleTask.titleStringId()));
        TextView header = view.findViewById(R.id.puzzleHeader);
        header.setText(getResources().getString(puzzleTask.headerStringId()));
    }

    private void assignOnRunButton(View view) {
        ImageButton runButton = view.findViewById(R.id.runPuzzleButton);
        TextView consoleTextView = view.findViewById(R.id.puzzleConsoleTextView);
//        TextView consoleInput = view.findViewById(R.id.puzzleTextInput1);
        TextInputEditText consoleInput = view.findViewById(R.id.puzzleTextInput1);
        runButton.setOnClickListener(v -> {
            PuzzleInput puzzleInput = new PuzzleInput(
                    consoleInput.getText() != null
                            ? consoleInput.getText().toString()
                            : "");
            PuzzleInvocationResult puzzleInvocationResult = puzzleTask.run(puzzleInput);

            consoleTextView.setText("");
            String consoleOut = puzzleInvocationResult.getOutput().stream()
                    .map(line -> ">" + line + System.getProperty("line.separator"))
                    .collect(Collectors.joining());
            consoleTextView.setText(consoleOut);
        });
    }
}