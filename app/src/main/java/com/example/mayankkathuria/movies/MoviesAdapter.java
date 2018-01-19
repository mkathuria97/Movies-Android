package com.example.mayankkathuria.movies;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    public static final String MOVIE = "MOVIE";

    //stores a list of movies from the requested pages
    private ArrayList<Movie> movies;

    public MoviesAdapter(ArrayList<Movie> movies){
        this.movies = movies;
    }

    /**
     *
     * @param parent
     * @param viewType type of view that the view holder can store
     * @return a new view holder when there are no existing view holders which the RecyclerView can
     *         reuse
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // a LayoutInflater turns a layout XML resource into a View object
        final View movieListItem = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.movie_list_item, parent, false);

        return new ViewHolder(movieListItem);
    }

    /**
     *
     * @param holder a view which the RecyclerView can reuse
     * @param position position of movie in the list of movies
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        //sets the movie's title
        holder.titleView.setText(movie.getTitle());

        //sets the movie's vote average
        String movieVoteAverage = Double.valueOf(movie.getVoteAverage()).toString();
        holder.voteAverageView.setText(movieVoteAverage);

        //sets the movie's image
        Picasso.with(holder.imageView.getContext()).load(movie.getPosterPath()).
                into(holder.imageView);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //provides runtime binding between the MainActivity and DetailActivity
                Intent intent = new Intent(v.getContext(), DetailActivity.class);

                intent.putExtra(MOVIE, movie);

                //starts a new activity
                v.getContext().startActivity(intent);

            }
        });
    }

    /**
     *
     * @return length of the list of movies
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView titleView;
        public TextView voteAverageView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            titleView = (TextView) itemView.findViewById(R.id.movieTitleTextView);
            voteAverageView = (TextView) itemView.findViewById(R.id.voteAverageTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}

