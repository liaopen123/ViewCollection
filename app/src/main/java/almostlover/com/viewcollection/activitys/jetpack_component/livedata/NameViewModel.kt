package almostlover.com.viewcollection.activitys.jetpack_component.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel: ViewModel() {

    val currentName:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val anotherName:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val currentNum:MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

}