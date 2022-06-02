package bg.tu.varna.lab7;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderCustom extends RecyclerView.ViewHolder {
    private TextView name, price, count;

    public ViewHolderCustom(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        price = itemView.findViewById(R.id.price);
        count = itemView.findViewById(R.id.count);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public void setCount(String count) {
        this.count.setText(count);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.itemView.setOnClickListener(onClickListener);
    }
}
