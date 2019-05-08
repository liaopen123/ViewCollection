package almostlover.com.viewcollection.aspectj01;

import android.util.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class MethodWay1Aspect {
    private static final String TAG = "MethodAspect";


    @Pointcut("call(* almostlover.com.viewcollection.aspectj01.Animal.fly(..))")
    public void callMethod() {
    }
    @Before("callMethod()")
    public void beforeMethodCall(JoinPoint joinPoint) {
        Log.e(TAG, "before->我在之前调用了这个方法" + joinPoint.getTarget().toString() + "###" + joinPoint.getSignature().getName());
    }


}
