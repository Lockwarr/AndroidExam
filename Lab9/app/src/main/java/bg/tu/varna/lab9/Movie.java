package bg.tu.varna.lab9;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Movie implements Parcelable {
    private int year;
    private String title;
    private String director;
    private String genre;
    private ArrayList<Float> ratings;
    private double revenue;
    private int replays;

    public Movie(int year, String title, String director, String genre) {
        this.year = year;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.ratings = new ArrayList<>(1);
        this.revenue = 0;
        this.replays = 0;
    }

    protected Movie(Parcel in) {
        year = in.readInt();
        title = in.readString();
        director = in.readString();
        genre = in.readString();
        replays = 0;
        revenue = 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public float getRating() {
        float avg = 0;
        for (float f : ratings) {
            avg += f;
        }
        return avg / ratings.size();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(float rating) {
        this.ratings.add(rating);
    }

    public void setReplays(int replays) {
        this.replays = replays;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public int getReplays() {
        return replays;
    }

    public double getRevenue() {
        return revenue;
    }

    public double compareTo(Movie movieToCompare) {
        // Sort by revenue, if revenue is equal sort by title
        if (this.revenue != movieToCompare.getRevenue()) {
            return this.revenue - movieToCompare.getRevenue();
        }
        return this.title.compareTo(movieToCompare.getTitle());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(year);
        dest.writeString(title);
        dest.writeString(director);
        dest.writeString(genre);
    }
}