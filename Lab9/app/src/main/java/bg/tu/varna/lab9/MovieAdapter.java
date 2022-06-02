package bg.tu.varna.lab9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private ArrayList<Movie> movies;
    Context context;
    IMovieListener listener;
    public MovieAdapter(ArrayList<Movie> movies, IMovieListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.movie_item, parent, false);

        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    public void deleteMovie(int position) {
        movies.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Movie movie = movies.get(position);

        holder.setDirector(movie.getDirector());
        holder.setGenre(movie.getGenre());
        holder.setTitle(movie.getTitle());
        holder.setYear(Integer.toString(movie.getYear()));

        holder.setDeleteListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movies.remove(position);
                notifyItemRemoved(position);
            }
        });
        holder.setEditListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLoadEditDialog(movies.get(position), position);
            }
        });
        holder.setRatingBarListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Movie movie = movies.get(position);
                movie.setRating(rating);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void add(Movie movie) {
        //always add on top of list
        movies.add(0, movie);
        notifyItemInserted(0);
    }

    public void change(Movie movie, int position) {
        movies.set(position, movie);
        notifyItemChanged(position);
    }
}
