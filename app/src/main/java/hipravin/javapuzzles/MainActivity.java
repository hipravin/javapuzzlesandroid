package hipravin.javapuzzles;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment puzzleListFragment = PuzzleListFragment.newInstance("", "");
        Fragment puzzleFragment = PuzzleFragment.newInstance("1");


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, puzzleListFragment)
//                .replace(R.id.flFragment, puzzleFragment)
                .commit();

        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        PuzzleViewModel model = new ViewModelProvider(getViewModelStore(), factory).get(PuzzleViewModel.class);
        model.getPuzzles().observe(this, puzzleDescriptions -> {


//            WebView codeWebView = (WebView)findViewById(R.id.puzzleCodeWebView);
//            codeWebView.loadData(codeText, "text/html", null);
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

    private void switchToPuzzleFragment(String puzzleId) {
        Fragment puzzleFragment = PuzzleFragment.newInstance(puzzleId);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, puzzleFragment)
                .commit();


    }

    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = super.onCreateView(name, context, attrs);

        if(view == null) {
            return view;
        }

        CardView puzzle1card = view.findViewById(R.id.puzzleCard1);
        puzzle1card.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
//            switchToPuzzleFragment("1");
        });

        return view;
    }
}