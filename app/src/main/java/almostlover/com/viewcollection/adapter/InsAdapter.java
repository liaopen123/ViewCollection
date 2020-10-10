package almostlover.com.viewcollection.adapter;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import almostlover.com.viewcollection.R;

public class InsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public InsAdapter(List data) {
        super(R.layout.item_ins, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.tv,item);
        }
}