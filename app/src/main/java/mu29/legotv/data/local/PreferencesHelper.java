package mu29.legotv.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * InJung Chung
 */

public class PreferencesHelper {

    private Context context;
    private static PreferencesHelper helper;

    public PreferencesHelper(Context context) {
        this.context = context;
    }

    public static PreferencesHelper getInstance(Context context) {
        if (helper == null)
            helper = new PreferencesHelper(context);

        return helper;
    }

    // 값 불러오기
    public String getString(String key) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    // 값 저장하기
    public void setString(String key, String value) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    // 값 지우기
    public void removePreferences(String key) {
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }

}

