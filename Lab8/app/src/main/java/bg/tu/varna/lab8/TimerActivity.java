package bg.tu.varna.lab8;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity implements TimeListener{
    private TextView message, timer, validate;
    private Handler handler;
    private TimerTask timerTask;
    private Button start, stop, reset;
    private int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        handler = new Handler();
        timerTask = new TimerTask(this);
        timer = findViewById(R.id.textView);
        start = findViewById(R.id.Start);
        stop = findViewById(R.id.Stop);
        reset = findViewById(R.id.Reset);
        start.setOnClickListener(this::onClick);
        stop.setOnClickListener(this::onClick);
        reset.setOnClickListener(this::onClick);
    }

    @SuppressLint("NewApi")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Start:
                runTimerTask();
                break;
            case R.id.Stop:
                if(handler.hasCallbacks(timerTask)) {
                    handler.removeCallbacks(timerTask);
                }
                break;
            case R.id.Reset:
                ResetTimer();
                break;
        }
    }

    @SuppressLint("NewApi")
    private void runTimerTask() {
        if(handler.hasCallbacks(timerTask)) {
            handler.removeCallbacks(timerTask);
        } else {
            handler.post(timerTask);
        }
    }

    public void OnSetTimer(int time) {
        seconds += time;
        timer.setText(Integer.toString(seconds));
        handler.postDelayed(timerTask, 1000);
    }

    @SuppressLint("NewApi")
    @Override
    public void ResetTimer() {
        if(handler.hasCallbacks(timerTask)) {
            handler.removeCallbacks(timerTask);
        }
        timer.setText("0");
        seconds = 0;
    }
}