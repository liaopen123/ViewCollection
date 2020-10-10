package almostlover.com.viewcollection.aspectj01.method.call;

import android.util.Log;
public class AroundMethodAspect {
    private static final String TAG = "MethodAspect";



//
//    @Around("call(* almostlover.com.viewcollection.aspectj01.Animal.aroundFly(..))")
//    public void beforeMethodCallWay02(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e(TAG, "around--->" + joinPoint.getTarget().toString() + "###" + joinPoint.getSignature().getName());
//        joinPoint.proceed();
//        Log.e(TAG, "around--->" + joinPoint.getTarget().toString() + "###" + joinPoint.getSignature().getName());
//
//    }


}
