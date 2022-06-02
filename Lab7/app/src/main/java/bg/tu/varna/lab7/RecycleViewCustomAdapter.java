package bg.tu.varna.lab7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bg.tu.varna.lab7.models.Vegetable;
import java.util.ArrayList;

public class RecycleViewCustomAdapter extends RecyclerView.Adapter<ViewHolderCustom> {

    private ArrayList<Vegetable> vegetables;

    public RecycleViewCustomAdapter(ArrayList<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    @NonNull
    @Override
    public ViewHolderCustom onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);

        return new ViewHolderCustom(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCustom holder, int position) {
        Vegetable vegetables = this.vegetables.get(position);

        holder.setName(vegetables.getName());
        holder.setPrice(Double.toString(vegetables.getPrice()));
        holder.setCount(Integer.toString(vegetables.getCount()));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), vegetables.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return vegetables.size();
    }

    public void update(ArrayList<Vegetable> generate) {
        vegetables = generate;
        notifyDataSetChanged();
    }
}
