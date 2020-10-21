package almostlover.com.viewcollection.activitys.layoutmanager

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.layoutmanager.fangkuai.CardItemView
import almostlover.com.viewcollection.activitys.layoutmanager.myLayoutManager.MyInsLayoutManager
import almostlover.com.viewcollection.activitys.layoutmanager.myLayoutManager.MyLinearLayoutManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_layout_manager.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.collections.ArrayList

class LayoutManagerDemo1Activity : AppCompatActivity() {


    private val mAdapter: Adapter =
       Adapter(this@LayoutManagerDemo1Activity)

     val mCount = 50
     val mGroupSize = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_manager_demo1)

        rv.apply {
            layoutManager = MyInsLayoutManager()
//            layoutManager = Demo1LayoutManager()
            val arrayList = ArrayList<String>()
            for (i in 1..100) {
                arrayList.add("" + i)
            }

            rv.adapter = RVInsAdapter(this@LayoutManagerDemo1Activity, arrayList)
//            rv.adapter = Adapter(this@LayoutManagerDemo1Activity)

        }
    }

    internal class Adapter( var layoutManagerDemo1Activity: Context) : RecyclerView.Adapter<Adapter.Holder?>(),AnkoLogger {
        var mCount = 0
        var mGroupCount = 0

        private val COLORS = intArrayOf(
            -0xff0001, -0x214779, -0xa06160,
            -0x800100, -0x9b6a13, -0x23ebc4,
            -0xff7475, -0xff9c00, -0xd0b0b1,
            -0x964c, -0xff01, -0x32a3a4,
            -0x6f1170, -0x783106, -0x800000
        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            info { "Adapter onCreateViewHolderonCreateViewHolder${mCount++}" }
            val item: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return Holder(item)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            info { "Adapter  onBindViewHolderonBindViewHolder${mGroupCount++}" }
//            holder.item.setText("" + position);
            holder.item.setCardColor(randomColor())
            holder.text.text = "菜单$position"
            holder.item.setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    layoutManagerDemo1Activity,
                    holder.text.text,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }



        private fun randomColor(): Int {
            return COLORS.get(Random().nextInt(COLORS.size))
        }

        internal inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var item: CardItemView
            var text: TextView

            init {
                item = itemView.findViewById<View>(R.id.item) as CardItemView
                text = itemView.findViewById<View>(R.id.text) as TextView
            }
        }

        override fun getItemCount(): Int {
            return 50
        }
    }
}