package almostlover.com.viewcollection.aspectj01.method.execution;


import android.util.Log;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BeforeExecutionAspect {


    private static final String TAG = "BeforeExecutionAspect";

    @Before("execution (* almostlover.com.viewcollection.aspectj01.AroundAnimal.beforeFly(..))")
    public void beforeExecutionMethod() {
        Log.e(TAG, "爱吃火锅底料");
    }
}
