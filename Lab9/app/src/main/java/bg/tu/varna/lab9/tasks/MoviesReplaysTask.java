package bg.tu.varna.lab9.tasks;

import android.util.Patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import bg.tu.varna.lab9.Movie;

public class MoviesReplaysTask implements Runnable {

    private ArrayList<Movie> movies;

    public MoviesReplaysTask(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void run() {
        int counter = 0;
        // can be configured to be executed endlessly with while(true) for example
        while(counter < 1000) {
            Random rand = new Random();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < movies.size() - 1; i++) {
                int replays = rand.nextInt(1000);
                Movie currentMovie = movies.get(i);
                // get current replays and increase them by random(0-1000) replays
                currentMovie.setReplays(currentMovie.getReplays() + replays);
                currentMovie.setRevenue(currentMovie.getReplays() * 8.90);
            }
            Collections.sort(movies, new Comparator<Movie>(){
                @Override
                public int compare(Movie first, Movie second)
                {
                    if (first.getRevenue() != second.getRevenue()) {
                        return (int) (second.getRevenue() - first.getRevenue());
                    }
                    return first.getTitle().compareTo(second.getTitle());
                }
            });
            counter++;
        }

    }
}
