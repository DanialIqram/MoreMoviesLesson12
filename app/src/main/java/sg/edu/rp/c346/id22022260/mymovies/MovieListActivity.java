package sg.edu.rp.c346.id22022260.mymovies;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {

    ListView lv;
    Button btnShowRating, btnAddMovie;
    Spinner ratingsSpinner;
    ArrayList<Movie> movieList, movies;
    ArrayList<String> spinnerList;
    CustomAdapter moviesAdapter;
    LinearLayout llNoMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Movie List");
        }

        lv = findViewById(R.id.lv);
        movieList = new ArrayList<>();
        moviesAdapter = new CustomAdapter(this, R.layout.row, movieList);
        lv.setAdapter(moviesAdapter);

        ratingsSpinner = findViewById(R.id.ratingsSpinner);
        ratingsSpinner.setSelection(0);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MovieListActivity.this, EditMovieActivity.class);
                intent.putExtra("data", movies.get(position));
                startActivity(intent);
            }
        });

        btnShowRating = findViewById(R.id.btnShowRating);
        btnShowRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMovies(ratingsSpinner.getSelectedItem().toString());
            }
        });

        btnAddMovie = findViewById(R.id.btnAddMovie);
        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        llNoMovie = findViewById(R.id.llNoMovies);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMovies();
        Log.i("test", "called");
        ratingsSpinner.setSelection(0);
    }

    private void loadMovies() {
        DBHelper db = new DBHelper(MovieListActivity.this);
        movies = db.getMovies();
        db.close();

        movieList.clear();
        movieList.addAll(movies);
        moviesAdapter.notifyDataSetChanged();

        if (movieList.size() == 0) {
            llNoMovie.setVisibility(View.VISIBLE);
        } else {
            llNoMovie.setVisibility(View.GONE);
        }
    }

    private void loadMovies(String rating) {
        if (rating.equalsIgnoreCase("show all")) {
            loadMovies();
        } else {
            DBHelper db = new DBHelper(MovieListActivity.this);
            movies = db.getMovies(rating);
            db.close();

            movieList.clear();
            movieList.addAll(movies);
            moviesAdapter.notifyDataSetChanged();
        }
    }
}