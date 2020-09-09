package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Utils {

    public static Map<String, String> read(InputStream inputStream){
        return read(inputStream,",");
    }


    public static Map<String, String> read(InputStream inputStream, String delimiter) {
        Map<String,String> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(delimiter);
                if (row.length != 2) continue;
                map.put(row[0],row[1]);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    static private final String PRF = Utils.class.getName();

    public static final String KEY_ALL_NEW = "all_new";
    public static final String KEY_MODE = "mode";
    public static final String KEY_LAN_ORI = "language_native";
    public static final String KEY_LAN_FOR = "language_foreign";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_GROUP_NAME = "group_name";
    public static final String KEY_DIFFICULTY = "difficulty_level";

    static public void saveString(Context context, String key, String str) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.putString(key, str);
        editor.apply();
    }

    static public String getString(Context context, String key) {
        SharedPreferences prf = context.getSharedPreferences(PRF, Context.MODE_PRIVATE);
        return prf.getString(key, "");
    }

    static public void saveInt(Context context, String key, int code) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.putInt(key, code);
        editor.apply();
    }

    static public int getInt(Context context, String key) {
        return getInt(context,key, 0);
    }

    static public int getInt(Context context, String key,int defaultValue) {
        SharedPreferences prf = context.getSharedPreferences(PRF, Context.MODE_PRIVATE);
        return prf.getInt(key, defaultValue);
    }

    static public void saveBoolean(Context context, String key, boolean code) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, code);
        editor.apply();
    }

    static public boolean getBoolean(Context context, String key,boolean defaultValue) {
        SharedPreferences prf = context.getSharedPreferences(PRF, Context.MODE_PRIVATE);
        return prf.getBoolean(key, defaultValue);
    }

    static public Set<String> getStringSet(Context context, String key) {
        SharedPreferences prf = context.getSharedPreferences(PRF, Context.MODE_PRIVATE);
        return prf.getStringSet(key, null);
    }

    static public void saveString2Set(Context context, String key, String str) {
        Set<String> set = getStringSet(context,key);
        if (set == null){
            SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
            set = new HashSet<>();
            set.add(str);
            editor.putStringSet(key,set);
            editor.apply();
        }else {
            set.add(str);
        }
    }

    static public void clear(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PRF, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }
}
