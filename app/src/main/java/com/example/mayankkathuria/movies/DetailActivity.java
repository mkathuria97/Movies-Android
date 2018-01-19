package com.example.mayankkathuria.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView movieImageView;
    private TextView movieTitleView;
    private TextView voteAverageView;
    private TextView releaseDateView;
    private TextView overviewDescView;
    private Button backButton;
    private ImageView calendarImageView;

    public static final String URL_CALENDAR = "http://www.racedepartment.com/images/rd_calext/" +
            "calendar.png";

    /**
     *
     * @param savedInstanceState save the state of the application in a bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieImageView = (ImageView) findViewById(R.id.movieImageView);
        movieTitleView = (TextView) findViewById(R.id.movieTitleTextView);
        voteAverageView = (TextView) findViewById(R.id.voteAverageTextView);
        releaseDateView = (TextView) findViewById(R.id.releaseDateTextView);
        overviewDescView = (TextView) findViewById(R.id.overviewDescTextView);
        calendarImageView = (ImageView) findViewById(R.id.calendarImageView);
        backButton = (Button) findViewById(R.id.backButton);

        Intent intent = getIntent();

        //stores the movie from which the activity commenced
        final Movie movie = intent.getParcelableExtra(MoviesAdapter.MOVIE);

        //sets the movie's image in the desired view
        Picasso.with(movieImageView.getContext()).load(movie.getPosterPath()).into(movieImageView);

        //sets the movie's title in the desired view
        movieTitleView.setText(movie.getOriginalTitle());

        String movieVoteAverage = Double.valueOf(movie.getVoteAverage()).toString();
        //sets the movie's vote average in the desired view
        voteAverageView.setText(movieVoteAverage);

        //sets the movie's release date in the desired view
        releaseDateView.setText(movie.getReleaseDate());

        //sets the movie's overview in the desired view
        overviewDescView.setText(movie.getOverview());

        //sets the calendar's image in the desired view
        Picasso.with(calendarImageView.getContext()).load(URL_CALENDAR).into(calendarImageView);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

