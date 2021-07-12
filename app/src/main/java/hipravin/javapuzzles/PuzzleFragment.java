package hipravin.javapuzzles;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PuzzleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PuzzleFragment extends Fragment {

    private static final String ARG_PUZZLE_ID = "puzzle_id";

    private String puzzleId;

    public PuzzleFragment() {
        // Required empty public constructor
    }

    public static PuzzleFragment newInstance(String param1, String param2) {
        PuzzleFragment fragment = new PuzzleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PUZZLE_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            puzzleId = getArguments().getString(ARG_PUZZLE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_puzzle, container, false);
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadCode(view, savedInstanceState);


    }

    private void loadCode( View view,  Bundle savedInstanceState) {
        try (InputStream is = getResources().openRawResource(R.raw.puzzlecode1);
             Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
            String text = scanner.useDelimiter("\\A").next();


            Spanned spanned = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT);

            TextView textView = view.findViewById(R.id.puzzleCodeTextView);
            textView.setText(spanned);


        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}