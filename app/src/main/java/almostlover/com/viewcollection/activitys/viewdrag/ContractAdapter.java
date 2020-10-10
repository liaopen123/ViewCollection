package almostlover.com.viewcollection.activitys.viewdrag;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import almostlover.com.viewcollection.R;

public class ContractAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ContractAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        try {
            helper.setText(R.id.text_view_name, item);
            helper.setText(R.id.button_hidden, item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}