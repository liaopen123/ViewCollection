package almostlover.com.viewcollection.aspectj01.field;

import android.util.Log;

public class FieldAnimal {

    private static final String TAG = "FieldAnimal";

    static {
    Log.e(TAG, "static block");
}
    public  int age;

    public  int getAge() {
        return age;
    }

    public  void setAge(int age) {
        this.age = age;
    }

    public void add(){
        try {
            int age = 100 / 0;
            int i = age + 1;
        }catch (ArithmeticException e){
            e.printStackTrace();
        }
    }
}
