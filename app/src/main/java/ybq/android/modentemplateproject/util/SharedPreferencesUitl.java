package ybq.android.modentemplateproject.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashSet;
import java.util.Set;

import ybq.android.modentemplateproject.app.AppConstants;

public class SharedPreferencesUitl implements AppConstants {

	public static void setStringValue(Context context, String key, String value) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = spf.edit();
		edit.putString(key, value);
		edit.apply();
	}

	public static String getStringValue(Context context, String key) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		return spf.getString(key, EMPTY_STRING);
	}

	public static void setIntValue(Context context, String key, int value) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = spf.edit();
		edit.putInt(key, value);
		edit.apply();
	}

	public static int getIntValue(Context context, String key) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		return spf.getInt(key, 0);
	}

	public static void setLongValue(Context context, String key, long value) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = spf.edit();
		edit.putLong(key, value);
		edit.apply();
	}

	public static long getLongValue(Context context, String key) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		return spf.getLong(key, 0);
	}

	public static void setBooleanValue(Context context, String key,
                                       boolean value) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = spf.edit();
		edit.putBoolean(key, value);
		edit.apply();
	}

	public static boolean getBooleanValue(Context context, String key) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		return spf.getBoolean(key, false);
	}

	public static void setSetValue(Context context, String key,
                                   Set<String> values) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = spf.edit();
		edit.putStringSet(key, values);
		edit.apply();
	}

	public static Set<String> getSetValue(Context context, String key) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		return spf.getStringSet(key, new HashSet<String>());
	}

	public static void removeValue(Context context, String key) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = spf.edit();
		edit.remove(key);
		edit.apply();
	}

	public static void cleanValue(Context context) {
		SharedPreferences spf = context.getSharedPreferences(
				AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor edit = spf.edit();
		edit.clear();
		edit.apply();
	}
}

