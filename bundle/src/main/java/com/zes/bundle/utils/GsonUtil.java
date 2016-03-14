package com.zes.bundle.utils;

import com.google.gson.Gson;

/**
 * Created by zes on 16-3-13.
 */
public class GsonUtil {
    private static Gson gson;

    /**
     * 得到解析工具gson
     *
     * @return
     */
    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

}
