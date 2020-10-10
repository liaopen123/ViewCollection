package almostlover.com.viewcollection.test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import almostlover.com.viewcollection.R;

public class TestJavaActivity extends AppCompatActivity {
    Integer a = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_java);
        List<Integer> list = new ArrayList<>();
        list.add(a);
        Integer integer = list.get(0);
        integer = 8000;

        Toast.makeText(this, "a::"+a, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "integer::"+integer, Toast.LENGTH_SHORT).show();

    }
}
