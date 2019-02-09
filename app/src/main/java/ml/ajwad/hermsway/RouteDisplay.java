package ml.ajwad.hermsway;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import androidx.appcompat.app.AppCompatActivity;

public class RouteDisplay extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_display);
        TextView routeList = findViewById(R.id.routeList);
        String message = getIntent().getStringExtra("routeText");
        routeList.setText(message);
    }

}
