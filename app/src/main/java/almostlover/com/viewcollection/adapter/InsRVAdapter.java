package almostlover.com.viewcollection.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.felipecsl.asymmetricgridview.AGVRecyclerViewAdapter;
import com.felipecsl.asymmetricgridview.AsymmetricItem;

import java.util.List;

import almostlover.com.viewcollection.R;
import almostlover.com.viewcollection.bean.DemoItem;

public class InsRVAdapter extends AGVRecyclerViewAdapter<InsRVAdapter.ViewHolder1> {
    private final List<DemoItem> items;
String TAG="InsRVAdapter";
    public InsRVAdapter(List<DemoItem> items) {
        this.items = items;
    }

    @Override public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecyclerViewActivity", "onCreateView");
        return new ViewHolder1(parent, viewType);
    }

    @Override public void onBindViewHolder(ViewHolder1 holder, int position) {
        Log.d("RecyclerViewActivity", "onBindView position=" + position);
        holder.bind(items.get(position));
    }

    @Override public int getItemCount() {
        return items.size();
    }

    @Override public AsymmetricItem getItem(int position) {
        return items.get(position);
    }

    @Override public int getItemViewType(int position) {
        return position % 2 == 0 ? 1 : 0;
    }


  public   class ViewHolder1 extends RecyclerView.ViewHolder  {
        private final TextView textView;

        ViewHolder1(ViewGroup parent, int viewType) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    viewType == 0 ? R.layout.adapter_item : R.layout.adapter_item_odd, parent, false));
            if (viewType == 0) {
                textView = (TextView) itemView.findViewById(R.id.textview);
            } else {
                textView = (TextView) itemView.findViewById(R.id.textview_odd);
            }
        }

        void bind(DemoItem item) {
            textView.setText(String.valueOf(item.getPosition()));
        }


  }
}