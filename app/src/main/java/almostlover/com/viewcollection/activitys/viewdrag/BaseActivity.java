package almostlover.com.viewcollection.activitys.viewdrag;

import almostlover.com.viewcollection.views.sixway2dragview.drag7viewdraghelper.Drag7FloatingDraggerViewGroup;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * @author hiphonezhu@gmail.com
 * @version [FloatingOval, 16/12/29 21:08]
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(new Drag7FloatingDraggerViewGroup(this, layoutResID).getView());
    }
}
