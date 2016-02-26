package com.zes.xiaoxuntakeaway.database;

/**
 * Created by zes on 16-2-18.
 */

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.zes.xiaoxuntakeaway.app.MyApplication;

public class DbHelper {


    private static DB snappyDb;

    public static DB getSnappyDb() {
        try {
            snappyDb = DBFactory.open(MyApplication.getAppContext(), "takeaway");
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return snappyDb;
    }

    public static void closeSnappyDb(){
        try {
            snappyDb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public static void destorySnappyDb(){
        try {
            snappyDb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

}
