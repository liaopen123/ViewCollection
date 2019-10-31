package almostlover.com.viewcollection.aspectj01.afterthrowing;


import android.util.Log;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterthrowingAspect {

    @AfterThrowing(pointcut = "call(* *..*(..))" ,throwing = "throwable")
    public void getMethodThrowing(Throwable throwable){
//        Log.e("廖鹏辉捕获异常", "hurtThrows: ", throwable);
    }

}
