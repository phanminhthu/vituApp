package danazone.com.vitu.util;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;

public class Utils {

    public static void showAlertDialog(Context context,@NonNull String msg) {
        try {
            new AlertDialog.Builder(context)
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
