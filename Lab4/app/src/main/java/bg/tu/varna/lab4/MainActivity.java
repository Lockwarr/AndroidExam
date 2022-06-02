package bg.tu.varna.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bg.tu.varna.lab4.helpers.Validator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected String activityName;
    EditText mEmail;
    Button mButtonContinue;

//    @Override
//    protected void onResume() {
//        super.onResume();
//        System.out.println("333333333Create");
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        System.out.println("2222222222222onStart");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        System.out.println("444444444Pause");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        System.out.println("45555555555555Stop");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        System.out.println("66666666666666onDestroy");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        System.out.println("77777777777onRestart");
//    }

        @Override
        public void onClick(View v) {
            // get the content of the edit text
            Intent intent = new  Intent(getBaseContext(), SecondActivity.class);
            intent.putExtra("email", String.valueOf(mEmail.getText()));
            startActivity(intent);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        mButtonContinue = findViewById(R.id.passToActivity2);
        mEmail = findViewById(R.id.email);
        mEmail.addTextChangedListener(textWatcher);

//        String username = mEmail.getText().toString();
//        Log.v("EditText", mEdit.getText().toString());

        mButtonContinue.setOnClickListener(this::onClick);

    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           Validator validator = new Validator();
           boolean isValid = validator.validateTextLength(mEmail, 8, 40);
           if (isValid) {
               mButtonContinue.setEnabled(true);
           } else {
               mButtonContinue.setEnabled(false);
           }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
//    public static boolean isValidEmail(CharSequence target) {
//        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
//    }

}