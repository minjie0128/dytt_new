
package com.minj.util;

import android.util.Log;

public class LogX {

    
    private static boolean needRecord=true;

    public static void d(String tag, String message) {
        if (needRecord) {
            Log.i(tag, message);
        }
    }

    public static void snipet(String tag, String message) {
        if (!needRecord) {
            return;
        }
        int maxLogSize = 1000;
        for (int i = 0; i <= message.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > message.length() ? message.length() : end;
            Log.i(tag, message.substring(start, end));
        }
    }
    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    public static void trace(String tag, String message) {
        if (needRecord) {
            Log.i(tag, message);
        }
    }

    public LogX(String path) {
    }
}
