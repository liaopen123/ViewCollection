package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_tag_flex_box.*


class TagFlexBoxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_flex_box)
        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        rv.layoutManager = layoutManager

        val autoLoadAdapter = AutoLoadAdapter(R.layout.abc_action_menu_item_layout)
        val arrayList = ArrayList<String>();
        arrayList.add("111111");
        arrayList.add("22");
        arrayList.add("3333");
        arrayList.add("111111");
        arrayList.add("11");
        arrayList.add("8888");
        arrayList.add("22");
        arrayList.add("3333");
        arrayList.add("111111");
        arrayList.add("11");
        arrayList.add("8888");
        autoLoadAdapter.setNewData(arrayList)
        autoLoadAdapter.setOnItemClickListener { adapter, view, position ->
            Toast.makeText(
                this,
                arrayList.get(position),
                Toast.LENGTH_LONG
            ).show()
        }

        rv.adapter = autoLoadAdapter
    }


    class AutoLoadAdapter(layoutResId: Int) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.activity_main2) {

        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.tv_des, "" + item)

        }
    }
}
