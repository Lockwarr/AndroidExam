package bg.tu.varna.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import bg.tu.varna.lab7.models.Fruit;
import bg.tu.varna.lab7.models.Vegetable;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        RecycleViewCustomAdapter customRecyclerViewAdapter = new RecycleViewCustomAdapter(generate(150));

        recyclerView.setAdapter(customRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(this::onClickRandomize);
    }

    public void onClickRandomize(View v) {
        RecycleViewCustomAdapter adapter = (RecycleViewCustomAdapter) recyclerView.getAdapter();
        adapter.update(generate(50));
    }

    public static ArrayList<Vegetable> generate(int count) {
        ArrayList<Vegetable> vegetables = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            vegetables.add(
                    new Vegetable("Vegetable" + i, 0.5*i, random.nextInt(255)+1));
            vegetables.add(
                    new Fruit("Fruit" + i, 0.5*i, 9*i, random.nextInt(255)+1));
        }

        return vegetables;
    }
}