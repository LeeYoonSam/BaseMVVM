/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ys.base.basemvvm.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Build.VERSION_CODES;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class containing some static utility methods.
 */
public class Utils {
    private static final String VERSION_UNAVAILABLE = "N/A";

    private Utils() {
    }

    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT;
    }

    public static String getVersionName(Context context) {
        // Get app version
        PackageManager pm = context.getPackageManager();
        String packageName = context.getPackageName();
        String versionName;
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = VERSION_UNAVAILABLE;
        }
        return versionName;
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 5;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static String loadJsonFromFile(Context cxt,String filePath) {
        AssetManager assetManager = cxt.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(filePath);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(istr));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (FileNotFoundException fnfe) {
            //This occurs on the first time the app is run, no error message necessary.
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e2) {
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e2) {
                }
            }
        }
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String addZero(int val) {
        if (String.valueOf(val).length() == 1) {
            return "0" + String.valueOf(val);
        }

        return String.valueOf(val);
    }

    public static boolean checkUpdateVersion(Context ctx,String ver) {
        return checkUpdateVersion(getAppVersion(ctx), ver);
    }

    private static String getAppVersion(Context context) {
        String version = "";
        String name = "";

        name = context.getPackageName();

        try {
            PackageInfo i = context.getPackageManager().getPackageInfo(name, 0);
            version = i.versionName == null ? "" : i.versionName;

        } catch (Exception e) {
            e.printStackTrace();
            version = "";
        }
        return version;
    }

    private static boolean checkUpdateVersion(String ver, String tver) {


        double thisVersion = getVersion(ver);
        double updateVersion = getVersion(tver);

        Log.e("checkUpdateVersion", "thisVersion  :  " +ver +" =>"+ thisVersion + "    updateVersion  :   " +tver +" =>"+ updateVersion);

        if (thisVersion < updateVersion) {
            return true;
        }
        return false;
    }

    private static double getVersion(String val) {
        String _val;
        double rVal = 0;
        try {
            if (val.contains(".")) {
                _val = val.substring(0, val.indexOf(".") + 1) + val.substring(val.indexOf(".") + 1, val.length()).replaceAll("\\.", "");
//                Log.e("getVersion","[0] : "+val.substring(0, val.indexOf(".") + 1)+"  [1] : "+val.substring(val.indexOf(".") + 1, val.length()).replaceAll("\\.", ""));
                rVal = Double.parseDouble(_val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rVal;
    }



}
