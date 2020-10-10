package almostlover.com.viewcollection.aspectj.tracemethod;

import almostlover.com.viewcollection.aspectj.StopWatch;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.util.Log;

/**
 * 截获类名最后含有Activity、Layout的类的所有非static方法（static方法另外加一个static修饰的execution或者call即可：execution( static * *..Activity+.*(..)）
 * 监听目标方法的执行时间
 */
public class TraceAspect {
//    private static Object currentObject = null;
//    //截获所有后缀为Activity或者Layout的类中所有方法的执行体（除了static，要监听static需要重新加一个static的execution 规则）
//    //target、this是用于截获运行时类型，便于做一些入参、出参的修改，或者做其他操作
//    private static final String POINTCUT_METHOD =
//            "(execution(* *..Activity+.*(..)) ||execution(* *..Layout+.*(..))) && target(Object) && this(Object)";
//
//    //截获所有后缀为Activity或者Layout的类中所有方法的调用（除了static，要监听static需要重新加一个static的execution 规则）
//    //target、this是用于截获运行时类型，便于做一些入参、出参的修改，或者做其他操作
//    private static final String POINTCUT_CALL = "(call(* *..Activity+.*(..)) || call(* *..Layout+.*(..))) && target(Object) && this(Object)";
//
//    @Pointcut(POINTCUT_METHOD)
//    public void methodAnnotated() {
//    }
//
//    @Pointcut(POINTCUT_CALL)
//    public void methodCall() {
//    }
//
//    /**
//     * 截获原方法，并替换
//     *
//     * @param joinPoint
//     * @return
//     */
//    @Around("methodAnnotated()")
//    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
//        if (currentObject == null) {
//            currentObject = joinPoint.getTarget();
//        }
//        //初始化计时器
//        final StopWatch stopWatch = new StopWatch();
//        //开始监听
//        stopWatch.start();
//        //调用原方法的执行。
//        Object result = joinPoint.proceed();
//        //监听结束
//        stopWatch.stop();
//        //获取方法信息对象
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String className;
//        //获取当前对象，通过反射获取类别详细信息
//        className = joinPoint.getThis().getClass().getName();
//
//        String methodName = methodSignature.getName();
//        String msg = buildLogMessage(methodName, 111d);
//        if (currentObject != null && currentObject.equals(joinPoint.getTarget())) {
////            DebugLog.log(new MethodMsg(className,msg,stopWatch.getTotalTime(1)));
//        } else if (currentObject != null && !currentObject.equals(joinPoint.getTarget())) {
////            DebugLog.log(new MethodMsg(className, msg,stopWatch.getTotalTime(1)));
//            Log.e(className, msg);
//            currentObject = joinPoint.getTarget();
////        DebugLog.outPut(new Path());    //日志存储
////        DebugLog.ReadIn(new Path());    //日志读取
//        }
//        return result;
//    }
//
//    @After("methodCall()")
//    public void onCallAfter(JoinPoint joinPoint) throws Throwable {
//        Log.e("onCallAfter:", "class : " + joinPoint.getSignature().getDeclaringTypeName() + "method : " + ((MethodSignature) joinPoint.getSignature()).getName());
//    }
//
//    /**
//     * 在截获的目标方法调用之前执行该Advise
//     *
//     * @param joinPoint
//     */
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    @Before("methodCall()")
//    public void onCallBefore(JoinPoint joinPoint) throws Throwable {
//        Log.e("onCallBefore:", "class : " + joinPoint.getSignature().getDeclaringTypeName() + "method : " + ((MethodSignature) joinPoint.getSignature()).getName());
//        Activity activity = null;
//        //获取目标对象，截获运行时类型
//        activity = ((Activity) joinPoint.getTarget());
//        //插入自己的实现，控制目标对象的执行
////        ChooseDialog dialog = new ChooseDialog(activity);
////        dialog.show();
//        Log.e("LPH", "LPH DO SOMETHING");
//
//        //做其他的操作
//        buildLogMessage("test", 20);
//    }
//
//    /**
//     * 创建一个日志信息
//     *
//     * @param methodName     方法名
//     * @param methodDuration 执行时间
//     * @return
//     */
//    private static String buildLogMessage(String methodName, double methodDuration) {
//        StringBuilder message = new StringBuilder();
//        message.append(methodName);
//        message.append(" --> ");
//        message.append("[");
//        message.append(methodDuration);
////        if (StopWatch.Accuracy == 1){
////            message.append("ms");
////        }else {
////            message.append("mic");
////        }
//        message.append("]      \n");
//        return message.toString();
//    }

}