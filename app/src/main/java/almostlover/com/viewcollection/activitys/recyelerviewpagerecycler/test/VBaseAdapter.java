package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.alibaba.android.vlayout.DelegateAdapter;

/**
 * Created by lph on 2018/6/9.
 *抽取公共context和inflater
 */

public abstract class VBaseAdapter< VH extends RecyclerView.ViewHolder> extends DelegateAdapter.Adapter<VH> {
    public Context mContext;
    public LayoutInflater inflater;



    public VBaseAdapter(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context may not be null");
        }
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
    }

}
