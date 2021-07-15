package hipravin.javapuzzles;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PuzzleListFragment extends Fragment {

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

        CardView puzzle1card = view.findViewById(R.id.puzzleCard1);
        puzzle1card.setOnClickListener(v -> {
//            Toast.makeText(view.getContext().getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
            switchToPuzzleFragment("1");
        });
    }

    private void switchToPuzzleFragment(String puzzleId) {
        Fragment puzzleFragment = PuzzleFragment.newInstance(puzzleId);

        if(getFragmentManager() != null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, puzzleFragment)
                    .addToBackStack(null)
                    .commit();

        }
    }
}