package com.example.mayankkathuria.movies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private int[] genre_ids;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;

    /**
     * @return image path of the movie's poster
     */
    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w1280/" + poster_path;
    }

    /**
     * @return true if the movie is adult and false otherwise
     */
    public boolean getAdult(){
        return adult;
    }

    /**
     * @return summary of the movie
     */
    public String getOverview(){
        return overview;
    }

    /**
     * @return release date of the movie
     */
    public String getReleaseDate() {
        return release_date;
    }

    /**
     * @return a list of genres of a particular movie
     */
    public int[] getGenreIds() {
        return genre_ids;
    }

    /**
     * @return unique ID of the movie
     */
    public int getId(){
        return id;
    }

    /**
     * @return title of the movie
     */
    public String getOriginalTitle(){
        return original_title;
    }

    /**
     * @return language of the movie
     */
    public String getOriginalLanguage(){
        return original_language;
    }

    /**
     * @return title of the movie
     */
    public String getTitle(){
        return title;
    }

    /**
     * @return image path of the backdrop
     */

    public String getBackdropPath(){
        return backdrop_path;
    }

    /**
     * @return popularity of the movie
     */
    public double getPopularity(){
        return popularity;
    }

    /**
     * @return vote count of the movie
     */
    public int getVoteCount(){
        return vote_count;
    }

    /**
     * @return true if the movie has a video and false otherwise
     */
    public boolean getVideo(){
        return video;
    }

    /**
     * @return average vote earned by the movie
     */
    public double getVoteAverage(){
        return vote_average;
    }

    /**
     *
     * @param obj
     * @return true if the IDs of the movies are same and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Movie)) {
            return false;
        }

        Movie other = (Movie) obj;

        return other.id == id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.poster_path);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeIntArray(this.genre_ids);
        dest.writeInt(this.id);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.title);
        dest.writeString(this.backdrop_path);
        dest.writeDouble(this.popularity);
        dest.writeInt(this.vote_count);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.vote_average);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.poster_path = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.release_date = in.readString();
        this.genre_ids = in.createIntArray();
        this.id = in.readInt();
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.popularity = in.readDouble();
        this.vote_count = in.readInt();
        this.video = in.readByte() != 0;
        this.vote_average = in.readDouble();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
