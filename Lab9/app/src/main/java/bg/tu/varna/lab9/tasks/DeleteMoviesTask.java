package bg.tu.varna.lab9.tasks;

import java.util.ArrayList;
import java.util.Random;

import bg.tu.varna.lab9.IMovieListener;
import bg.tu.varna.lab9.Movie;
import bg.tu.varna.lab9.MovieAdapter;

public class DeleteMoviesTask implements Runnable {

    private ArrayList<Movie> movies;;
    private IMovieListener listener;

    public DeleteMoviesTask(ArrayList<Movie> movies, IMovieListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @Override
    public void run() {
        int counter = 0;
        // can be configured to be executed endlessly with while(true) for example
        while(counter < 1000) {
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int lowestRevenueMovieIndex = movies.size() - 1;
            double lowestRevenue = 1000000000000000.0;
            if (movies.size() > 10) {
                for (int i = 0; i < movies.size() - 1; i++) {
                    Movie currentMovie = movies.get(i);
                    if (currentMovie.getRevenue() < lowestRevenue) {
                        lowestRevenueMovieIndex = i;
                        lowestRevenue = currentMovie.getRevenue();
                    }
                }
                listener.onDeleteMovie(lowestRevenueMovieIndex);
            }
            counter++;
        }
    }
}