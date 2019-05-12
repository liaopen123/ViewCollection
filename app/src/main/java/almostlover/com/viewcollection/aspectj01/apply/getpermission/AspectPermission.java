package almostlover.com.viewcollection.aspectj01.apply.getpermission;


import almostlover.com.viewcollection.base.BaseActivity;
import almostlover.com.viewcollection.utils.PermissionUtils;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import io.reactivex.functions.Consumer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectPermission {

        @Around("execution(@almostlover.com.viewcollection.aspectj01.apply.getpermission.CheckPermission * *(..))&& @annotation(checkPermission)")
        public void checkPermission(final ProceedingJoinPoint joinPoint,CheckPermission checkPermission) throws Throwable{

            String permissionName = checkPermission.value();
            final Context context;
            if(joinPoint.getThis()  instanceof Fragment){
                android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) joinPoint.getThis();
                context = fragment.getActivity();

            }else{
                context = (Context) joinPoint.getThis();
            }

            Toast.makeText(context, "我拿到权限了+"+permissionName, Toast.LENGTH_SHORT).show();

            PermissionUtils.requestPermisson((BaseActivity)context,permissionName).doOnNext(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                }
            }).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    try {
                        if(aBoolean) {
                            joinPoint.proceed();
                        }else{
                            ((BaseActivity) context).failed();
                        }
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }

                }
            });


        }

}
