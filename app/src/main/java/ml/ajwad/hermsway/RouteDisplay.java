package ml.ajwad.hermsway;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RouteDisplay extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_display);
        TextView routeList = findViewById(R.id.routeList);
        routeList.setMovementMethod(new ScrollingMovementMethod());
        String message = getIntent().getStringExtra("routeText");
        routeList.setText(message);
    }

}
