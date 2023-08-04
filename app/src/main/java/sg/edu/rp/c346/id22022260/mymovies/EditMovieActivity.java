package sg.edu.rp.c346.id22022260.mymovies;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditMovieActivity extends AppCompatActivity {

    EditText etID, etTitle, etGenre, etYear;
    Spinner ratingsSpinner;
    Button btnUpdate, btnDelete, btnCancel;
    Movie data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Edit Movie");
        }

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        ratingsSpinner = findViewById(R.id.spinnersRating);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        data = (Movie) intent.getSerializableExtra("data");

        etID.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(String.valueOf(data.getYear()));

        switch (data.getRating()) {
            case "G":
                ratingsSpinner.setSelection(0);
                break;
            case "PG":
                ratingsSpinner.setSelection(1);
                break;
            case "PG13":
                ratingsSpinner.setSelection(2);
                break;
            case "NC16":
                ratingsSpinner.setSelection(3);
                break;
            case "M18":
                ratingsSpinner.setSelection(4);
                break;
            case "R21":
                ratingsSpinner.setSelection(5);
                break;
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setRating(ratingsSpinner.getSelectedItem().toString());

                DBHelper db = new DBHelper(EditMovieActivity.this);
                db.updateMovie(data);
                db.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditMovieActivity.this);
                db.deleteMovie(data.getId());
                db.close();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}