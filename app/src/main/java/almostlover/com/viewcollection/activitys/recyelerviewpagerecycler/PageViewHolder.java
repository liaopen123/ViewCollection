package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler;

import almostlover.com.viewcollection.R;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/8/29.
 * Description : PageViewHolder
 */

public class PageViewHolder extends RecyclerView.ViewHolder {

    public ViewPager mViewPager;
    public RelativeLayout rlVpContainer;
    public TabLayout tabLayout;

    public PageViewHolder(View view) {
        super(view);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        rlVpContainer = view.findViewById(R.id.rl_vp_container);
        tabLayout = view.findViewById(R.id.tablayout);
    }
}