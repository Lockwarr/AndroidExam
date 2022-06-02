package bg.tu.varna.lab9;

public interface IMovieListener {
    void onAddMovie(Movie movie);
    void onLoadEditDialog(Movie movie, int position);
    void onEditMovie(Movie movie, int position);
    void onDeleteMovie(int position);
}
