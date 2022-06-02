package bg.tu.varna.fragmentslab6;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements IBirthdayCard {

    private Button create, remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = findViewById(R.id.createButton);
        create.setOnClickListener(v -> openDialog());

        remove = findViewById(R.id.removeButton);
        remove.setOnClickListener(v -> removeFragment());
    }

    private void removeFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("new");

        if (fragment != null) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
        }
    }

    private void openDialog() {
        FragmentManager fm = getSupportFragmentManager();
        InvitationFragment createFragment = InvitationFragment.newInstance();
        createFragment.show(fm, "CREATE_NEW_CARD");
    }

    @Override
    public void onCreateBirthdayCard(BirthdayCardModel card) {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        CardFragment fragment = CardFragment.newInstance(card);
        transaction.add(R.id.frameLayout, fragment, "new");

        transaction.commit();

    }
}