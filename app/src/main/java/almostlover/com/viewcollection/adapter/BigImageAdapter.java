package almostlover.com.viewcollection.adapter;

import android.support.v4.widget.NestedScrollView;
import android.view.ViewGroup;
import android.widget.ImageView;

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