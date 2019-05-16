package almostlover.com.viewcollection.aspectj01;

import almostlover.com.viewcollection.R;
import almostlover.com.viewcollection.annotation.Test;
import almostlover.com.viewcollection.annotation.TestAnn;
import almostlover.com.viewcollection.aspectj01.afterreturning.Animal;
import almostlover.com.viewcollection.aspectj01.apply.getpermission.CheckPermission;
import almostlover.com.viewcollection.aspectj01.constructor.call.ConstructorAnimal;
import almostlover.com.viewcollection.aspectj01.field.FieldAnimal;
import almostlover.com.viewcollection.base.BaseActivity;
import almostlover.com.viewcollection.utils.PermissionUtils;
import android.Manifest;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import cn.com.almostlover.annotation.MyClass;
import cn.com.almostlover.annotation.testanno.TestAnno;
import cn.com.almostlover.javassist.JavassistDemo;
import cn.com.almostlover.javassist.ModifyOtherClassDemo;

public class Main3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        Animal animal = new Animal();
//        AroundAnimal aroundAnimal = new AroundAnimal();
////        animal.beforeFly();
////        animal.aroundFly();
////        animal.afterFly();
//        animal.fly();
//        aroundAnimal.fly();
//        aroundAnimal.beforeFly();

        MyClass myClass = new MyClass();
        myClass.testAnnotion();
        TestAnno testAnno = new TestAnno();
        testAnno.testBug();
//        ConstructorAnimal.fly();
        FieldAnimal fieldAnimal = new FieldAnimal();
        fieldAnimal.setAge(20);

        Animal animal = new Animal();
        animal.getHeight();
        animal.getHeight(1);
        try {
            fieldAnimal.add();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "得到的age:" + fieldAnimal.getAge(), Toast.LENGTH_SHORT).show();


        ModifyOtherClassDemo modifyOtherClassDemo = new ModifyOtherClassDemo();
        modifyOtherClassDemo.test();
        test();
    }

    @CheckPermission(Manifest.permission.CAMERA)
    public void test() {
        Toast.makeText(this, "拿到权限的我为所欲为", Toast.LENGTH_SHORT).show();

    }


}
