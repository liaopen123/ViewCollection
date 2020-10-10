package almostlover.com.viewcollection.activitys.sku

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.bean.Product
import almostlover.com.viewcollection.bean.SkuAttribute
import almostlover.com.viewcollection.utils.lphLog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson

class SKUActivity : AppCompatActivity() {

    private  val mSkuMap :HashMap<String,MutableList<String>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sku)

        val gson = Gson()
//        Gson().toJson(addressBean));

        val string = getString(R.string.product)
        val product = gson.fromJson<Product>(string, Product::class.java)
        lphLog(product.toString())

        val skus = product.skus
        for (sku in skus){
            val attributes = sku.attributes
         for (attribute in attributes){
             accumulateSKUMap(attribute)
         }
        }

        val string1 = getString(R.string.product)
    }



    private fun accumulateSKUMap(skuAttribute: SkuAttribute) {
        val key = skuAttribute.key
        val value = skuAttribute.value
        if(mSkuMap.containsKey(key)){
            val mutableList = mSkuMap[key]
            if (mutableList?.contains(value)!!) {
                return
            }else{
                mutableList?.add(value)
            }
        }else{
            val list = ArrayList<String>()
            list.add(value)
            mSkuMap[key] = list
        }
        initView(mSkuMap)
    }

    private fun initView(mSkuMap: java.util.HashMap<String, MutableList<String>>) {

    }


    fun notifyGoodsAttribute(){



    }
}
