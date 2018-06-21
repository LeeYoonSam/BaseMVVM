package com.ys.base.basemvvm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CommonPreferences {

    static String PREFERENCES_NAME = "common_setting";

    public static String getValue(Context context, String args, String key,
                                  String defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(args,
                Activity.MODE_PRIVATE);
        try {
            return prefs.getString(key, defaultValue);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "";
    }

    public static int getValue(Context context, String args, String key,
                               int defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(args,
                Activity.MODE_PRIVATE);

        try {
            return prefs.getInt(key, defaultValue);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean getValue(Context context, String args, String key,
                                   boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(args,
                Activity.MODE_PRIVATE);
        try {
            return prefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public static long getValue(Context context, String args, String key,
                                long defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(args,
                Activity.MODE_PRIVATE);
        try {
            return prefs.getLong(key, defaultValue);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean putValue(Context context, String args, String key,
                                   String value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(args,
                    Activity.MODE_PRIVATE);
            SharedPreferences.Editor ed = prefs.edit();
            ed.putString(key, value);
            return ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    public static boolean putValue(Context context, String args, String key,
                                   int value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(args,
                    Activity.MODE_PRIVATE);
            SharedPreferences.Editor ed = prefs.edit();
            ed.putInt(key, value);
            return ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    public static boolean putValue(Context context, String args, String key,
                                   long value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(args,
                    Activity.MODE_PRIVATE);
            SharedPreferences.Editor ed = prefs.edit();
            ed.putLong(key, value);

            return ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean putValue(Context context, String args, String key,
                                   boolean value) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(args,
                    Activity.MODE_PRIVATE);
            SharedPreferences.Editor ed = prefs.edit();
            ed.putBoolean(key, value);

            return ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getValue(Context context, String key, String defaultValue) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME,
                    Activity.MODE_PRIVATE);
            return prefs.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getValue(Context context, String key, int defaultValue) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME,
                    Activity.MODE_PRIVATE);

            return prefs.getInt(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }



    public static boolean getValue(Context context, String key, boolean defaultValue) {
        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME,
                    Activity.MODE_PRIVATE);
            return prefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static long getValue(Context context, String key, long defaultValue) {

        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME,
                    Activity.MODE_PRIVATE);

            return prefs.getLong(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return -1;
    }

    public static boolean putValue(Context context, String key, String value) {

        try {
            SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
            SharedPreferences.Editor ed = prefs.edit();
            ed.putString(key, value);

            return ed.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean putValue(Context context, String key, int value) {

        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putInt(key, value);

        return ed.commit();
    }

    public static boolean putValue(Context context, String key, long value) {

        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putLong(key, value);

        return ed.commit();
    }

    public static boolean putValue(Context context, String key, boolean value) {

        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putBoolean(key, value);

        return ed.commit();
    }

}
