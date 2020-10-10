package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_double_seek_bar.*

class DoubleSeekBarActivity : AppCompatActivity() {
    val TAG = "DoubleSeekBarActivity";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_double_seek_bar)
        seekbar_two.thumb  = getDrawable(R.drawable.ic_launcher)
        seekbar_one.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.e(TAG, "currentProgress$progress")
                seekbar_two.progress = progress
            }
            //监听开始拖动滚动条时的操作
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            // 停止拖动滚动条的操作
            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }
}
