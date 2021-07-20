package com.dialoginsight.appdemo;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import com.dialoginsight.dianalytics2.DIAnalytics;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edittext_login_activity_email)
    EditText mEmailEditText;

    @BindView(R.id.edittext_login_activity_first_name)
    EditText mFirstNameEditText;

    @BindView(R.id.edittext_login_activity_last_name)
    EditText mLastNameEditText;

    @BindView(R.id.edittext_login_activity_id)
    EditText mIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login_activity_submit)
    public void onSubmitClick() {
        if(mIdEditText.getText().toString().equals("")){
            Toast.makeText(this, "ID must not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (mEmailEditText.getText().toString().equals("")) {
            Toast.makeText(this, "Email must not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (mFirstNameEditText.getText().toString().equals("")) {
            Toast.makeText(this, "First name must not be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (mLastNameEditText.getText().toString().equals("")) {
            Toast.makeText(this, "Last name must not be empty", Toast.LENGTH_LONG).show();
            return;
        }

        HashMap contactData = new HashMap();
        contactData.put("f_ID", mIdEditText.getText().toString());
        contactData.put("f_EMail", mEmailEditText.getText().toString());
        contactData.put("f_FirstName", mLastNameEditText.getText().toString());
        contactData.put("f_LastName", mFirstNameEditText.getText().toString());

        HashMap hashMap = new HashMap();
        hashMap.put("Contact", contactData);
        //DIAnalytics.identify(hashMap);

        finish();
    }
}
