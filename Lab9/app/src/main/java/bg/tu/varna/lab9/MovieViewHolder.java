package bg.tu.varna.lab9;

import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private TextView year, title, director, genre;
    private Button button, edit;
    private RatingBar ratingBar;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        year = itemView.findViewById(R.id.textViewTitle);
        title = itemView.findViewById(R.id.textViewDirector);
        director = itemView.findViewById(R.id.textViewDirector);
        genre = itemView.findViewById(R.id.textViewGenre);
        edit = itemView.findViewById(R.id.buttonEdit);
        ratingBar = itemView.findViewById(R.id.ratingBar);
        button = itemView.findViewById(R.id.buttonDelete);

    }

    public void setYear(String year) {
        this.year.setText(year);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDirector(String director) {
        this.director.setText(director);
    }

    public void setGenre(String genre) {
        this.genre.setText(genre);
    }

    public void setDeleteListener(View.OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public void setEditListener(View.OnClickListener listener) {
        this.edit.setOnClickListener(listener);
    }

    public void setRatingBar(float rating) {
        this.ratingBar.setRating(rating);
    }

    public void setRatingBarListener(RatingBar.OnRatingBarChangeListener listener) {
        this.ratingBar.setOnRatingBarChangeListener(listener);
    }
}
