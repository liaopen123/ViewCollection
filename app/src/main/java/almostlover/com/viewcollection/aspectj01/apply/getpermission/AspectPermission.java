package almostlover.com.viewcollection.aspectj01.apply.getpermission;


public class AspectPermission {

//        @Around("execution(@almostlover.com.viewcollection.aspectj01.apply.getpermission.CheckPermission * *(..))&& @annotation(checkPermission)")
//        public void checkPermission(final ProceedingJoinPoint joinPoint,CheckPermission checkPermission) throws Throwable{
//
//            String permissionName = checkPermission.value();
//            final Context context;
//            if(joinPoint.getThis()  instanceof Fragment){
//                android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) joinPoint.getThis();
//                context = fragment.getActivity();
//
//            }else{
//                context = (Context) joinPoint.getThis();
//            }
//
//            Toast.makeText(context, "我拿到权限了+"+permissionName, Toast.LENGTH_SHORT).show();
//
//            PermissionUtils.requestPermisson((BaseActivity)context,permissionName).doOnNext(new Consumer<Boolean>() {
//                @Override
//                public void accept(Boolean aBoolean) throws Exception {
//                }
//            }).subscribe(new Consumer<Boolean>() {
//                @Override
//                public void accept(Boolean aBoolean) throws Exception {
//                    try {
//                        if(aBoolean) {
//                            joinPoint.proceed();
//                        }else{
//                            ((BaseActivity) context).failed();
//                        }
//                    } catch (Throwable throwable) {
//                        throwable.printStackTrace();
//                    }
//
//                }
//            });
//
//
//        }

}
