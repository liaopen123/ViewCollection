package almostlover.com.viewcollection.aspectj01.method.call;

import android.util.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BeforeMethodAspect {
    private static final String TAG = "BeforeMethodAspect";



    @Before("call(* almostlover.com.viewcollection.aspectj01.Animal.beforeFly(..))")
    public void beforeMethodCallWay02(JoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "before--->" + joinPoint.getTarget().toString() + "###" + joinPoint.getSignature().getName());

    }


}
