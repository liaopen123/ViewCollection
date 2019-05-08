package almostlover.com.viewcollection.aspectj01.method.execution;


import android.util.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AroundExecutionAspect {

    private static final String TAG = "BeforeExecutionAspect";

    @Around("execution (* almostlover.com.viewcollection.aspectj01.AroundAnimal.fly(..))")
    public void ExecutionMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "round爱吃火锅底料");
        joinPoint.proceed();
        Log.e(TAG, "round爱吃火锅底料");
    }
}
