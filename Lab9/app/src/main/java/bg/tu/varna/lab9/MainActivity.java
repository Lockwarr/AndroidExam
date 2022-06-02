package bg.tu.varna.lab9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import bg.tu.varna.lab9.tasks.DeleteMoviesTask;
import bg.tu.varna.lab9.tasks.MoviesReplaysTask;

public class MainActivity extends AppCompatActivity implements IMovieListener {

    private RecyclerView recyclerView;
    private FloatingActionButton addMovieFloatingButton;
    private MovieAdapter adapter;

    protected static ArrayList<Movie> addInitialMovies(int count) {
        ArrayList<Movie> movies = new ArrayList<>();

        for(int i=0; i < count; i++) {
            movies.add(new Movie(new Random().nextInt(121) + 1900, "Title" + i, "Director" + i, "Genre" + i));
        }

        return movies;
    }

    private void moviesReplays(ArrayList<Movie> movies) {
        HandlerThread revenueThread = new HandlerThread("MoviesReplaysThread");
        revenueThread.start();
        Looper looper = revenueThread.getLooper();
        Handler handler = new Handler(looper);
        handler.post(new MoviesReplaysTask(movies));
    }

    private void deleteMovies(ArrayList<Movie> movies, MovieAdapter moviesAdapter) {
        HandlerThread revenueThread = new HandlerThread("DeleteMoviesThread");
        revenueThread.start();
        Looper looper = revenueThread.getLooper();
        Handler handler = new Handler(looper);
        handler.post(new DeleteMoviesTask(movies, this));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Movie> initialMovies = addInitialMovies(10);
        adapter = new MovieAdapter(initialMovies, this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addMovieFloatingButton = findViewById(R.id.buttonAddMovie);
        addMovieFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMovieFragment dialogFragment = AddMovieFragment.getInstance(null, 0,true);
                dialogFragment.show(getSupportFragmentManager(), "add_fragment_dialog");
            }
        });
        //Start threads
        moviesReplays(initialMovies);
        deleteMovies(initialMovies, adapter);
    }

    @Override
    public void onAddMovie(Movie movie) {
        adapter.add(movie);
    }

    @Override
    public void onLoadEditDialog(Movie movie, int position) {
        AddMovieFragment dialogFragment = AddMovieFragment.getInstance(movie, position,false);
        dialogFragment.show(getSupportFragmentManager(), "add_fragment_dialog");
    }

    @Override
    public void onEditMovie(Movie movie, int position) {
        adapter.change(movie, position);
    }

    @Override
    public void onDeleteMovie(int position) {
        runOnUiThread(() -> {
            adapter.deleteMovie(position);
        });
    }
}
