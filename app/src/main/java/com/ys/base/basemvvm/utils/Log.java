package com.ys.base.basemvvm.utils;

import android.content.Context;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Log class
 * 
 * 
 * 배포시 DEBUG = false 로 지정
 * 
 */
public final class Log {
	public static  boolean DEBUG = false;//true;//

	/**
	 * verbose log print
	 * @return int
	 */
	public static int v(String tag, String msg) {
		if (!DEBUG) {
			return -1;
		}
		return android.util.Log.v(tag, msg);
	}

	/**
	 * verbose log print
	 * @return int
	 */
	public static int v(String tag, String msg, Throwable tr) {
		if (!DEBUG) {
			return -1;
		}
		return android.util.Log.v(tag, msg, tr);
	}

	/**
	 * error log print
	 * @return int
	 */
	public static int e(String tag, Object msg) {
		if (!DEBUG) {
			return -1;
		}

		return android.util.Log.e(tag, String.valueOf(msg));
	}

	/**
	 * error log print
	 * @return int
	 */
	public static int e(String tag, Object msg, Throwable tr) {
		if (!DEBUG) {
			return -1;
		}
		return android.util.Log.e(tag, String.valueOf(msg), tr);
	}

	/**
	 * worn log print
	 * @return int
	 */
	public static int w(String tag, String msg) {
		if (!DEBUG) {
			return -1;
		}

		return android.util.Log.w(tag, msg);
	}

	/**
	 * worn log print
	 * @return int
	 */
	public static int w(String tag, String msg, Throwable tr) {
		if (!DEBUG) {
			return -1;
		}
		return android.util.Log.w(tag, msg, tr);
	}

	/**
	 * info log print
	 * @return int
	 */
	public static int i(String tag, String msg) {
		if (!DEBUG) {
			return -1;
		}

		return android.util.Log.i(tag, msg);
	}

	/**
	 * info log print
	 * @return int
	 */
	public static int i(String tag, String msg, Throwable tr) {
		if (!DEBUG) {
			return -1;
		}
		return android.util.Log.i(tag, msg, tr);
	}

	/**
	 * debug log print
	 * @return int
	 */
	public static int d(String tag, String msg) {
		if (!DEBUG) {
			return -1;
		}

		return android.util.Log.d(tag, msg);
	}

	/**
	 * debug log print
	 * @return int
	 */
	public static int d(String tag, String msg, Throwable tr) {
		if (!DEBUG) {
			return -1;
		}
		return android.util.Log.d(tag, msg, tr);
	}
	
	
	
	
	
	public static void ex(Exception e){
		if (!DEBUG){
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			String s = writer.toString();
			Log.w("Exception",s);
		}
		
		
	}
	


	public static final synchronized void SaveLog(Context context,
                                                  String loginfo, String fileName) {
		// TODO log test ramon
		Log.e("loginfo", loginfo);
		if (!DEBUG) 
		{
//			String filepath = ilbsConfig.getLogFilePath();
//			File dir = new File(Environment.getExternalStorageDirectory()
//					.getAbsolutePath() + filepath);
//			if (!dir.exists())
//				dir.mkdirs();
//
//			try {
//				FileOutputStream fos = new FileOutputStream(new File(dir,
//						fileName), true);
//				fos.write(loginfo.toString().getBytes());
//				fos.close();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	
	
	
	
	
}
