package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String email = intent.getStringExtra("email");

        TextView showUserName = (TextView) findViewById(R.id.showUserName);
        TextView showPhoneNumber = (TextView) findViewById(R.id.showPhoneNumber);
        TextView showEmail = findViewById(R.id.showEmail);
        showUserName.setText(userName);
        showPhoneNumber.setText(phoneNumber);
        showEmail.setText(email);

    }
    public void showEmail(View view){
        CheckBox checkBox = (CheckBox)view;
        TextView showEmail = findViewById(R.id.showEmail);

        if(checkBox.isChecked()){
            showEmail.setVisibility(View.VISIBLE);
        }
        else {
            showEmail.setVisibility(View.GONE);
        }
    }

    public void backToMainActivity(View view){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void goToEditActivity(View view){
        Intent editActivity = new Intent(this, ThirdActivity.class);
        TextView editUserName = findViewById(R.id.showUserName);
        TextView editPhoneNumber = findViewById(R.id.showPhoneNumber);
        TextView showEmail = findViewById(R.id.showEmail);
        String userName = editUserName.getText().toString();
        String phoneNumber = editPhoneNumber.getText().toString();
        String email = showEmail.getText().toString();
        editActivity.putExtra("userName", userName);
        editActivity.putExtra("phoneNumber", phoneNumber);
        editActivity.putExtra("email", email);
        startActivity(editActivity);
    }
}