package almostlover.com.viewcollection.aspectj01.method.call;

import android.util.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AfterMethodAspect {
    private static final String TAG = "AfterMethodAspect";



    @After("call(* almostlover.com.viewcollection.aspectj01.Animal.afterFly(..))")
    public void beforeMethodCallWay02(JoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "after---->" + joinPoint.getTarget().toString() + "###" + joinPoint.getSignature().getName());

    }


}
