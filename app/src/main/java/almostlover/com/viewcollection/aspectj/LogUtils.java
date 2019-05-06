package almostlover.com.viewcollection.aspectj;

import almostlover.com.viewcollection.aspectj.inject.DeBugTrace;
import android.util.Log;

public class LogUtils {
    @DeBugTrace
    public  static void log(){
        try{
            Log.e("logloglog","LogUtilsLogUtilsLogUtilsLogUtils");
            Thread.sleep(100);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
