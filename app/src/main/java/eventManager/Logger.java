package eventManager;

import android.util.Log;

import com.shayan.pourvatan.eventbus.BuildConfig;

/**
 * Created by shayanpourvatan on 5/14/17.
 */

public class Logger {

    static boolean loggingInDebug = false;
    private static final String TAG = "EventManager";

    static void printLog(String message, Exception e) {
        if (!BuildConfig.DEBUG || !loggingInDebug) {
            return;
        }

        Log.d(TAG, message, e);
    }
}
