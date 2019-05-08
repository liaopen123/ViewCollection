package almostlover.com.viewcollection.aspectj01;

import android.util.Log;

public class AroundAnimal {
    private String TAG = "AroundAnimal";

    public void fly(){
        Log.e(TAG,this.toString()+"##fly");
    }

    public void beforeFly(){
        Log.e(TAG,this.toString()+"##AroundAnimalbeforeFly");
    }
    public void afterFly(){
        Log.e(TAG,this.toString()+"##afterFly");
    }


    public void aroundFly(){
        Log.e(TAG,this.toString()+"##aroundFly");
    }
}
