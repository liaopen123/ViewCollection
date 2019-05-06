package almostlover.com.viewcollection.aspectj.inject;


import almostlover.com.viewcollection.aspectj.DebugLog;
import almostlover.com.viewcollection.aspectj.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TraceAspect {

    // "execution(@org.android10.gintonic.annotation.DebugTrace * *(..))";
    private static final  String POINTCUT_METHOD = "execution(@almostlover.com.viewcollection.aspectj.inject.DeBugTrace  * *(..))";
    private static final  String POINTCUT_CONSTUCTOR = "execution(@almostlover.com.viewcollection.aspectj.inject.DeBugTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace(){}


    @Pointcut(POINTCUT_CONSTUCTOR)
    public void constructorAnnotatedDebugTrace(){}

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws  Throwable{
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();//拿到类名
        String methodName = methodSignature.getName();//拿到方法名

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 被注解的方法在这一行代码被执行
        Object result = joinPoint.proceed();
        stopWatch.stop();

        DebugLog.log(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));

//        return result;
        return  null;
    }


    private static  String buildLogMessage(String methodName, long methodDuration){

        StringBuilder message = new StringBuilder();
        message.append("Gintonic --> ");
        message.append(methodName);
        message.append(" --> ");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");

        return message.toString();
    }


}
