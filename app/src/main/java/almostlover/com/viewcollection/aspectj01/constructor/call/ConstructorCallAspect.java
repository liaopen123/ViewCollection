package almostlover.com.viewcollection.aspectj01.constructor.call;

import android.util.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ConstructorCallAspect {


    private static final String TAG = "ConstructorCallAspect";

    @Before("call(almostlover.com.viewcollection.aspectj01.constructor.call.ConstructorAnimal.new(..))")
    public void BeforeConstructorMethod(JoinPoint joinPoint){
        Log.e(TAG,"这个是BeforeConstructorMethod"+joinPoint.getSignature().getName());
    }
}