package sg.edu.rp.c346.id22022260.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);

        this.parent_context = context;
        this.layout_id = resource;
        this.movieList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        ImageView ivRating = rowView.findViewById(R.id.ivRating);
        TextView tvYear = rowView.findViewById(R.id.tvYear);

        Movie movie = movieList.get(position);

        tvTitle.setText(movie.getTitle());
        tvGenre.setText(movie.getGenre());
        tvYear.setText(String.valueOf(movie.getYear()));

        String imageUrl = "";
        if (movie.getRating().equalsIgnoreCase("g")) {
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/IMDA_Age_Rating_-_General_Audiences.png/192px-IMDA_Age_Rating_-_General_Audiences.png";
        } else if (movie.getRating().equalsIgnoreCase("pg")) {
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/IMDA_Age_Rating_-_Parental_Guidance.png/192px-IMDA_Age_Rating_-_Parental_Guidance.png";
        } else if (movie.getRating().equalsIgnoreCase("pg13")) {
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/IMDA_Age_Rating_-_Parental_Guidance_for_Under_13.png/192px-IMDA_Age_Rating_-_Parental_Guidance_for_Under_13.png";
        } else if (movie.getRating().equalsIgnoreCase("nc16")) {
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/IMDA_Age_Rating_-_No_Children_Under_16.png/192px-IMDA_Age_Rating_-_No_Children_Under_16.png";
        }  else if (movie.getRating().equalsIgnoreCase("m18")) {
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/IMDA_Age_Rating_-_Mature_18.png/192px-IMDA_Age_Rating_-_Mature_18.png";
        } else if (movie.getRating().equalsIgnoreCase("r21")) {
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/IMDA_Age_Rating_-_Restricted_21.png/192px-IMDA_Age_Rating_-_Restricted_21.png";
        }

        Picasso.with(this.getContext()).load(imageUrl).into(ivRating);

        return rowView;
    }
}
