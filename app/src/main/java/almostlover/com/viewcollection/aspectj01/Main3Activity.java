package almostlover.com.viewcollection.aspectj01;

import almostlover.com.viewcollection.R;
import almostlover.com.viewcollection.aspectj01.constructor.call.ConstructorAnimal;
import almostlover.com.viewcollection.aspectj01.field.FieldAnimal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

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


//        ConstructorAnimal.fly();
        FieldAnimal fieldAnimal = new FieldAnimal();
        fieldAnimal.setAge(20);
        fieldAnimal.add();
        Toast.makeText(this, "得到的age:"+ fieldAnimal.getAge(), Toast.LENGTH_SHORT).show();
    }
}
