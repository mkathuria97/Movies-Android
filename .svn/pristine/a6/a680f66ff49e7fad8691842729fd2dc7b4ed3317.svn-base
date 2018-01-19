package com.example.mayankkathuria.movies;

import java.util.ArrayList;

public class MovieCollection {
    private int page;
    private Movie[] results;

    /**
     * @return current page of the movie results
     */
    public int getPageNumber(){
        return page;
    }

    /**
     * @return list of popular movies along with their details such as poster path,
     * adult, overview, release date, genre, id, title, original language, backdrop path, popularity,
     * vote count, video and vote average
     */
    public Movie[] getMovieResults(){
        return results;
    }

    /**
     * @return list of all the movie titles on the current page
     */
    public ArrayList<String> getAllMovieTitles(){
        ArrayList<String> movieTitles = new ArrayList<>();

        for(Movie movieResult: results) {

            //adds the movie title to the array
            movieTitles.add(movieResult.getOriginalTitle());
        }

        return  movieTitles;
    }

    /**
     * @param givenGenreID represents a particular genre
     * @return list of all the movie titles on the current page with the given genreID
     */
    public ArrayList<String> getMoviesWithGenreID(int givenGenreID){
        //the array stores a list of all the movie titles on the current page with the given genreID
        ArrayList<String> genreMovieTitles = new ArrayList<>();

        for(Movie movieResult: results) {

            //a list of genres of a particular movie
            int[] genreIds = movieResult.getGenreIds();
            for(int genreId: genreIds) {

                if (givenGenreID == genreId) {

                    //adds the movie title to the array
                    genreMovieTitles.add(movieResult.getOriginalTitle());
                    break;
                }
            }
        }

        return genreMovieTitles;
    }

    /**
     * @param vote_average represents average vote of a movie
     * @return list of all the movie titles on the current page whose vote average exceeds the
     * voteAverage taken as parameter
     */
    public ArrayList<String> getMoviesExceedingVoteAverage(double vote_average){
        ArrayList<String> moviesExceedingVoteAverage = new ArrayList<>();

        for(Movie movieResult: results) {

            if (movieResult.getVoteAverage() > vote_average) {

                //adds the movie title to the array
                moviesExceedingVoteAverage.add(movieResult.getOriginalTitle());
            }
        }

        return moviesExceedingVoteAverage;
    }

    /**
     * @param popularity represents popularity of a movie
     * @return list of all the movie titles on the current page whose popularity exceeds the
     * popularity value taken as parameter
     */
    public ArrayList<String> getMoviesExceedingPopularity(double popularity ){
        ArrayList<String> moviesExceedingPopularity = new ArrayList<>();

        for(Movie movieResult: results) {

            if(movieResult.getPopularity() > popularity){

                // /adds the movie title to the array
                moviesExceedingPopularity.add(movieResult.getOriginalTitle());
            }
        }

        return moviesExceedingPopularity;
    }
}
