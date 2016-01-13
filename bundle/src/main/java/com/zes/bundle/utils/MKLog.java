package com.zes.bundle.utils;

/**
 * 类名称：MKLog 类描述：日志打印类，可以写入文件，通过DEBUG和WRITE_FILE控制 创建人：anan 创建时间：2012-11-23
 * 下午4:52:54 修改人：anan 修改时间：2012-11-23 下午4:52:54 修改备注：
 */
public class MKLog {
    public final static boolean DEBUG = true;

    public final static String TAG = MKLog.class.getSimpleName();

    public static void pln(Object obj) {
        if (DEBUG && null != obj)
            System.out.println(obj);
    }

    public static void p(Object obj) {
        if (DEBUG && null != obj)
            System.out.print(obj);
    }

    public static void i(String tag, String msg) {
        if (DEBUG && null != msg)
            android.util.Log.i(tag, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG && null != msg)
            android.util.Log.d(tag, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG && null != msg)
            android.util.Log.e(tag, msg);
    }

    public static void e(String msg) {
        if (DEBUG && null != msg)
            android.util.Log.e(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG && null != msg)
            android.util.Log.v(tag, msg);
    }

    public static void v(String msg) {
        v(TAG, msg);
    }
}
