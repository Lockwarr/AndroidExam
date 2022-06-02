package bg.tu.varna.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import bg.tu.varna.lab4.helpers.Validator;

public class SecondActivity extends AppCompatActivity {
    EditText mEmail;
    EditText mName;
    EditText mCity;
    EditText mPhone;
    EditText mPostalAddress;
    Button mButtonBack;
    Button mButtonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        mButtonBack = findViewById(R.id.Back);
        mButtonNext = findViewById(R.id.Next);
        mEmail = findViewById(R.id.editTextEmail);
        mEmail.setText(email);
        mName = findViewById(R.id.editTextTextPersonName);
        mCity = findViewById(R.id.editTextCity);
        mPhone = findViewById(R.id.editTextPhone);
        mPostalAddress = findViewById(R.id.editTextTextPostalAddress);


        mButtonBack.setOnClickListener(this::onClickBack);
        mButtonNext.setOnClickListener(this::onClickNext);

        Toast.makeText(getApplicationContext(), "We are moved to second Activity",Toast.LENGTH_LONG).show();
    }

    public void onClickBack(View v) {
        // get the content of the edit text
        Intent intent = new  Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickNext(View v) {
        // get the content of the edit text
        Validator validator = new Validator();
        boolean emailValid = validator.validateTextLength(mEmail, 8, 40);
        boolean nameValid = validator.validateTextLength(mName, 2, 100);
        boolean cityValid = validator.validateTextLength(mCity, 5, 150);
        boolean addressValid = validator.validateTextLength(mPostalAddress, 5, 255);
        boolean phoneValid = validator.validateNumber(mPhone, "08", "10");
        if (!emailValid) {
            mButtonNext.setEnabled(false);
        } else if (!nameValid) {
            mButtonNext.setEnabled(false);
        } else if (!cityValid) {
            mButtonNext.setEnabled(false);
        } else if (!addressValid) {
            mButtonNext.setEnabled(false);
        } else if (!phoneValid) {
            mButtonNext.setEnabled(false);
        } else {
            mButtonNext.setEnabled(true);
        }
    }
}