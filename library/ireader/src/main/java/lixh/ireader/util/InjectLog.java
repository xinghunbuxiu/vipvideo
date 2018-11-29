package lixh.ireader.util;

import android.util.Log;

public class InjectLog {
    public static void PrintFunc() {
        PrintFunc(null);
    }

    public static void PrintFunc(String log1) {
        PrintFunc(log1, null);
    }

    public static void PrintFunc(String log1, String log2) {
        PrintFunc(log1, log2, null);
    }

    public static void PrintFunc(String log1, String log2, String log3) {
        StringBuffer params = new StringBuffer();
        params.append("param:\u2000");
        if (log1 != null) {
            params.append("[" + log1 + "]\n");
        }
        if (log2 != null) {
            params.append("[" + log2 + "]\n");
        }
        if (log3 != null) {
            params.append("[" + log3 + "]\n");
        }
        Thread cur_thread = Thread.currentThread();
        StackTraceElement stack[] = cur_thread.getStackTrace();
        Log.d("InjectLog", "param:" + "[" + params.toString() + "]" + stack[3].toString() + "[" + cur_thread.getId() + "]");
    }
}