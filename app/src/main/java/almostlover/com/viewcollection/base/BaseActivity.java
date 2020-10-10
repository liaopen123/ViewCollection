package almostlover.com.viewcollection.base;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    
    
    public void failed(){
        Toast.makeText(this, "没有拿到权限我的哭哭啼啼", Toast.LENGTH_SHORT).show();
    }
}
