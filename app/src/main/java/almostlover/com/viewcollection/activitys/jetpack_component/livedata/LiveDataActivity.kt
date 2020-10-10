package almostlover.com.viewcollection.activitys.jetpack_component.livedata

import almostlover.com.viewcollection.App
import almostlover.com.viewcollection.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_live_data.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk27.coroutines.onClick

class LiveDataActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var model: NameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

//        model = ViewModelProvider(this).get(NameViewModel::class.java)
//        easyUse()
//        commonUse()
        transformLiveData()
        mediatorLiveData()
    }

    private fun commonUse() {
        //创建一个ViewModel
        var model = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(App.instances)
        ).get(NameViewModel::class.java)
        //创建一个Observer用来刷新UI
        val observerName = Observer<String> { t -> tv.text = t }
        val observerNum = Observer<Int> { t -> tv2.text = "$t" }
        model.currentName.observe(this, observerName)
        model.currentNum.observe(this, observerNum)
        btn.onClick {
            model.currentName.value = "廖鹏辉真帅"
            model.currentNum.value = 23
        }
    }

    private fun transformLiveData() {
        //创建一个ViewModel
        var model = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(App.instances)
        ).get(NameViewModel::class.java)
        //创建一个Observer用来刷新UI
        val observerName = Observer<String> { t -> tv.text = t }
        val observerNum = Observer<Int> { t -> tv2.text = "$t" }
        val observerName2 = Observer<Int> { t -> tv3.text = "$t" }

        // 在LiveData对象分发给观察者之前对其中存储的值进行更改
//        Transformations.map(model.currentName){"数据被我改变~"}.observe(this,observerName)
        Transformations.map(model.currentNum){2444}.observe(this,observerNum)

        // 在LiveData对象分发给观察者之前可以将LiveData对象改为另一个LiveData对象
        Transformations.switchMap(model.currentName) { model.anotherName }.observe(this, observerName)


        model.currentName.observe(this, observerName)
        model.currentNum.observe(this, observerNum)
        btn.onClick {
            model.currentName.value = "廖鹏辉真帅"
            model.currentNum.value = 23
        }




    }


    /**
     * 最简单的用法
     */
    private fun easyUse() {
        val liveData = MutableLiveData<String>()
        liveData.observe(this, Observer<String> {
            info { "数据发生改变 $it" }
        })
        liveData.value = "廖鹏辉真帅"
    }

    private  fun mediatorLiveData(){
//        model = ViewModelProvider(this).get(NameViewModel::class.java)
//        val nameObserver = Observer<String> { t -> text_name.text = t }
//        val mediatorLiveData = MediatorLiveData<String>()
//        mediatorLiveData.addSource(model.currentNum) { t -> mediatorLiveData.value = t.toString() }
//        mediatorLiveData.addSource(model.currentName) { t -> mediatorLiveData.value = t }
//        mediatorLiveData.observe(this, nameObserver)
    }


}