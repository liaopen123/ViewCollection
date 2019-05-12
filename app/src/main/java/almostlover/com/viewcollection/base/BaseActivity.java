package almostlover.com.viewcollection.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.AbstractCollection;

public class BaseActivity extends AppCompatActivity {
    
    
    public void failed(){
        Toast.makeText(this, "没有拿到权限我的哭哭啼啼", Toast.LENGTH_SHORT).show();
    }
}
