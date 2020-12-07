package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();

        String userName = intent.getStringExtra("userName");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String email = intent.getStringExtra("email");

        TextView editUserName = (EditText) findViewById(R.id.userNameEdit);
        TextView editPhoneNumber = (EditText) findViewById(R.id.phoneNumberEdit);
        TextView editEmail = findViewById(R.id.emailEdit);
        editUserName.setText(userName);
        editPhoneNumber.setText(phoneNumber);
        editEmail.setText(email);

    }

    public void saveUserInfo(View view) {
        Intent displayUserInfo = new Intent(this, DisplayUserActivity.class);
        EditText userNameEdit = findViewById(R.id.userNameEdit);
        EditText phoneNumberEdit = findViewById(R.id.phoneNumberEdit);
        EditText emailEdit = findViewById(R.id.emailEdit);
        String userName = userNameEdit.getText().toString();
        String phoneNumber = phoneNumberEdit.getText().toString();
        String email = emailEdit.getText().toString();
        displayUserInfo.putExtra("userName", userName);
        displayUserInfo.putExtra("phoneNumber", phoneNumber);
        displayUserInfo.putExtra("email", email);
        startActivity(displayUserInfo);
    }

    public void backToMainActivity(View view){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}