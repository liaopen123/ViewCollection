package almostlover.com.viewcollection.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.ArrayList;
import java.util.List;

import almostlover.com.viewcollection.R;

public class CommonAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CommonAdapter( List data) {
        super(R.layout.item_lv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv,item);
        }
}