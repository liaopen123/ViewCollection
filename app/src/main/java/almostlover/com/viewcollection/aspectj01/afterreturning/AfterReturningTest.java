package almostlover.com.viewcollection.aspectj01.afterreturning;

import android.util.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterReturningTest {


    @AfterReturning(pointcut = "call(int afterreturning.Animal.getHeight(..))", returning = "height")
    public void getHeight(int height) {
        Log.e("廖鹏辉AR", "height:" + height);
    }


}
