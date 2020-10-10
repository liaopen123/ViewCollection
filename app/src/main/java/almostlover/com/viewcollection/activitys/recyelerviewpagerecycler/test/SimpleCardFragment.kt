package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.PagerListAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_base.*

@SuppressLint("ValidFragment")
class SimpleCardFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvHot.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(activity, 2)
        rvHot.setOnHeadLister()
        rvHot.adapter = PagerListAdapter("GAGA")
    }

    companion object {
        fun getInstance(title: String): SimpleCardFragment {
            return SimpleCardFragment()
        }
    }
}