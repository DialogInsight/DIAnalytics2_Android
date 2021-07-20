package com.dialoginsight.appdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dialoginsight.dianalytics2.DIAnalytics;
import com.dialoginsight.dianalytics2.DISharedPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.edittext_main_activity_push_id)
    EditText pushIdEditText;

    @BindView(R.id.textToken)
    TextView token;

    @BindView(R.id.textGuid)
    TextView guid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("DIAnalytics demo");
        ButterKnife.bind(this);
        this.getToken();
        NotificationReceiver n = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.dialoginsight.dianalytics.NotificationBroadcast");
        this.registerReceiver(n, filter);

        Log.d("di","version name : " + DIAnalytics.libraryVersion());
    }

    @OnClick(R.id.button_main_activity_set_token)
    public void onSetTokenClick() {
        DIAnalytics.registerForRemoteNotification();
    }

    @OnClick(R.id.button_main_activity_identify)
    public void onIdentifyClick() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_main_activity_simulate_push_reception)
    public void onSimulatePushReceptionClick() {
        DIAnalytics.sendPushReceptions(pushIdEditText.getText().toString());
    }

    @OnClick(R.id.button_main_activity_simulate_application_launch)
    public void onSimulateApplicationLaunchClick() {
        Toast.makeText(this, "The function is called on the start of the application.", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.button_main_activity_logcat)
    public void onLogcatClick() {
        Intent intent = new Intent(this, LogcatActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_main_activity_update)
    public void onUpdateClick(){
        Intent intent = new Intent(this, UpdateContactActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_main_activity_getGuid)
    public void onGetGuidClick(){

        DIAnalytics.GetGuid(DIAnalytics.getContact().getToken()).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String result) {
                guid.setText(result);
            }
        });
    }

    public void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {
                Log.e(TAG, "Failed to get the token.");
                return;
            }

            //get the token from task
            String tokenFCM = task.getResult();
            DISharedPref.setNotificationTokenSharedPref(tokenFCM);
            Log.d(TAG, "Token : " + tokenFCM);
            token.setText(tokenFCM);


        }).addOnFailureListener(e -> Log.e(TAG, "Failed to get the token : " + e.getLocalizedMessage()));
    }

    @OnClick(R.id.button_main_activity_resetid)
    public void onResetIDClick(){
        DIAnalytics.reset();
    }
}

