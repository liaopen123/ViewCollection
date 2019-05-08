package almostlover.com.viewcollection.aspectj01.field;

import android.util.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class FieldAspect {


    private static final String TAG = "FieldAspect";

//    @Around("get(int almostlover.com.viewcollection.aspectj01.field.FieldAnimal.age)")
    public int changeAge(ProceedingJoinPoint joinPoint) throws Throwable {

        Object object = joinPoint.proceed();

        int age = Integer.parseInt(object.toString());
        Log.e(TAG,"这个是得到的age:"+age);

        return 100;
    }

    @Around("set(int almostlover.com.viewcollection.aspectj01.field.FieldAnimal.age)")
    public void aroundSetAge(ProceedingJoinPoint joinPoint){
        Log.e(TAG,"不让他们动");

    }
    //这个是插入在静态方法内部
    @Before("staticinitialization(almostlover.com.viewcollection.aspectj01.field.FieldAnimal)")
    public void staticBlock(JoinPoint joinPoint){
        Log.e(TAG, "beforeStaticBlock: ");
    }
    @After("staticinitialization(almostlover.com.viewcollection.aspectj01.field.FieldAnimal)")
    public void afterStaticBlock(JoinPoint joinPoint){
        Log.e(TAG, "afterStaticBlock: ");
    }


    @Before("handler(java.lang.ArithmeticException)")
    public void handler() {
        Log.e(TAG, "handlerhandlerhandlerhandlerhandler");
    }
}
