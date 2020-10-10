package almostlover.com.viewcollection.activitys.viewdrag;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import almostlover.com.viewcollection.R;
import almostlover.com.viewcollection.utils.RvUtils;
import almostlover.com.viewcollection.views.rv.VerticalRecyclerView;

public class ViewDragHelperRVActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drag_helper_rv);
        VerticalRecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList arrayList = new ArrayList<String>();
        for (int i=0;i<100;i++){
            arrayList.add("gaga"+i);
        }

        rv.setAdapter(new ContractAdapter(R.layout.item_swipe_to_delete,arrayList));
    }
}
