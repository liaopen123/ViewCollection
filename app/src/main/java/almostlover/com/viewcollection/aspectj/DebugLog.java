package almostlover.com.viewcollection.aspectj;

import android.util.Log;

public class DebugLog {
    private DebugLog() {

    }

    public static void log(String tag, String message) {
        Log.e(tag, message);

    }
}
