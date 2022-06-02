package bg.tu.varna.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements ValidateListener {
    private EditText email;
    private Button login;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.editTextEmail);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        login = findViewById(R.id.Login);
        login.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login:
                runLoginTask();
                break;
        }
    }

    private void runLoginTask() {
        progressBar.setVisibility(View.VISIBLE);
        HandlerThread loginThread = new HandlerThread("LoginThread");
        loginThread.start();
        Looper looper = loginThread.getLooper();
        Handler handler = new Handler(looper);
        handler.post(new LoginTask(email.getText().toString(), MainActivity.this));
    }

    @Override
    public void OnValidateLogin(final boolean isValid) {
        //run on main thread
        runOnUiThread(() -> {
            if (!isValid) {
                email.setError("Incorrect login");
                progressBar.setVisibility(View.INVISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                Intent intent = new  Intent(getBaseContext(), TimerActivity.class);
                intent.putExtra("email", email.getText().toString());
                startActivity(intent);
            }
        });
    }
}