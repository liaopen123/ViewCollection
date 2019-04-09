package almostlover.com.viewcollection

import almostlover.com.viewcollection.bean.db.SearchHistoryBean
import almostlover.com.viewcollection.utils.MathUtils
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.greendao.gen.SearchHistoryBeanDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //初始化数据库
        val searchHistoryBeanDao = App.getInstances().daoSession.searchHistoryBeanDao

        tv_add.setOnClickListener {
            if (Patterns.WEB_URL.matcher("123").matches()) {
                //符合标准
            } else{
                //不符合标准
            }
            val searchHistoryBean = SearchHistoryBean()
            searchHistoryBean.searchContent = ""+MathUtils.get6Number()
            searchHistoryBeanDao.insert(searchHistoryBean)
        }

        tv_get.setOnClickListener {
            getIdAbove20(searchHistoryBeanDao)
//            searchAll(searchHistoryBeanDao)
        }
    }

    private fun searchAll(searchHistoryBeanDao: SearchHistoryBeanDao) {
        val loadAll = searchHistoryBeanDao.loadAll()
        for (item in loadAll) {
            Log.e("searchHistoryBeanDao", item.toString());
        }
    }

    private fun getIdAbove20(searchHistoryBeanDao: SearchHistoryBeanDao) {
        val loadAll = searchHistoryBeanDao.queryBuilder().where(SearchHistoryBeanDao.Properties.Id.ge(10)).list()
        for (item in loadAll.reversed()) {
            Log.e("searchHistoryBeanDao", item.toString());
        }


    }


    /**
     * 当size大于30的时候  清空一次
     */
    fun deleteAll(searchHistoryBeanDao: SearchHistoryBeanDao,list:List<SearchHistoryBean>){
        searchHistoryBeanDao.deleteInTx(list)
    }


}
