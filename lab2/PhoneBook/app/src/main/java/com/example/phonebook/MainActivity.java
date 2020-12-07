package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendUserInfo(View view) {

        Intent intent = new Intent(this, DisplayUserActivity.class);
        EditText userNameEdit = findViewById(R.id.userNameEdit);
        EditText phoneNumberEdit = findViewById(R.id.phoneNumberEdit);
        EditText emailEdit = findViewById(R.id.emailEdit);
        String userName = userNameEdit.getText().toString();
        String phoneNumber = phoneNumberEdit.getText().toString();
        String email = emailEdit.getText().toString();
        intent.putExtra("userName", userName);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}