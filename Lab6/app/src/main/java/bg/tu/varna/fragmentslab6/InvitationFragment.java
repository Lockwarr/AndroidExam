package bg.tu.varna.fragmentslab6;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvitationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvitationFragment extends DialogFragment {

    private Button back, create;
    private EditText name, age, message;

    private IBirthdayCard birthdayCardInterface;

    private InvitationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InvitationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvitationFragment newInstance() {
        return new InvitationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_invitation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.editTextTextPersonName);
        age = view.findViewById(R.id.editTextNumberSigned);
        message = view.findViewById(R.id.editTextTextMultiLine);

        back = view.findViewById(R.id.button4);
        back.setOnClickListener(v -> dismiss());

        create = view.findViewById(R.id.button3);
        create.setOnClickListener(v -> {
            birthdayCardInterface.onCreateBirthdayCard(new BirthdayCardModel(
                    name.getText().toString(),
                    message.getText().toString(),
                    Integer.parseInt(age.getText().toString())
            ));
            dismiss();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (!(context instanceof IBirthdayCard)) {
            throw new RuntimeException("Context is not IBirthdayCard");
        }

        birthdayCardInterface = (IBirthdayCard) context;
    }
}