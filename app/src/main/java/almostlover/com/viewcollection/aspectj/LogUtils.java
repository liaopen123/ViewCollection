package almostlover.com.viewcollection.aspectj;

import almostlover.com.viewcollection.aspectj.inject.DeBugTrace;

public class LogUtils {
    @DeBugTrace
    public  static void log(){
        try{
            Thread.sleep(100);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
