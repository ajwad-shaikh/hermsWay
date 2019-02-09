package ml.ajwad.hermsway;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Dashboard extends AppCompatActivity {

    Button queryButton;
    AutoCompleteTextView source;
    AutoCompleteTextView dest;
    Button minButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        queryButton =  findViewById(R.id.queryButton);
        minButton = findViewById(R.id.material_text_button);
        source = findViewById(R.id.sourceBox);
        dest = findViewById(R.id.destBox);

        LocalBroadcastManager.getInstance(this).
                registerReceiver(waitforResponse, new IntentFilter("Inbox"));

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                minButton.setEnabled(false);
                sendSMS();
            }
        });
    }

    protected void sendSMS(){
        Log.i("Send SMS", "");
        Uri uri = Uri.parse("smsto:7368960909");
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
        String message = "<hermsWay>\nSource : " + source.getText() + "\nDestination : " +
                dest.getText() + "\n</hermsWay>";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage
                ("+919123989841", null, message,
                        null, null);
        closeKeyboard();
        minButton.setText("Waiting for Response...");

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).
                registerReceiver(waitforResponse, new IntentFilter("Inbox"));
        super.onResume();
    }

    private BroadcastReceiver waitforResponse = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("Inbox")) {
                final String message = intent.getStringExtra("message");
                minButton.setText("See Query Response!");
                minButton.setEnabled(true);
                minButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent routeIntent = new Intent(Dashboard.this, RouteDisplay.class);
                        routeIntent.putExtra("routeText", message);
                        Log.d("message: ", message);
                        startActivity(routeIntent);
                    }
                });
            }
        }
    };
}
