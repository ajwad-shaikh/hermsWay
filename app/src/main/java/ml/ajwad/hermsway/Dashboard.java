package ml.ajwad.hermsway;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Dashboard extends AppCompatActivity {

    Button queryButton =  findViewById(R.id.queryButton);
    final AutoCompleteTextView source = findViewById(R.id.sourceBox);
    final AutoCompleteTextView dest = findViewById(R.id.destBox);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LocalBroadcastManager.getInstance(this).
                registerReceiver(waitforResponse, new IntentFilter("Inbox"));

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sendSMS();
            }
        });
    }

    protected void sendSMS(){
        Log.i("Send SMS", "");
        Uri uri = Uri.parse("smsto:7368960909");
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
        smsIntent.putExtra("sms_body", "<hermsWay>\nSource : " +
                source.getText() + "\nDestination : " + dest.getText() + "\n</hermsWay>");
        Button prevButton = findViewById(R.id.material_text_button);

        try {
            startActivity(smsIntent);
            Log.i("Finished sending SMS...", "");
            prevButton.setText("Waiting for Response...");

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Dashboard.this,
                    "SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).
                registerReceiver(waitforResponse, new IntentFilter("otp"));
        super.onResume();
    }

    private BroadcastReceiver waitforResponse = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("Inbox")) {
                final String message = intent.getStringExtra("message");
                
            }
        }
    };
}
