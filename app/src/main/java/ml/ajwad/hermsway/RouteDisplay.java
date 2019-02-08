package ml.ajwad.hermsway;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RouteDisplay extends AppCompatActivity {

    TextView routeBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_display);
        String message = getIntent().getStringExtra("routeText");
        routeBox = findViewById(R.id.routeBox);
        routeBox.setText(message);
    }

}
