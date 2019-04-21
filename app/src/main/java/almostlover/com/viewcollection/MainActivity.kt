package almostlover.com.viewcollection

import almostlover.com.viewcollection.bean.db.SearchHistoryBean
import almostlover.com.viewcollection.bean.db.room.AppDatabase
import almostlover.com.viewcollection.bean.db.room.RoomSearBean
import almostlover.com.viewcollection.utils.MathUtils
import almostlover.com.viewcollection.utils.RandomUtils
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.greendao.gen.SearchHistoryBeanDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val nanoTime = System.nanoTime()
        Log.e(TAG, "nanoTime:$nanoTime")
        val random = RandomUtils.getInstance(nanoTime)//种子数只是随机算法的起源数字，和生成的随机数字的区间无关。
        random.nextInt()

//        //初始化数据库
////        val searchHistoryBeanDao = App.getInstances().daoSession.searchHistoryBeanDao
//        val app = Room.databaseBuilder(this, AppDatabase::class.java, "app").addMigrations().build()
//        val searchDao = app.searchDao()
//        tv_add.setOnClickListener {
//
//            searchDao.insertSearchBean(RoomSearBean(MathUtils.get6Number()))
//
////            AppDatabase mDatabase = Room.databaseBuilder(this, AppDatabase.class, "app").addMigrations().build();
////            StudentDao dao = mDatabase.studentDao();
////            Student student = new Student();
////            student.id = 111;
////            students.add(student);
//
//
//
//            if (Patterns.WEB_URL.matcher("123").matches()) {
//                //符合标准
//            } else{
//                //不符合标准
//            }
////            val searchHistoryBean = SearchHistoryBean()
////            searchHistoryBean.searchContent = ""+MathUtils.get6Number()
////            searchHistoryBeanDao.insert(searchHistoryBean)
//        }
//
//        tv_get.setOnClickListener {
////            getIdAbove20(searchHistoryBeanDao)
////            searchAll(searchHistoryBeanDao)
//            val loadAllSearchBean = searchDao.loadAllSearchBean()
//            for (item in loadAllSearchBean) {
//                Log.e("searchHistoryBeanDao", item.toString());
//            }
//        }
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
