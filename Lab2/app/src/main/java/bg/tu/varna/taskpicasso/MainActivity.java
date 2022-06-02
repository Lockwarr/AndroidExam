package bg.tu.varna.taskpicasso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso.get().load("https://i.picsum.photos/id/580/200/300.jpg?hmac=ETV-og2PgiTBmJBERthfeRRRuLpWGxM4Zq_3z8pXndA").into(imageView);
    }
}