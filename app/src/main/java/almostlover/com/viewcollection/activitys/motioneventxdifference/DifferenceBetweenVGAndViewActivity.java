package almostlover.com.viewcollection.activitys.motioneventxdifference;

import almostlover.com.viewcollection.R;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DifferenceBetweenVGAndViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difference_between_vgand_view);
       findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.e("onCreate","v.getY"+v.getY()+",,,v.getTOP:"+v.getTop());
           }
       });
    }
}
