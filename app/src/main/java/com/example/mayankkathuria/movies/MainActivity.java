package com.example.mayankkathuria.movies;

import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String URL_STRING = "https://api.themoviedb.org/3/movie/popular?api_key=";

    //stores the number of movie result pages
    public static final int NUMBER_OF_MOVIE_PAGES = 10;

    private RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private MoviesAdapter mMoviesAdapter;

    /**
     *
     * @param savedInstanceState save the state of the application in a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        mMoviesAdapter = new MoviesAdapter(mMovies);
        mRecyclerView.setAdapter(mMoviesAdapter);

        //construct URLs and request data
        try {
            URL[] urls = new URL[NUMBER_OF_MOVIE_PAGES];

            for(int index = 0; index < NUMBER_OF_MOVIE_PAGES; index++) {
                urls[index] = new URL(URL_STRING + MoviesApi.API_KEY + "&page=" + (index + 1));
            }

            //fetch the movies on a background thread; it will populate mMovies
            new MoviesAsyncTask(this).execute(urls);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param newConfig represents all of the current configurations
     * avoid the activity from restarting based on configuration changes
     *
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public class MoviesAsyncTask extends AsyncTask<URL, Void, ArrayList<Movie>> {
        Context context;

        public MoviesAsyncTask(Context context) {
            this.context = context;
        }

        /**
         * this function is done on the background thread
         *
         * @param params list of URLs
         * @return list of movies from the requested pages
         */
        @Override
        protected ArrayList<Movie> doInBackground(URL... params) {try {
                //stores a list of movies from the requested pages
                ArrayList<Movie> movies = new ArrayList<>();

                for (int index = 0; index < NUMBER_OF_MOVIE_PAGES; index++) {
                    URL url = params[index];
                    URLConnection connection = url.openConnection();
                    connection.connect();

                    InputStream inStream = connection.getInputStream();
                    InputStreamReader inStreamReader =
                            new InputStreamReader(inStream, Charset.forName("UTF-8"));

                    Gson gson = new Gson();
                    MovieCollection movieCollection =
                            gson.fromJson(inStreamReader, MovieCollection.class);
                    //stores a list of movies from the requested page
                    Movie[] moviesListFromPage = movieCollection.getMovieResults();

                    for (Movie movie : moviesListFromPage) {
                        movies.add(movie);
                    }
                }

                return movies;
            } catch (IOException e) {
                Log.d("MoviesAsyncTask", "Failed to get data from network", e);
                return null;
            }
        }

        /**
         * @param movies list of movies
         */
        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {

            //pop up a Toast if we failed to get data
            if (movies.size() == 0) {
                Toast.makeText(context, "Failed to get network data", Toast.LENGTH_LONG).show();
                return;
            }

            //empty the ArrayList of movies (mMovies) and fill it with the ones we loaded
            mMovies.clear();
            mMovies.addAll(movies);

            //poke mMoviesAdapter to let it know that its data changed
            mMoviesAdapter.notifyDataSetChanged();
        }
    }
}

