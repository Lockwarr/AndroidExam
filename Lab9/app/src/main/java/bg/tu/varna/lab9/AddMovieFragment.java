package bg.tu.varna.lab9;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMovieFragment extends DialogFragment implements View.OnClickListener {

    private EditText year, title, director, genre, replays, revenue;
    private Button buttonSave, buttonBack;
    private boolean flag;
    private int position;

    private IMovieListener listener;

    public AddMovieFragment() {
        // Required empty public constructor
    }

    public static AddMovieFragment getInstance(Movie movie, int position, boolean flag) {
        AddMovieFragment fragment = new AddMovieFragment();

        Bundle args = new Bundle();
        args.putParcelable("movie", movie);
        args.putBoolean("add", flag);
        args.putInt("position", position);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        year = view.findViewById(R.id.editTextYear);
        title = view.findViewById(R.id.editTextTitle);
        director = view.findViewById(R.id.editTextDirectr);
        genre = view.findViewById(R.id.editTextGenre);
        replays = view.findViewById(R.id.editTextReplays);
        revenue = view.findViewById(R.id.editTextRevenue);

        buttonSave = view.findViewById(R.id.buttonSave);
        buttonBack = view.findViewById(R.id.buttonBack);
        buttonSave.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

        Movie movie = getArguments().getParcelable("movie");
        if(movie != null) {
            year.setText(Integer.toString(movie.getYear()));
            title.setText(movie.getTitle());
            director.setText(movie.getDirector());
            genre.setText(movie.getGenre());
            replays.setText(String.valueOf(movie.getReplays()));
            revenue.setText(String.valueOf(movie.getRevenue()));
        }

        flag = getArguments().getBoolean("add");
        buttonSave.setText(flag ? "Add" : "Edit");

        position = getArguments().getInt("position");

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (IMovieListener) context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                int year = Integer.parseInt(this.year.getText().toString());
                String title = this.title.getText().toString();
                String director = this.director.getText().toString();
                String genre = this.genre.getText().toString();

                Movie movie = new Movie(year, title, director, genre);

                if(flag) {
                    listener.onAddMovie(movie);
                } else {
                    listener.onEditMovie(movie, position);
                }

                dismiss();
                break;
            case R.id.buttonBack:
                dismiss();
                break;
        }
    }
}
