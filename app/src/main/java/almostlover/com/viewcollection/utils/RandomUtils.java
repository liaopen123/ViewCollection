package almostlover.com.viewcollection.utils;

import java.util.Random;

public class RandomUtils {


    private static Random random;

    private RandomUtils(){

    }

   public static Random getInstance(long values){
       random = new Random(values);
       return random;
   }

}
