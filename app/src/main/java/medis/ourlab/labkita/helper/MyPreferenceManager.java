package medis.ourlab.labkita.helper;

/**
 * Created by tri on 10/3/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import medis.ourlab.labkita.model.User;

/**
 * Created by Lincoln on 07/01/16.
 */
public class MyPreferenceManager {

    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "androidhive_gcm";

    // All Shared Preferences Keys
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_NAME = "namae";
    private static final String KEY_BALANCE = "balance";
    private static final String KEY_FOTO = "foto";
    private static final String KEY_MOTTO = "motto";

    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void storeUser(User user) {
        editor.putString(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_NAME, user.getNamae());
        editor.putString(KEY_FOTO, user.getFoto());
        editor.putString(KEY_MOTTO, user.getMotto());
        editor.putFloat(KEY_BALANCE,(float) user.getBalance());
        editor.commit();

        Log.e(TAG, "User is stored in shared preferences. " + user.getName() + ", " + user.getEmail());
    }

    public User getUser() {
        if (pref.getString(KEY_USER_ID, null) != null) {
            String id, name, email,namae,foto,motto;
            float balance;
            id = pref.getString(KEY_USER_ID, null);
            name = pref.getString(KEY_USER_NAME, null);
            email = pref.getString(KEY_USER_EMAIL, null);
            namae = pref.getString(KEY_NAME, null);
            balance = pref.getFloat(KEY_BALANCE,0);
            foto = pref.getString(KEY_FOTO,null);
            motto= pref.getString(KEY_MOTTO,null);
            User user = new User(id, name, email,namae,balance,foto,motto);
            return user;
        }
        return null;
    }

    public void addNotification(String notification) {

        // get old notifications
        String oldNotifications = getNotifications();

        if (oldNotifications != null) {
            oldNotifications += "|" + notification;
        } else {
            oldNotifications = notification;
        }

        editor.putString(KEY_NOTIFICATIONS, oldNotifications);
        editor.commit();
    }

    public String getNotifications() {
        return pref.getString(KEY_NOTIFICATIONS, null);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}