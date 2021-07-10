package hipravin.javapuzzles;

import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment puzzleListFragment = PuzzleListFragment.newInstance("", "");
        Fragment puzzleFragment = PuzzleFragment.newInstance("", "");
        String text = "";
        try (InputStream is = getResources().openRawResource(R.raw.puzzlecode1);
             Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
             text = scanner.useDelimiter("\\A").next();



        } catch (IOException e) {
//            throw new RuntimeException(e);
        }

        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.flFragment, puzzleListFragment)
                .replace(R.id.flFragment, puzzleFragment)
                .commit();

        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        String codeText = text;
        PuzzleListViewModel model = new ViewModelProvider(getViewModelStore(), factory).get(PuzzleListViewModel.class);
        model.getPuzzles().observe(this, puzzleDescriptions -> {

            Spanned spanned = HtmlCompat.fromHtml(codeText, HtmlCompat.FROM_HTML_MODE_COMPACT);

            TextView textView = (TextView) findViewById(R.id.puzzleCodeTextView);
            textView.setText(spanned);
//            TextView textView = (TextView)findViewById(R.id.txt1);
//
//            String descrs = puzzleDescriptions.stream()
//                    .map(PuzzleDescription::getHeader)
//                    .collect(Collectors.joining(", "));
//
//            textView.setText(descrs);
            // update UI
        });
    }


}