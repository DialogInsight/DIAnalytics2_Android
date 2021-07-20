package com.dialoginsight.appdemo;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dialoginsight.dianalytics2.DIAnalytics;
import com.dialoginsight.dianalytics2.DIContact;
import com.dialoginsight.dianalytics2.DIUtils;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateContactActivity extends AppCompatActivity {
    @BindView(R.id.edittext_update_activity_email)
    EditText mEmailEditText;

    @BindView(R.id.edittext_update_activity_first_name)
    EditText mFirstNameEditText;

    @BindView(R.id.edittext_update_activity_last_name)
    EditText mLastNameEditText;

    @BindView(R.id.edittext_update_activity_id)
    EditText mIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ButterKnife.bind(this);
        DIContact currentContact = DIAnalytics.getContact();
        if(currentContact == null){
            currentContact = new DIContact();
        }
        mIdEditText.setText(currentContact.getDIContactData().getOrDefault("f_ID", "5").toString());
        mEmailEditText.setText(currentContact.getDIContactData().getOrDefault("f_EMail", "test1@dialoginsight.com").toString());
        mFirstNameEditText.setText(currentContact.getDIContactData().getOrDefault("f_FirstName", "Pascale").toString());
        mLastNameEditText.setText(currentContact.getDIContactData().getOrDefault("f_LastName", "Morissette").toString());
    }

    @OnClick(R.id.button_update_activity_submit)
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

        DIContact contactData = new DIContact();
        contactData.getDIContactData().put("f_ID", mIdEditText.getText().toString());
        contactData.getDIContactData().put("f_EMail", mEmailEditText.getText().toString());
        contactData.getDIContactData().put("f_FirstName", mFirstNameEditText.getText().toString());
        contactData.getDIContactData().put("f_LastName", mLastNameEditText.getText().toString());
        //contactData.getDIContactData().put("f_Membre", true);

        DIAnalytics.updateContact(contactData);
        DIAnalytics.requestToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String token) {
                DIUtils.displayLog("Current token is: " + token);
            }
        });
        finish();
    }
}
