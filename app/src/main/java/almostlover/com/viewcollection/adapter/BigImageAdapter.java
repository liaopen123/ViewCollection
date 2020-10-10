package almostlover.com.viewcollection.adapter;

import androidx.core.widget.NestedScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import almostlover.com.viewcollection.R;

public class BigImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    ;

    public BigImageAdapter(List<String> data) {
        super(R.layout.item_iv, data);


    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}