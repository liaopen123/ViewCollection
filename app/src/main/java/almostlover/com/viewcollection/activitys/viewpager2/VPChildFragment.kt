package almostlover.com.viewcollection.activitys.viewpager2

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.ext.dp2px
import almostlover.com.viewcollection.views.GridItemDecoration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_v_p_child.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class VPChildFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var childRecyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
      val rootView =  inflater.inflate(R.layout.fragment_v_p_child, container, false)
        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);

        childRecyclerView = rootView as RecyclerView
        childRecyclerView!!.layoutManager = layoutManager
        childRecyclerView!!.addItemDecoration(GridItemDecoration(activity!!.dp2px(8f)))
        childRecyclerView!!.adapter = FeedsListAdapter(activity!!)
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VPChildFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VPChildFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}