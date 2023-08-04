package sg.edu.rp.c346.id22022260.mymovies;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etGenre, etYear;
    Spinner ratingSpinner;
    Button btnInsert, btnShowList;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Movies");
        }

        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        ratingSpinner = findViewById(R.id.spinnerRating);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        ratingSpinner.setSelection(0);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String yearString = etYear.getText().toString();

                if (title.isEmpty() || genre.isEmpty() || yearString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Field(s) cannot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (yearString.length() != 4) {
                    Toast.makeText(MainActivity.this, "Year has to have a length of 4.", Toast.LENGTH_LONG).show();
                    return;
                }

                int year = Integer.parseInt(yearString);
                String rating = ratingSpinner.getSelectedItem().toString();

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertMovie(title, genre, year, rating);

                Toast.makeText(MainActivity.this, "Added movie", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
                startActivity(intent);
            }
        });
    }
}